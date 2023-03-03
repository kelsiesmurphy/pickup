import { useState, useEffect } from "react";
import Footer from "../components/Footer";
import AboutPoints from "../components/landing/AboutPoints";
import Features from "../components/landing/Features";
import Hero from "../components/landing/Hero";
import Newsletter from "../components/landing/Newsletter";
import Statistics from "../components/landing/Statistics";
import HomeHandler from "../handlers/homeHandler";

const Landing = () => {
  const [homeStats, setHomeStats] = useState({});

  useEffect(() => {
    const homeHandler = new HomeHandler();
    homeHandler.getHomeStats()
  }, []);

  return (
    <div className="bg-white">
      <Hero />
      <AboutPoints />
      <Statistics homeStats={homeStats}/>
      <Features />
      <Newsletter />
      <Footer />
    </div>
  );
};

export default Landing;
