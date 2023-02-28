const Leaderboard = ({ communityMembers }) => {
  const memberNodes = communityMembers.splice(0, 2).map((member) => {
    return (
      <li>
        <div className="flex flex-col items-center">
          <img className="aspect-square w-12 rounded-full" />
          <div className="w-[2px] rounded-sm bg-slate-400" />
        </div>
        <h3 className="text-sm font-medium text-slate-800">{member.name}</h3>
        <h3 className="text-sm text-slate-500">
          Picked up
          <span className="font-medium text-green-900"> {member.name} </span>
          items
        </h3>
      </li>
    );
  });

  return (
    <div className="flex justify-center py-12 px-4">
      <div className="max-w-7xl flex-1">
        <h2 className="text-2xl font-medium text-slate-900">Leaderboard</h2>
        <ul className="flex max-w-7xl flex-1 flex-wrap gap-8">{memberNodes}</ul>
      </div>
    </div>
  );
};

export default Leaderboard;
