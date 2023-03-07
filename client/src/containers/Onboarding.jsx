import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import Footer from "../components/Footer";
import CommunityHandler from "../handlers/CommunityHandler";

const CommunitiesList = () => {
  const [communities, setCommunities] = useState([]);

  useEffect(() => {
    const communityHandler = new CommunityHandler();
    communityHandler.getCommunities().then((result) => setCommunities(result));
  }, []);

  const communityNodes = communities.map((community, index) => {
    return (
      <li key={index} className="flex-1 rounded-2xl border bg-white shadow-sm hover:shadow-md transition-shadow duration-300">
        <Link
          to={`/communities/${community.id}`}
          className="flex flex-wrap gap-4 p-6"
        >
          <img
            src={community.img_logo_link}
            className="aspect-square rounded-lg object-cover"
            width="120"
            height="120"
          />
          <div className="flex-1 space-y-2">
            <h3 className="text-xl font-semibold text-slate-900 md:text-2xl">
              {community.name}
            </h3>
            <p className="text-slate-600">{community.description}</p>
          </div>
        </Link>
      </li>
    );
  });

  return (
    <>
      <div className="flex flex-col items-center gap-5 px-4 pt-16 pb-16 text-center md:pt-24 md:pb-0 lg:px-32">
        <h2 className="text-4xl font-semibold text-slate-900">
          Join a community
        </h2>
        <p className="text-lg text-slate-500">
          To finish signing up to Pickup, please choose a community to join.
        </p>
        <ul className="mt-8 flex max-w-2xl flex-col gap-5 text-left">
          {communityNodes}
        </ul>
      </div>
      <Footer />
    </>
  );
};

export default CommunitiesList;
