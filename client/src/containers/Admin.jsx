import { useState, useEffect } from "react";
import CommunityDetails from "../components/admin/communityDetails";
import Members from "../components/admin/Members";
import Events from "../components/admin/Events";
import CommunityHandler from "../handlers/communityHandler";
import UserHandlers from "../handlers/userHandlers";

const Admin = () => {
  const [community, setCommunity] = useState({});
  const [communityMembers, setCommunityMembers] = useState([]);
  
  const [currentPage, setCurrentPage] = useState("Community details");

  useEffect(() => {
    const communityHandler = new CommunityHandler();
    const userHandler = new UserHandlers();

    communityHandler.findCommunity(1).then((result) => setCommunity(result));

    userHandler
      .findUsersFromCommunity(1)
      .then((result) => setCommunityMembers(result));
  }, []);




  const adminPages = ["Community details", "Members", "Events"];

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
      <div className="flex flex-col flex-1 justify-between gap-6 py-4 pb-24 pt-12 transition-all lg:px-28">
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
          {currentPage === "Community details" && <CommunityDetails />}
          {currentPage === "Members" && <Members communityMembers={communityMembers}/>}
          {currentPage === "Events" && <Events />}
        </div>
      </div>
    </div>
  );
};

export default Admin;
