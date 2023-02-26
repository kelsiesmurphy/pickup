import Footer from "../components/Footer";
import AboutPoints from "../components/landing/AboutPoints";
import Features from "../components/landing/Features";
import Hero from "../components/landing/Hero";
import Newsletter from "../components/landing/Newsletter";
import Statistics from "../components/landing/Statistics";

const Landing = () => {
  return (
    <>
      <Hero />
      <AboutPoints />
      <Statistics />
      <Features />
      <Newsletter />
      <Footer />
    </>
  )
};

export default Landing;
