import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import CommunityStats from "../components/dashboard/CommunityStats";
import SocialHeader from "../components/dashboard/SocialHeader";
import Leaderboard from "../components/dashboard/Leaderboard";
import UpcomingEvents from "../components/dashboard/UpcomingEvents";
import PastEvents from "../components/dashboard/PastEvents";
import Footer from "../components/Footer";
import CommunityHandler from "../handlers/CommunityHandler";
import UserHandlers from "../handlers/UserHandlers";
import EventHandlers from "../handlers/EventHandlers";

const CommunityPage = ({ kFormatter }) => {
  const { id } = useParams();

  const [community, setCommunity] = useState({});
  const [communityMembers, setCommunityMembers] = useState([]);
  const [upcomingEvents, setUpcomingEvents] = useState([]);
  const [communityStats, setCommunityStats] = useState([]);
  const [pastEvents, setPastEvents] = useState([]);

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

    eventHandler.getEvents(urlId).then((result) => setUpcomingEvents(result));
  }, [urlId]);

  return (
    <>
      <SocialHeader community={community} />
      <CommunityStats community={community} kFormatter={kFormatter} />
      <UpcomingEvents upcomingEvents={upcomingEvents} />
      <PastEvents pastEvents={pastEvents} />
      <Leaderboard communityMembers={communityMembers} />
      <Footer />
    </>
  );
};

export default CommunityPage;
