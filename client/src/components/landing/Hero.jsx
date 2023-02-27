import { HeroConstant } from "../../constants/constants";
import SignupButton from "../SignupButton";

const Hero = () => {
  return (
    <header className="flex justify-center border-t py-24">
      <div className="flex flex-col gap-16">
        <div className="mx-4 flex flex-col gap-12">
          <div className="max-w-3xl space-y-6">
            <h1 className="text-4xl font-semibold text-slate-900 transition-all md:text-6xl">
              {HeroConstant.main_heading}
            </h1>
            <p className="text-lg text-slate-500 md:text-xl">
              {HeroConstant.sub_heading}
            </p>
          </div>
          <SignupButton buttonText={"Get started"} />
        </div>
        <img
          src={HeroConstant.hero_image}
          width="1248"
          className="aspect-[375/272] object-cover md:aspect-[1216/480] xl:rounded-2xl"
        />
      </div>
    </header>
  );
};

export default Hero;
