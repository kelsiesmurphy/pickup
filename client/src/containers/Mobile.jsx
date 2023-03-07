import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import LitterHandlers from "../handlers/LitterHandlers";
import EventHandlers from "../handlers/EventHandlers";
import BarChart from "./BarChart";

const Mobile = () => {
  const { id } = useParams();
  const [urlId, setUrlId] = useState(id);

  const [event, setEvent] = useState({});
  const [litterTypes, setLitterTypes] = useState([]);
  const [litterCounts, setLitterCounts] = useState([]);
  const [submitResult, setSubmitResult] = useState({});

  const litterHandler = new LitterHandlers();

  useEffect(() => {
    const eventHandlers = new EventHandlers();
    eventHandlers.findEvent(urlId).then((result) => setEvent(result));
    litterHandler.getLitter().then((res) => setLitterTypes(res));
  }, []);

  const data = {
    labels: [
      "General",
      "Smoking",
      "Crisp Packets",
      "Plastic Bags",
      "Cans",
      "Packaging",
    ],
    datasets: [
      {
        label: "Litter collected",
        data: litterTypes.map((litterType, index) => {
          const stats = litterCounts.filter(
            (count) => count.litter_type_id === litterType.id
          );
          return stats.length;
        }),
        backgroundColor: [
          "#14532d",
          "#166534",
          "#15803d",
          "#16a34a",
          "#22c55e",
          "#4ade80",
        ],
        borderWidth: 1,
      },
    ],
  };

  const handleClick = (litterTypeId) => {
    const currentDate = new Date();
    const litterItem = {
      community_id: event.community_id,
      event_id: urlId,
      user_id: 1,
      litter_type_id: litterTypeId,
      collection_date_time: currentDate,
    };
    const litterCountsClone = [...litterCounts];
    litterCountsClone.push(litterItem);
    setLitterCounts(litterCountsClone);
  };

  const handleSubmit = () => {
    litterHandler
      .handleLitterRegister(litterCounts)
      .then((res) => res.json())
      .then((data) => {
        setSubmitResult(data);
        setLitterCounts([]);
      });
  };

  const litterTypeNodes = litterTypes.map((litterType, index) => {
    return (
      <li
        key={index}
        onClick={() => handleClick(litterType.id)}
        className="flex min-h-[116px] flex-1 cursor-pointer items-center justify-center rounded-xl border bg-white px-4 py-5 text-center text-2xl font-medium text-slate-900 shadow-sm transition-colors hover:bg-slate-100"
      >
        {litterType.litter_type_desc}
      </li>
    );
  });
  return (
    <div className="flex basis-full justify-center">
      <div className="flex flex-1 justify-center gap-6 py-4 px-4 pb-24 pt-12 transition-all lg:px-28">
        <div className="flex flex-col gap-6">
          <BarChart data={data} />
          <ul className="max-w-xl flex-1 columns-2 space-y-6">
            {litterTypeNodes}
          </ul>
          <button
            onClick={handleSubmit}
            className="flex flex-1 items-center justify-center gap-2 rounded-lg border border-green-800 bg-green-800 py-2.5 px-4 text-white shadow-sm outline-slate-900 transition-colors hover:border-green-900 hover:bg-green-900"
          >
            Submit
          </button>
        </div>
      </div>
    </div>
  );
};

export default Mobile;
