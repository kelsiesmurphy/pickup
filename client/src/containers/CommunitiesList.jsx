import { useEffect } from "react";
import { useState } from "react";
import { Link } from "react-router-dom";
import Footer from "../components/Footer";
import CommunityHandler from "../handlers/communityHandler";

const CommunitiesList = () => {
  const [communities, setCommunities] = useState([]);

  useEffect(() => {
    const communityHandler = new CommunityHandler();
    communityHandler.getCommunities().then((result) => setCommunities(result));
  }, []);

  const communityNodes = communities.map((community, index) => {
    return (
      <li key={index} className="flex-1 rounded-2xl border bg-white shadow-sm">
        <Link to={`/communities/${community.id}`} className="flex flex-wrap gap-4 p-6">
          <img
            src={community.img_logo_link}
            className="aspect-square object-cover rounded-lg"
            width="120"
            height="120"
          />
          <div className="space-y-2 flex-1">
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
    <div className="bg-slate-50">
      <div className="flex min-h-screen flex-col items-center gap-5 px-4 pt-16 pb-16 text-center md:pt-24 md:pb-0 lg:px-32">
        <h2 className="text-4xl font-semibold text-slate-900">
          View all communities
        </h2>
        <p className="text-lg text-slate-500">
          Find a community you like, and see what events they are running!
        </p>
        <ul className="flex flex-col gap-5 text-left max-w-2xl mt-8">{communityNodes}</ul>
      </div>

      <Footer />
    </div>
  );
};

export default CommunitiesList;
