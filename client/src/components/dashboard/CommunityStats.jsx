const CommunityStats = ({ community, kFormatter }) => {
  

  return (
    <div className="flex justify-center py-12 px-4">
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
          <p className="mt-auto">graph here</p>
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
          <p className="mt-auto">graph here</p>
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
          <p className="mt-auto">graph here</p>
        </li>
      </ul>
    </div>
  );
};

export default CommunityStats;
