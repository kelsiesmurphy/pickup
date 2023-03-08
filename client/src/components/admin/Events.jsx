import { Edit3 } from "react-feather";
import AddEventButton from "../AddEventButton";
import DeleteItem from "../DeleteItem";
import EventHandlers from "../../handlers/EventHandlers";

const Events = ({ communityEvents, setCommunityEvents }) => {
  const removeItem = (event) => {
    const eventToUpdate = {
      id: event.id,
      is_active: false,
    };
    const eventHandler = new EventHandlers();
    eventHandler.handleUpdateEvent(eventToUpdate);
    setCommunityEvents(communityEvents)
  };

  const eventNodes = communityEvents.map((event, index) => {
    return (
      <tr
        key={index}
        className="flex flex-1 items-center justify-between gap-1 border-b py-3 odd:bg-slate-50"
      >
        <td className="flex max-w-[412px] flex-1 items-center gap-3 px-6">
          <img
            src={event.img_before_link}
            className="aspect-square w-10 rounded-lg object-cover"
          />
          <p className="text-sm font-medium text-slate-900">{event.name}</p>
        </td>
        <td className="hidden max-w-[200px] flex-1 px-6 md:block">
          <p className="text-sm text-slate-500">
            {event.event_date_time_start}
          </p>
        </td>
        <td className="hidden max-w-[200px] flex-1 px-6  md:block">
          <p className="text-sm text-slate-500">{event.event_date_time_end}</p>
        </td>
        <td className="hidden max-w-[168px] flex-1 px-6 md:block">
          <p className="text-sm text-slate-500">{event.stats != undefined
                ? event.stats.users.total
                : "Error"}</p>
        </td>
        <td className="hidden max-w-[168px]  flex-1 px-6 md:block">
          <p className="text-sm text-slate-500">{event.stats != undefined
                ? event.stats.litter.total
                : "Error"}</p>
        </td>
        <td className="flex max-w-[116px] flex-1 justify-center gap-4 px-6">
          {/* <Edit3
            size={20}
            className="cursor-pointer text-slate-500 transition-colors hover:text-slate-900"
          /> */}
          <DeleteItem itemToRemove={event.name} removeItem={() => removeItem(event)} />
        </td>
      </tr>
    );
  });

  return (
    <div className="mt-4 flex flex-1 flex-col border-y border-slate-300 bg-white pb-12 shadow-sm md:mx-4 md:rounded-xl md:border-x">
      <div className="flex flex-col flex-wrap justify-between gap-6 py-6 px-6  md:flex-row">
        <div>
          <h3 className="text-lg font-medium text-slate-900">
            Community Events
          </h3>
          <p className="text-sm text-slate-500">
            Manage your community events here.
          </p>
        </div>
        <AddEventButton />
      </div>
      <table>
        <thead className="text-left">
          <tr className="flex flex-1 justify-between gap-1 border-y py-3">
            <th className="max-w-[412px] flex-1 px-6">
              <h4 className="text-xs font-medium text-slate-500">Name</h4>
            </th>
            <th className="hidden max-w-[200px] flex-1 px-6 md:block">
              <h4 className="text-xs font-medium text-slate-500">
                Start of event
              </h4>
            </th>
            <th className="hidden max-w-[200px] flex-1 px-6 md:block">
              <h4 className="text-xs font-medium text-slate-500">
                End of event
              </h4>
            </th>
            <th className="hidden max-w-[168px] flex-1 px-6 md:block">
              <h4 className="text-xs font-medium text-slate-500">
                Total attendees
              </h4>
            </th>
            <th className="hidden max-w-[168px] flex-1 px-6 md:block">
              <h4 className="text-xs font-medium text-slate-500">
                Total litter gathered
              </h4>
            </th>
            <th className="max-w-[116px] flex-1 px-6" />
          </tr>
        </thead>
        <tbody>{eventNodes}</tbody>
      </table>
    </div>
  );
};

export default Events;
