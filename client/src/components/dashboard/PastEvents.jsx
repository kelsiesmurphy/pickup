const PastEvents = ({ pastEvents }) => {
    const eventNodes = pastEvents.splice(0, 2).map((event) => {
      return (
        <li className="rounded-2xl border shadow-sm">
          <img src={event.after_img_link} />
          <div className="px-6 py-8">
            <h3 className="text-xl md:text-2xl text-slate-900 font-semibold">{event.name}</h3>
            <p className="text-slate-600">{event.descripion}</p>
          </div>
        </li>
      )
    })
  
    return (
      <div className="py-12 px-4 flex justify-center">
        <div className="flex-1 max-w-7xl">
          <h2 className="text-slate-900 text-2xl font-medium">Past events</h2>
          <ul className="flex flex-wrap gap-8 flex-1 max-w-7xl">
            {eventNodes}
          </ul>
        </div>
      </div>
    )
}

export default PastEvents