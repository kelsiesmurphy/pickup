import { useAuth0 } from "@auth0/auth0-react";
import { Link } from "react-router-dom";
import { HeroConstant } from "../../constants/constants";
import SignupButton from "../SignupButton";

const Hero = () => {
  const { isAuthenticated } = useAuth0();

  return (
    <header className="flex justify-center border-t py-24">
      <div className="flex flex-col gap-16">
        <div className="mx-4 flex flex-col gap-12">
          <div className="max-w-3xl space-y-6">
            <h1 className="text-4xl font-semibold text-slate-900 transition-all md:text-6xl">
              {HeroConstant.main_heading}
            </h1>
            <p className="text-lg text-slate-500 md:text-xl">
              {HeroConstant.sub_heading}
            </p>
          </div>
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
        </div>
        <img
          src={HeroConstant.hero_image}
          width="1248"
          className="aspect-[375/272] object-cover md:aspect-[1216/480] xl:rounded-2xl"
        />
      </div>
    </header>
  );
};

export default Hero;
