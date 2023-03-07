import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { useAuth0 } from "@auth0/auth0-react";
import Footer from "../components/Footer";
import CommunityHandler from "../handlers/CommunityHandler";

const CommunitiesList = () => {
  const { user } = useAuth0();

  const [communities, setCommunities] = useState([]);
  const [communityId, setCommunityId] = useState();

  useEffect(() => {
    const communityHandler = new CommunityHandler();
    communityHandler.getCommunities().then((result) => setCommunities(result));
  }, []);

  const handleSubmit = () => {
    const userContext = {
      community_id: communityId,
      user_name: user.nickname,
      email: user.email,
      img_profile_link: user.picture,
      create_date: new Date().toJSON().slice(0, 10),
      auth0id: user.sub,
    };
    fetch(`/api/user-context`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(userContext),
    })
      .then((res) => res.json())
      .then((data) => {
        window.location = "/";
      });
  };

  const communityNodes = communities.map((community, index) => {
    return (
      <li
        key={index}
        className={`flex-1 cursor-pointer rounded-2xl border shadow-sm transition-all duration-300 ${community.id === communityId ? "bg-slate-200" : "bg-white"}`}
      >
        <div
          onClick={() => setCommunityId(community.id)}
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
        </div>
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
        <button
          onClick={handleSubmit}
          className="flex flex-1 items-center justify-center gap-2 rounded-lg border border-green-800 bg-green-800 py-2.5 px-4 text-white shadow-sm outline-slate-900 transition-colors hover:border-green-900 hover:bg-green-900 md:flex-none"
        >
          Complete onboarding
        </button>
      </div>
      <Footer />
    </>
  );
};

export default CommunitiesList;
