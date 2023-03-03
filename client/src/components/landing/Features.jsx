import { useAuth0 } from "@auth0/auth0-react";
import { Link } from "react-router-dom";
import {
  communityExampleDesktop,
  communityExampleMobile,
} from "../../assets/AssetFiles";

import SignupButton from "../SignupButton";

const Features = () => {
  const { isAuthenticated } = useAuth0();

  return (
    <div className="flex flex-col items-center gap-5 bg-slate-50 px-4 pt-16 pb-16 text-center md:pt-24 md:pb-0 lg:px-32">
      <h2 className="text-4xl font-semibold text-slate-900">
        All-in-one board for your community
      </h2>
      <p className="text-lg text-slate-500">
        Organise your litter picking events, see the statistics of your members,
        and recruit new volunteers from your dashboard!
      </p>
      {!isAuthenticated ? (
        <SignupButton buttonText={"Get started"} />
      ) : (
        <Link
          to="/communities"
          className="flex min-w-[92px] flex-1 items-center justify-center gap-2 rounded-lg border border-green-800 bg-green-800 py-2.5 px-4 text-white shadow-sm outline-slate-900 transition-colors hover:border-green-900 hover:bg-green-900 md:max-w-[182px] md:flex-none"
        >
          Visit communities
        </Link>
      )}

      <picture className="mt-12 max-w-6xl">
        <source srcSet={communityExampleMobile} media="(max-width: 768px)" />
        <source srcSet={communityExampleDesktop} />
        <img
          src={communityExampleDesktop}
          alt="Screenshot of the desktop view of the Pickup dashboard, showing stats and a cover picture for a beach community group"
        />
      </picture>
    </div>
  );
};

export default Features;
