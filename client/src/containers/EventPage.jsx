import { useState, useEffect } from "react";
import { Link, useParams } from "react-router-dom";
import { useAuth0 } from "@auth0/auth0-react";
import { Copy, Twitter, Facebook, Linkedin } from "react-feather";
import EventHandlers from "../handlers/EventHandlers";
import Footer from "../components/Footer";
import Comments from "../components/Comments";

const EventPage = ({ loggedInUserData }) => {
  const { id } = useParams();
  const [event, setEvent] = useState({});
  const [copyLinkText, setCopyLinkText] = useState("Copy Link");
  const [allComments, setAllComments] = useState([]);

  const { isAuthenticated} = useAuth0();

  useEffect(() => {
    const eventHandler = new EventHandlers();

    eventHandler.findEvent(id).then((result) => {
      setEvent(result);
      setAllComments(result.comments);
    });
  }, [id]);

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

      <div className="py-18 flex justify-center">
        <div className="flex max-w-4xl flex-col gap-6">
          <div className="flex items-center justify-between">
            <div className="flex max-w-7xl flex-col gap-2">
              <p className="font-medium text-green-800">Event on</p>
              <p className="text-lg text-slate-500 md:text-xl">
                {new Date(event.event_date_time_start).toLocaleString()}
              </p>
            </div>
            { isAuthenticated && <Link
              to={`/add/${id}`}
              className="flex-0 rounded-lg border border-green-800 bg-green-800 py-2.5 px-4 text-white shadow-sm outline-slate-900 transition-colors hover:border-green-900 hover:bg-green-900"
            >
              Add Litter
            </Link> }
          </div>
          <div className="my-8 h-[1px] bg-slate-300" />
          <div className="flex flex-1 flex-col gap-16">
            <h1 className="md:text-3.5xl text-4xl font-semibold text-slate-900 transition-all">
              Introduction
            </h1>
            <p className="text-lg text-slate-500 md:text-xl">
              {event.text_body ? event.text_body["1"] : ""}
            </p>
            {event.img_before_link && (
              <img
                src={event.img_before_link}
                width="1248"
                className="aspect-[375/272] object-cover shadow-sm md:aspect-[1216/700] xl:rounded-2xl"
              />
            )}
            <p className="text-lg text-slate-500 md:text-xl">
              {event.text_body ? event.text_body["2"] : ""}
            </p>
            {event.img_after_link && (
              <img
                src={event.img_after_link}
                width="1248"
                className="aspect-[375/272] object-cover shadow-sm md:aspect-[1216/700] xl:rounded-2xl "
              />
            )}
          </div>
          <div className="my-8 h-[1px] bg-slate-300" />
          <div className="flex justify-center">
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
          <Comments
            event={event}
            allComments={allComments}
            setAllComments={setAllComments}
            loggedInUserData={loggedInUserData}
          />
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default EventPage;
