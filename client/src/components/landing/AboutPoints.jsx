import { Sun } from "react-feather";
import { AboutPointsConstant } from "../../constants/constants";

const AboutPoints = () => {
  const aboutNodes = AboutPointsConstant.map((aboutPoint, index) => {
    return (
      <li key={index} className="flex max-w-sm flex-col items-center gap-2">
        <div className="mb-5 rounded-full border-8 border-green-50 bg-green-100 p-2.5">
          <aboutPoint.icon size={24} color="#166534" />
        </div>
        <h3 className="text-lg font-semibold text-slate-900 md:text-xl">
          {aboutPoint.title}
        </h3>
        <p className="text-slate-500">{aboutPoint.description}</p>
      </li>
    );
  });

  return (
    <div className="flex basis-full justify-center bg-slate-50 py-24 px-4">
      <ul className="flex max-w-7xl flex-1 flex-wrap justify-center gap-8 text-center">
        {aboutNodes}
      </ul>
    </div>
  );
};

export default AboutPoints;
