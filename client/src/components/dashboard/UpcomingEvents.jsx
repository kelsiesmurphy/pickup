import { Link } from "react-router-dom";

const UpcomingEvents = ({ upcomingEvents }) => {
  const eventNodes = upcomingEvents.splice(0, 2).map((event, index) => {
    return (
      <li
        key={index}
        className="min-w-[280px] flex-1 rounded-2xl border bg-white shadow-sm transition-shadow duration-300 hover:shadow-md"
      >
        <Link to={`/events/${event.id}`}>
          <img
            src={event.img_before_link}
            className="aspect-[592/192] rounded-t-2xl object-cover"
          />
          <div className="space-y-2 px-6 py-6">
            <h3 className="text-xl font-semibold text-slate-900 md:text-2xl">
              {event.name}
            </h3>
            <p className="text-slate-600">{event.description}</p>
          </div>
        </Link>
      </li>
    );
  });

  return (
    <div className="flex justify-center py-12 px-4">
      <div className="max-w-7xl flex-1 space-y-6">
        <h2 className="text-2xl font-medium text-slate-900">Upcoming events</h2>
        <ul className="flex flex-1 flex-wrap justify-between gap-8">
          {eventNodes}
        </ul>
      </div>
    </div>
  );
};

export default UpcomingEvents;
