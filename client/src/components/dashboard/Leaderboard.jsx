import { Award } from "react-feather";

const Leaderboard = ({ communityMembers }) => {
  const memberNodes = communityMembers.map((member, index) => {
    return (
      <li key={index} className="flex items-center gap-5">
        <p className="w-[24px] text-lg text-slate-500">#{index + 1}</p>
        <div className="flex flex-col items-center">
          <img
            className="aspect-square w-12 rounded-full"
            src={member.img_profile_link}
          />
        </div>
        <div>
          <h3 className="text-sm font-medium text-slate-800">
            {member.user_name}
          </h3>
          <h3 className="text-sm text-slate-500">
            Picked up
            <span className="font-medium text-green-900"> {member.name} </span>
            items
          </h3>
        </div>
        {index === 0  ? <Award color="#D4AF37" /> : null || index === 1 ? <Award color="#94a3b8" /> : null || index === 2 ? <Award color="#CD7F32" /> : null}
      </li>
    );
  });

  return (
    <div className="flex justify-center py-12 px-4">
      <div className="max-w-7xl flex-1 space-y-8">
        <h2 className="border-b border-slate-300 pb-6 text-2xl font-medium text-slate-900">
          Leaderboard
        </h2>
        <div className="flex justify-center md:block">
          <ul className="columns-1 space-y-5 md:columns-2">{memberNodes}</ul>
        </div>
      </div>
    </div>
  );
};

export default Leaderboard;
