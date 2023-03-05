import { Edit3 } from "react-feather";
import AddUserButton from "../AddMemberButton";
import DeleteItem from "../DeleteItem";
import UserHandlers from "../../handlers/userHandlers"

const Members = ({ communityMembers }) => {

  const removeItem = () => {
    // Todo
  };

  const memberNodes = communityMembers.map((member, index) => {
    return (
      <tr
        key={index}
        className="flex flex-1 items-center justify-between gap-1 border-b py-3 odd:bg-slate-50"
      >
        <td className="flex max-w-[412px] flex-1 items-center gap-3 px-6">
          <img
            src={member.img_profile_link}
            className="aspect-square w-10 rounded-full object-cover"
          />
          <p className="text-sm font-medium text-slate-900">
            {member.user_name}
          </p>
        </td>
        <td className="hidden max-w-[268px] flex-1 px-6 md:block">
          <p className="text-sm text-slate-500">
            <a className="hover:underline" href={`mailto:${member.email}`}>
              {member.email}
            </a>
          </p>
        </td>
        <td className="hidden max-w-[220px] flex-1 px-6 md:block">
          <p className="text-sm text-slate-500">101</p>
        </td>
        <td className="hidden max-w-[168px] flex-1 px-6 md:block">
          <p className="text-sm text-slate-500">102</p>
        </td>
        <td className="hidden max-w-[120px]  flex-1 px-6 md:block">
          <p className="text-sm text-slate-500">{member.is_active}</p>
        </td>
        <td className="flex max-w-[116px] flex-1 justify-center gap-4 px-6">
          <Edit3 color="#64748b" size={20} />
          <DeleteItem itemToRemove={member.user_name} removeItem={removeItem} />
        </td>
      </tr>
    );
  });

  return (
    <div className="mt-4 flex flex-1 flex-col border-y border-slate-300 bg-white pb-12 shadow-sm md:mx-4 md:rounded-xl md:border-x">
      <div className="flex flex-col flex-wrap justify-between gap-6 py-6 px-6  md:flex-row">
        <div>
          <h3 className="text-lg font-medium text-slate-900">
            Community members
          </h3>
          <p className="text-sm text-slate-500">
            Manage your community members here.
          </p>
        </div>
        <AddUserButton />
      </div>
      <table>
        <thead className="text-left">
          <tr className="flex flex-1 justify-between gap-1 border-y py-3">
            <th className="max-w-[412px] flex-1 px-6">
              <h4 className="text-xs font-medium text-slate-500">Name</h4>
            </th>
            <th className="hidden max-w-[268px] flex-1 px-6 md:block">
              <h4 className="text-xs font-medium text-slate-500">
                Email address
              </h4>
            </th>
            <th className="hidden max-w-[220px] flex-1 px-6 md:block">
              <h4 className="text-xs font-medium text-slate-500">
                Litter gathered this month
              </h4>
            </th>
            <th className="hidden max-w-[168px] flex-1 px-6 md:block">
              <h4 className="text-xs font-medium text-slate-500">
                Litter gathered total
              </h4>
            </th>
            <th className="hidden max-w-[120px]  flex-1 px-6 md:block">
              <h4 className="text-xs font-medium text-slate-500">Status</h4>
            </th>
            <th className="max-w-[116px] flex-1 px-6" />
          </tr>
        </thead>
        <tbody>{memberNodes}</tbody>
      </table>
    </div>
  );
};

export default Members;
