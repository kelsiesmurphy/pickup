import { Routes, Route } from "react-router-dom";
import Navbar from "../components/Navbar";
import LandingPage from "./LandingPage";
import CommunityPage from "./CommunityPage";
import CommunityEditPage from "./CommunityEditPage";
import CommunitiesList from "./CommunitiesList";
import EventPage from "./EventPage";
import Error from "./Error";
import { useState } from "react";
import Admin from "./Admin";

const MainContainer = () => {
  const [communityId, setTempId] = useState(1);

  function kFormatter(num) {
    return Math.abs(num) > 999
      ? Math.sign(num) * (Math.abs(num) / 1000).toFixed(1) + "k"
      : Math.sign(num) * Math.abs(num);
  }

  return (
    <div className="flex min-h-screen basis-full flex-col bg-slate-50">
      <Navbar communityId={communityId} />
      <Routes>
        <Route path="/" element={<LandingPage kFormatter={kFormatter}/>} />
        <Route path="/communities" element={<CommunitiesList />} />
        <Route path="/communities/:id" element={<CommunityPage kFormatter={kFormatter}/>} />
        <Route path="/communities/:id/edit" element={<CommunityEditPage />} />
        <Route path="/events/:id" element={<EventPage />} />
        <Route path="/admin" element={<Admin />} />
        <Route path="*" element={<Error />} />
      </Routes>
    </div>
  );
};

export default MainContainer;
