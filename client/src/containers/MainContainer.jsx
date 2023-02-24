import { Routes, Route } from "react-router-dom"
import Navbar from "../components/Navbar"
import LandingPage from "./LandingPage"
import CommunityPage from "./CommunityPage"
import CommunityEditPage from "./CommunityEditPage"
import EventPage from "./EventPage"
import Error from "./Error"

const MainContainer = () => {
  return (
    <>
        <Navbar />
        <Routes>
            <Route path="/" element={ <LandingPage /> } />
            <Route path="/communities/:id" element={ <CommunityPage /> } />
            <Route path="/communities/:id/edit" element={ <CommunityEditPage /> } />
            <Route path="/events/:id" element={ <EventPage /> } />
            <Route path="*" element={ <Error /> } />
        </Routes>
    </>
  )
}

export default MainContainer