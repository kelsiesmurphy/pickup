import { StatisticsConstant } from "../../constants/constants";

const Statistics = () => {
  const aboutNodes = StatisticsConstant.map((statistic, index) => {
    return (
      <li key={index} className="flex flex-col items-center gap-2">
        <h3 className="text-4xl font-semibold text-green-900 md:text-6xl">
          {statistic.stat}
        </h3>
        <p className="text-green-800">{statistic.description}</p>
      </li>
    );
  });

  return (
    <div className="flex flex-col text-center gap-5 py-24 px-4 lg:px-32">
      <h2 className="text-slate-900 text-4xl font-semibold">We've been growing!</h2>
      <p className="text-lg text-slate-500">Since we launched in February 2023, we've been helping groups clean up their communities!</p>
      <ul className="mt-12 flex flex-wrap justify-around gap-8 p-16 bg-green-50 rounded-lg">
        {aboutNodes}
      </ul>
    </div>
  );
};

export default Statistics;
