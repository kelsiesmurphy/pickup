const PastEvents = ({ pastEvents }) => {
  const eventNodes = pastEvents.splice(0, 2).map((event) => {
    return (
      <li className="rounded-2xl border shadow-sm">
        <img src={event.after_img_link} />
        <div className="px-6 py-8">
          <h3 className="text-xl font-semibold text-slate-900 md:text-2xl">
            {event.name}
          </h3>
          <p className="text-slate-600">{event.descripion}</p>
        </div>
      </li>
    );
  });

  return (
    <div className="flex justify-center py-12 px-4">
      <div className="max-w-7xl flex-1">
        <h2 className="text-2xl font-medium text-slate-900">Past events</h2>
        <ul className="flex max-w-7xl flex-1 flex-wrap gap-8">{eventNodes}</ul>
      </div>
    </div>
  );
};

export default PastEvents;
