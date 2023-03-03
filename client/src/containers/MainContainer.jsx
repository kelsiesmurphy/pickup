import { useState } from "react";
import { Routes, Route } from "react-router-dom";
import Navbar from "../components/Navbar";
import LandingPage from "./LandingPage";
import CommunityPage from "./CommunityPage";
import CommunityEditPage from "./CommunityEditPage";
import EventPage from "./EventPage";
import Error from "./Error";

import CommunityHandler from "../handlers/communityHandler";
import { useEffect } from "react";

const MainContainer = () => {
  const [tempCommunity, setTempCommunity] = useState({});

  useEffect(() => {
    const communityHandler = new CommunityHandler();
    communityHandler.findCommunity(0).then((result) =>
      setTempCommunity(result)
    );
  }, []);

  return (
    <div className="flex min-h-screen basis-full flex-col">
      <Navbar community={tempCommunity} />
      <Routes>
        <Route path="/" element={<LandingPage />} />
        <Route path="/communities/:id" element={<CommunityPage />} />
        <Route path="/communities/:id/edit" element={<CommunityEditPage />} />
        <Route path="/events/:id" element={<EventPage />} />
        <Route path="*" element={<Error />} />
      </Routes>
    </div>
  );
};

export default MainContainer;
