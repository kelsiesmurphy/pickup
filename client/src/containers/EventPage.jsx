import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { Copy, Twitter, Facebook, Linkedin } from "react-feather";
import EventHandlers from "../handlers/eventHandlers";
import Footer from "../components/Footer";
import { Link } from "react-router-dom";

const EventPage = () => {
  const { id } = useParams();
  const [urlId, setUrlId] = useState(id);
  const [event, setEvent] = useState({});
  const [copyLinkText, setCopyLinkText] = useState("Copy Link");

  useEffect(() => {
    const eventHandler = new EventHandlers();

    eventHandler.findEvent(urlId).then((result) => {
      setEvent(result);
    });
  }, [urlId]);

  // Change button text to show user the url has been copied
  const delay = (ms) => new Promise((res) => setTimeout(res, ms));
  const changeText = async () => {
    setCopyLinkText("Copied!");
    await delay(1000);
    setCopyLinkText("Copy Link");
  };

  const handleCopyLink = () => {
    if (navigator.clipboard) {
      changeText();
      navigator.clipboard.writeText(window.location.href);
    } else {
      document.execCommand("copy");
    }
  };

  return (
    <div className="p-4">
      <div className="flex justify-center pt-24 pb-6">
        <div className="flex max-w-7xl flex-1 flex-col gap-16">
          <div className="flex flex-col gap-12">
            <div className="max-w-3xl space-y-6">
              <h1 className="text-4xl font-semibold text-slate-900 transition-all md:text-6xl">
                {event.name}
              </h1>
              <p className="text-lg text-slate-500 md:text-xl">
                {event.description}
              </p>
            </div>
          </div>
          <img
            src={event.img_before_link}
            width="1248"
            className="aspect-[375/272] object-cover md:aspect-[1216/480] xl:rounded-2xl "
          />
        </div>
      </div>
      <div className="flex justify-center px-8">
        <div className="flex max-w-7xl flex-1 flex-col gap-4">
          <p className="text-lg text-teal-500">Event ran on</p>
          <p className="text-lg text-slate-500 md:text-xl">
            {event.event_date_time_start}
          </p>
        </div>
        <Link to={`/add/${urlId}`} className="flex flex-1 items-center justify-center gap-2 rounded-lg border border-green-800 bg-green-800 py-2.5 px-4 text-white shadow-sm outline-slate-900 transition-colors hover:border-green-900 hover:bg-green-900">Add</Link>
      </div>
      <div className="py-18 mt-24 flex justify-center">
        <div className="flex max-w-4xl flex-1 flex-col gap-16">
          <h1 className="md:text-3.5xl text-4xl font-semibold text-slate-900 transition-all">
            Introduction
          </h1>
          <p className="text-lg text-slate-500 md:text-xl">
            {event.text_body === undefined ? "" : event.text_body["1"]}
          </p>
          <img
            src={event.img_before_link}
            width="1248"
            className="aspect-[375/272] object-cover shadow-sm md:aspect-[1216/700] xl:rounded-2xl"
          />
          <p className="text-lg text-slate-500 md:text-xl">
            {event.text_body === undefined ? "" : event.text_body["2"]}
          </p>
          <img
            src={event.img_after_link}
            width="1248"
            className="aspect-[375/272] object-cover shadow-sm md:aspect-[1216/700] xl:rounded-2xl "
          />

          <div className="py-18 mt-8 mb-8 flex justify-center">
            <div className="flex flex-1 flex-wrap justify-between gap-16">
              <p className="text-xl text-slate-500">Share this event</p>
              <div className="flex gap-8">
                <button
                  className=" flex items-center gap-4 rounded-md border border-slate-300 bg-white p-2.5 shadow-sm transition-shadow duration-300 hover:shadow-md"
                  onClick={handleCopyLink}
                >
                  <Copy colour="#64748b" size={24} />
                  {copyLinkText}
                </button>
                {event.twitter_link ? (
                  <a
                    className="rounded-md border border-slate-300 bg-white p-2.5 shadow-sm transition-shadow duration-300 hover:shadow-md"
                    href={event.twitter_link}
                  >
                    <Twitter color="#64748b" size={24} />
                  </a>
                ) : null}
                {event.twitter_link ? (
                  <a
                    className="rounded-md border border-slate-300 bg-white p-2.5 shadow-sm transition-shadow duration-300 hover:shadow-md"
                    href={event.facebook_link}
                  >
                    <Facebook color="#64748b" size={24} />
                  </a>
                ) : null}
                {event.twitter_link ? (
                  <a
                    className="rounded-md border border-slate-300 bg-white p-2.5 shadow-sm transition-shadow duration-300 hover:shadow-md"
                    href={event.linkedin_link}
                  >
                    <Linkedin color="#64748b" size={24} />
                  </a>
                ) : null}
              </div>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default EventPage;
