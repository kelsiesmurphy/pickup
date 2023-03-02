import { useState } from "react";
import CommunityStats from "../components/dashboard/CommunityStats";
import SocialHeader from "../components/dashboard/SocialHeader";
import Leaderboard from "../components/dashboard/Leaderboard";
import UpcomingEvents from "../components/dashboard/UpcomingEvents";
import PastEvents from "../components/dashboard/PastEvents";
import Footer from "../components/Footer";
import { useParams } from "react-router-dom";
import { useEffect } from "react";
// import {communityHandlers} from "../handlers/communityHandler";


const CommunityPage = () => {
  const {id} = useParams();
  const [community, setCommunity] = useState({});
  const [upcomingEvents, setUpcomingEvents] = useState([]);
  const [communityStats, setCommunityStats] = useState([]);
  const [pastEvents, setPastEvents] = useState([]);
  const [communityMembers, setCommunityMembers] = useState([]);

  
  const [urlId, setUrlId] = useState(id);


  useEffect(() => {
    const findCommunity = (urlId) => {
      const request = new Request();
      request.get(`/api/communities/` + urlId)
      .then(result => setCommunity(result))
    };
  })

  // Temporary Community object for testing, until backend is hooked up.
  // const community = {
  //     id: 1,
  //     name: "30th Glasgow Scout Group",
  //     description:
  //       "Each month we do a cleanup of our local beach. All equipment provided by the Western Rotary Club.",
  //     is_private: false,
  //     hero_img_link:
  //       "https://pbs.twimg.com/profile_banners/523418837/1664179789/1500x500",
  //     logo_img_link:
  //       "https://upload.wikimedia.org/wikipedia/en/thumb/8/87/World_Scout_Emblem_1955.svg/1200px-World_Scout_Emblem_1955.svg.png",
  //   };

  return (
    <div className="bg-slate-100">
      <SocialHeader community={community} />
      <CommunityStats communityStats={communityStats} />
      <UpcomingEvents upcomingEvents={upcomingEvents} />
      <PastEvents pastEvents={pastEvents} />
      <Leaderboard communityMembers={communityMembers} />
      <Footer />
    </div>
  );
};

export default CommunityPage;
