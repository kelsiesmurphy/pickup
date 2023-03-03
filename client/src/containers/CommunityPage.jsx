import { useState } from "react";
import CommunityStats from "../components/dashboard/CommunityStats";
import SocialHeader from "../components/dashboard/SocialHeader";
import Leaderboard from "../components/dashboard/Leaderboard";
import UpcomingEvents from "../components/dashboard/UpcomingEvents";
import PastEvents from "../components/dashboard/PastEvents";
import Footer from "../components/Footer";
import { useParams } from "react-router-dom";
import { useEffect } from "react";
import CommunityHandler from "../handlers/communityHandler";
import UserHandlers from "../handlers/userHandlers";
import EventHandlers from "../handlers/eventHandlers";

const CommunityPage = () => {
  const { id } = useParams();

  const [community, setCommunity] = useState({});
  const [upcomingEvents, setUpcomingEvents] = useState([]);
  const [communityStats, setCommunityStats] = useState([]);
  const [pastEvents, setPastEvents] = useState([]);
  const [communityMembers, setCommunityMembers] = useState([]);

  const [urlId, setUrlId] = useState(id);

  useEffect(() => {
    const communityHandler = new CommunityHandler();
    const userHandler = new UserHandlers();
    const eventHandler = new EventHandlers();

    communityHandler
      .findCommunity(urlId)
      .then((result) => setCommunity(result));

    userHandler
      .findUsersFromCommunity(urlId)
      .then((result) => setCommunityMembers(result));

    eventHandler
      .getEvents(urlId)
      .then((result) => setUpcomingEvents(result))
  }, [urlId]);

  return (
    <div className="bg-slate-100">
      <SocialHeader community={community} />
      <CommunityStats community={community} />
      <UpcomingEvents upcomingEvents={upcomingEvents} />
      <PastEvents pastEvents={pastEvents} />
      <Leaderboard communityMembers={communityMembers} />
      <Footer />
    </div>
  );
};

export default CommunityPage;
