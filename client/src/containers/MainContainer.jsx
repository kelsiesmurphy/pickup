import { useState } from "react";
import { Routes, Route, useNavigate } from "react-router-dom";
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
import { useEffect } from "react";
import FormHeader from "../components/FormHeader";

const MainContainer = () => {
  const { isAuthenticated, user, isLoading } = useAuth0();
  const [loggedInUserData, setLoggedInUserData] = useState({});
  const [showBanner, setShowBanner] = useState(true);

  const navigate = useNavigate();

  useEffect(() => {
    async function fetchData() {
      const response = await fetch(
        `/api/user-context?auth0Id=${user.sub.replace("|", "%7C")}`
      );
      if (response.status === 200) {
        const json = await response.json();
        setLoggedInUserData(json);
      } else {
        navigate("/onboarding");
      }
    }
    if (!isLoading && isAuthenticated) {
      fetchData();
    }
  }, [isAuthenticated]);

  const handleBanner =() => {
    setShowBanner(false)
  }

  function kFormatter(num) {
    return Math.abs(num) > 999
      ? Math.sign(num) * (Math.abs(num) / 1000).toFixed(1) + "k"
      : Math.sign(num) * Math.abs(num);
  }

  return (
    <div className="flex min-h-screen basis-full flex-col bg-slate-50">
      {showBanner === true && <FormHeader handleBanner={handleBanner}/>}
      <Navbar loggedInUserData={loggedInUserData} />
      <Routes>
        <Route path="/" element={<LandingPage kFormatter={kFormatter} />} />
        <Route path="/communities" element={<CommunitiesList />} />
        <Route path="/onboarding" element={<Onboarding />} />
        <Route
          path="/communities/:id"
          element={<CommunityPage kFormatter={kFormatter} />}
        />
        <Route path="/events/:id" element={<EventPage loggedInUserData={loggedInUserData}/>} />
        <Route path="/admin" element={<Admin loggedInUserData={loggedInUserData}/>} />
        <Route path="/add/:id" element={<Mobile loggedInUserData={loggedInUserData}/>} />
        <Route path="*" element={<Error />} />
      </Routes>
    </div>
  );
};

export default MainContainer;
