import LineChart from "../../containers/LineChart";

const CommunityStats = ({ community, kFormatter }) => {
  // const checkData = () => {
  //   if (community.stats != undefined) {
  //     console.log(community.stats.litter);

  //     Object.entries(community.stats.litter.monthly_data).forEach(
  //       ([key, value]) => {
  //         console.log(`${key} ${value}`);
  //       }
  //     );
  //   }
  // };

  // const dataMonths = [
  //   "01",
  //   "02",
  //   "03",
  //   "04",
  //   "05",
  //   "06",
  //   "07",
  //   "08",
  //   "09",
  //   "10",
  //   "11",
  //   "12",
  // ];
  // const dataLitter = {
  //   labels: dataMonths,
  //   datasets: [
  //     {
  //       label: "Total Litter Collected",
  //       data: dataMonths.forEach((month) => {
  //         if (community.stats != undefined) {
  //           return community.stats.litter.monthly_data;
  //         }
  //       }),
  //       backgroundColor: ["#14532d"],
  //       borderWidth: 1,
  //     },
  //   ],
  // };

  return (
    <div className="flex justify-center py-12 px-4">
      {/* <button onClick={checkData}>test</button> */}
      <ul className="flex max-w-7xl flex-1 flex-wrap gap-6">
        <li className="flex min-h-[176px] min-w-[280px] flex-1 justify-between gap-4 rounded-xl border border-slate-200 bg-white p-6 shadow-sm">
          <div className="flex flex-col gap-6">
            <h2 className="font-medium text-slate-900">Total litter picked</h2>
            <p className="text-3xl font-semibold text-slate-900 md:text-4xl">
              {community.stats != undefined
                ? kFormatter(community.stats.litter.total)
                : "Error"}
            </p>
          </div>
          {/* <LineChart data={dataLitter}/> */}
        </li>
        <li className="flex min-h-[176px] min-w-[280px] flex-1 justify-between gap-4 rounded-xl border border-slate-200 bg-white p-6 shadow-sm">
          <div className="flex flex-col gap-6">
            <h2 className="font-medium text-slate-900">Total events ran</h2>
            <p className="text-3xl font-semibold text-slate-900 md:text-4xl">
              {community.stats != undefined
                ? community.stats.events.total
                : "Error"}
            </p>
          </div>
          {/* <p className="mt-auto">graph here</p> */}
        </li>
        <li className="flex min-h-[176px] min-w-[280px] flex-1 justify-between gap-4 rounded-xl border border-slate-200 bg-white p-6 shadow-sm">
          <div className="flex flex-col gap-6">
            <h2 className="font-medium text-slate-900">Members</h2>
            <p className="text-3xl font-semibold text-slate-900 md:text-4xl">
              {community.stats != undefined
                ? community.stats.users.total
                : "Error"}
            </p>
          </div>
          {/* <p className="mt-auto">graph here</p> */}
        </li>
      </ul>
    </div>
  );
};

export default CommunityStats;
