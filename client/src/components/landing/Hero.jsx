import { HeroConstant } from "../../constants/constants";
import LoginButton from "../LoginButton";

const Hero = () => {
  return (
    <header className="flex justify-center py-24 border-t">
      <div className="flex flex-col gap-16">
        <div className="mx-4 flex flex-col gap-12">
          <div className="space-y-6 max-w-3xl">
            <h1 className="text-4xl font-semibold text-slate-900 md:text-6xl transition-all">
              {HeroConstant.main_heading}
            </h1>
            <p className="text-lg md:text-xl text-slate-500">{HeroConstant.sub_heading}</p>
          </div>
          <LoginButton buttonText={"Get started"} />
        </div>
        <img
          src={HeroConstant.hero_image}
          width="1248"
          className="aspect-[375/272] md:aspect-[1216/480] object-cover xl:rounded-2xl"
        />
      </div>
    </header>
  );
};

export default Hero;
