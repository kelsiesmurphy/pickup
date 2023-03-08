import { useState, useEffect } from "react";
import CommunityDetails from "../components/admin/CommunityDetails";
import Members from "../components/admin/Members";
import Events from "../components/admin/Events";
import CommunityHandler from "../handlers/CommunityHandler";
import UserHandlers from "../handlers/UserHandlers";
import EventHandlers from "../handlers/EventHandlers";

const Admin = ({ loggedInUserData }) => {
  const adminPages = ["Members", "Events", "Community details"];

  const [community, setCommunity] = useState({});
  const [communityMembers, setCommunityMembers] = useState([]);
  const [communityEvents, setCommunityEvents] = useState([]);
  const [currentPage, setCurrentPage] = useState("Members");

  useEffect(() => {
    const communityHandler = new CommunityHandler();
    const userHandler = new UserHandlers();
    const eventHandler = new EventHandlers();

    // The problem is the "1" params below. They should be "loggedInUserData.community_id", but the useEffect reads them as undefined, despite it being a dependency on the useEffect. I am displaying the community id on page (line 67), so it definitely comes through.
    if (loggedInUserData.community_id !== undefined) {
      communityHandler
        .findCommunity(loggedInUserData.community_id)
        .then((result) => setCommunity(result));

      userHandler
        .findUsersFromCommunity(loggedInUserData.community_id)
        .then((result) => setCommunityMembers(result));

      eventHandler
        .getEvents(loggedInUserData.community_id)
        .then((result) => setCommunityEvents(result));
    }
  }, [communityMembers, communityEvents, loggedInUserData]);

  const pageNavNodesDesktop = adminPages.map((pageNav, index) => {
    return (
      <li
        key={index}
        onClick={() => setCurrentPage(pageNav)}
        className={`hidden h-8 cursor-pointer hover:border-b-2 md:block  ${
          pageNav == currentPage
            ? "border-b-2 border-green-900 text-green-900"
            : "text-slate-500"
        }`}
      >
        <h2 className="text-sm font-medium">{pageNav}</h2>
      </li>
    );
  });

  const pageNavNodesMobile = adminPages.map((pageNav, index) => {
    return (
      <option key={index} className="md:hidden" value={pageNav}>
        {pageNav}
      </option>
    );
  });

  return (
    <div className="flex basis-full justify-center">
      <div className="flex flex-1 flex-col justify-between gap-6 py-4 pb-24 pt-12 transition-all lg:px-28">
        <div className="mx-4 flex flex-1 flex-col gap-6">
          <h1 className="text-2xl font-semibold text-slate-900 md:text-3xl">
            Settings
          </h1>
          <ul className="hidden flex-1 gap-4 md:flex md:border-b">
            {pageNavNodesDesktop}
          </ul>
          <select
            value={currentPage}
            onChange={(e) => setCurrentPage(e.target.value)}
            className="flex gap-4 rounded-lg border border-slate-300 py-3 px-3.5 shadow-sm outline-slate-900 placeholder:text-slate-500 md:hidden md:border-b"
          >
            {pageNavNodesMobile}
          </select>
        </div>
        <div className="flex flex-1 justify-center">
          {currentPage === "Community details" && (
            <CommunityDetails community={community} />
          )}
          {currentPage === "Members" && (
            <Members
              communityMembers={communityMembers}
              setCommunityMembers={setCommunityMembers}
            />
          )}
          {currentPage === "Events" && (
            <Events
              communityEvents={communityEvents}
              setCommunityEvents={setCommunityEvents}
            />
          )}
        </div>
      </div>
    </div>
  );
};

export default Admin;
