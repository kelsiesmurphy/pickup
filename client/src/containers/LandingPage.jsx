import { useState, useEffect } from "react";
import Footer from "../components/Footer";
import AboutPoints from "../components/landing/AboutPoints";
import Features from "../components/landing/Features";
import Hero from "../components/landing/Hero";
import Newsletter from "../components/landing/Newsletter";
import Statistics from "../components/landing/Statistics";
import HomeHandler from "../handlers/HomeHandler";

const Landing = ({ kFormatter }) => {
  const [homeStats, setHomeStats] = useState({});

  useEffect(() => {
    const homeHandler = new HomeHandler();
    homeHandler.getHomeStats().then((result) => setHomeStats(result));
  }, []);

  return (
    <div className="bg-white">
      <Hero />
      <AboutPoints />
      <Statistics homeStats={homeStats} kFormatter={kFormatter} />
      <Features />
      <Newsletter />
      <Footer />
    </div>
  );
};

export default Landing;
