const CommunityStats = ({communityStats}) => {

  const totalLitterPicked = 2424
  const totalEventsRan = 10
  const members = 316


  return (
    <div className="py-12 px-4 flex justify-center">
      <ul className="flex flex-wrap gap-6 flex-1 max-w-7xl">
        <li className="bg-white flex-1 min-w-[280px] rounded-xl flex justify-between min-h-[176px] gap-4 shadow-sm border border-slate-200 p-6">
          <div className="flex flex-col gap-6">
            <h2 className="text-slate-900 font-medium">Total litter picked</h2>
            <p className="text-slate-900 font-semibold text-3xl md:text-4xl">{totalLitterPicked}</p>
          </div>
          <p className="mt-auto">graph here</p>
        </li>
        <li className="bg-white flex-1 min-w-[280px] rounded-xl flex justify-between min-h-[176px] gap-4 shadow-sm border border-slate-200 p-6">
          <div className="flex flex-col gap-6">
            <h2 className="text-slate-900 font-medium">Total events ran</h2>
            <p className="text-slate-900 font-semibold text-3xl md:text-4xl">{totalEventsRan}</p>
          </div>
          <p className="mt-auto">graph here</p>
        </li>
        <li className="bg-white flex-1 min-w-[280px] rounded-xl flex justify-between min-h-[176px] gap-4 shadow-sm border border-slate-200 p-6">
          <div className="flex flex-col gap-6">
            <h2 className="text-slate-900 font-medium">Members</h2>
            <p className="text-slate-900 font-semibold text-3xl md:text-4xl">{members}</p>
          </div>
          <p className="mt-auto">graph here</p>
        </li>
      </ul>
    </div>
  )
}

export default CommunityStats