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
  const [communityId, setTempId] = useState(1)

  return (
    <div className="flex min-h-screen basis-full flex-col">
      <Navbar communityId={communityId} />
      <Routes>
        <Route path="/" element={<LandingPage />} />
        <Route path="/communities" element={<CommunitiesList />} />
        <Route path="/communities/:id" element={<CommunityPage />} />
        <Route path="/communities/:id/edit" element={<CommunityEditPage />} />
        <Route path="/events/:id" element={<EventPage />} />
        <Route path="/admin" element={<Admin />} />
        <Route path="*" element={<Error />} />
      </Routes>
    </div>
  );
};

export default MainContainer;
