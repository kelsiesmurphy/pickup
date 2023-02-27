import { Routes, Route } from "react-router-dom";
import Navbar from "../components/Navbar";
import LandingPage from "./LandingPage";
import CommunityPage from "./CommunityPage";
import CommunityEditPage from "./CommunityEditPage";
import EventPage from "./EventPage";
import Error from "./Error";

const MainContainer = () => {
  // Temporary Community object for testing, until backend is hooked up.
  const community = {
    id: 1,
    name: "30th Glasgow Scout Group",
    description:
      "Each month we do a cleanup of our local beach. All equipment provided by the Western Rotary Club.",
    is_private: false,
    hero_img_link:
      "https://pbs.twimg.com/profile_banners/523418837/1664179789/1500x500",
    logo_img_link:
      "https://upload.wikimedia.org/wikipedia/en/thumb/8/87/World_Scout_Emblem_1955.svg/1200px-World_Scout_Emblem_1955.svg.png",
  };

  return (
    <div className="flex flex-col basis-full min-h-screen">
      <Navbar community={community} />
      <Routes>
        <Route path="/" element={<LandingPage />} />
        <Route path="/communities/:id" element={<CommunityPage />} />
        <Route path="/communities/:id/edit" element={<CommunityEditPage />} />
        <Route path="/events/:id" element={<EventPage />} />
        {/* <Route path="*" element={<Error />} /> */}
      </Routes>
    </div>
  );
};

export default MainContainer;
