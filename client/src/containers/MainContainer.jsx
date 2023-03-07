import { useState } from "react";
import { Routes, Route } from "react-router-dom";
import { useAuth0 } from "@auth0/auth0-react";
import Navbar from "../components/Navbar";
import LandingPage from "./LandingPage";
import CommunityPage from "./CommunityPage";
import CommunitiesList from "./CommunitiesList";
import EventPage from "./EventPage";
import Onboarding from "./Onboarding";
import Error from "./Error";
import Admin from "./Admin";
import Mobile from "./Mobile";

const MainContainer = () => {
  const { isAuthenticated } = useAuth0();
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
        <Route path="/" element={<LandingPage kFormatter={kFormatter} />} />
        <Route path="/communities" element={<CommunitiesList />} />
        <Route path="/onboarding" element={<Onboarding />} />
        <Route
          path="/communities/:id"
          element={<CommunityPage kFormatter={kFormatter} />}
        />
        <Route path="/events/:id" element={<EventPage />} />
        <Route path="/admin" element={<Admin />} />
        <Route path="/add/:id" element={<Mobile />} />
        <Route path="*" element={<Error />} />
      </Routes>
    </div>
  );
};

export default MainContainer;
