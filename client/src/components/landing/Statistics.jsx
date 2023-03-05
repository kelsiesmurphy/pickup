const Statistics = ({ homeStats, kFormatter }) => {
  return (
    <div className="flex flex-col gap-5 py-24 px-4 text-center lg:px-32">
      <h2 className="text-4xl font-semibold text-slate-900">
        We've been growing!
      </h2>
      <p className="text-lg text-slate-500">
        Since we launched in February 2023, we've been helping groups clean up
        their communities!
      </p>
      <ul className="mt-12 flex flex-wrap justify-around gap-8 rounded-lg bg-green-50 p-16">
        <li className="flex flex-col items-center gap-2">
          <h3 className="text-4xl font-semibold text-green-900 md:text-6xl">
            {String(homeStats.total_events_completed)}
          </h3>
          <p className="text-green-800">Events completed</p>
        </li>
        <li className="flex flex-col items-center gap-2">
          <h3 className="text-4xl font-semibold text-green-900 md:text-6xl">
            {String(kFormatter(homeStats.total_litter_collected))}
          </h3>
          <p className="text-green-800">Litter cleaned up</p>
        </li>
        <li className="flex flex-col items-center gap-2">
          <h3 className="text-4xl font-semibold text-green-900 md:text-6xl">
            {String(homeStats.total_users)}
          </h3>
          <p className="text-green-800">Global users</p>
        </li>
      </ul>
    </div>
  );
};

export default Statistics;
