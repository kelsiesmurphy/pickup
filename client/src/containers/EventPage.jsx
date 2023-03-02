import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { Copy, Twitter, Facebook, Linkedin } from "react-feather";
import EventHandlers from "../handlers/eventHandlers";

const EventPage = () => {
  const { id } = useParams();
  const [urlId, setUrlId] = useState(id);
  const [event, setEvent] = useState({});

  useEffect(() => {
    const eventHandler = new EventHandlers();

    eventHandler.findEvent(urlId).then((result) => setEvent(result));
  }, [urlId]);

  const handleCopyLink = () => {
    if (navigator.clipboard) {
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
      </div>
      <div className="py-18 mt-24 flex justify-center">
        <div className="flex max-w-4xl flex-1 flex-col gap-16">
          <h1 className="md:text-3.5xl text-4xl font-semibold text-slate-900 transition-all">
            Introduction
          </h1>
          <p className="text-lg text-slate-500 md:text-xl">
            Lorem ipsum dolor sit amet. Nam aspernatur esse ut consectetur quod
            non molestiae incidunt et omnis nemo qui totam culpa. Ut ratione
            recusandae qui consequuntur aperiam et maxime obcaecati. Id
            accusantium provident hic accusantium voluptatem cum perspiciatis
            consequatur et beatae quae. Ex quia consequuntur est consectetur
            laboriosam ut voluptas mollitia est facere molestiae quo enim dolor
            sed voluptatem placeat. Ut incidunt odio sed molestiae odio sed
            repellendus velit vel eligendi reiciendis est delectus illo et quasi
            quia ea inventore necessitatibus. Nam magnam consequuntur aut dicta
            sint cum quidem omnis qui voluptatem amet a architecto
            necessitatibus ut tenetur debitis. A enim dolorem et velit
            voluptatibus et tempore sint. In expedita commodi ut amet obcaecati
            sit laborum pariatur et dolorem ipsa quo nihil galisum.
          </p>
          <p className="text-lg text-slate-500 md:text-xl">
            Lorem ipsum dolor sit amet. Nam aspernatur esse ut consectetur quod
            non molestiae incidunt et omnis nemo qui totam culpa. Ut ratione
            recusandae qui consequuntur aperiam et maxime obcaecati. Id
            accusantium provident hic accusantium voluptatem cum perspiciatis
            consequatur et beatae quae. Ex quia consequuntur est consectetur
            laboriosam ut voluptas mollitia est facere molestiae quo enim dolor
            sed voluptatem placeat. Ut incidunt odio sed molestiae odio sed
            repellendus velit vel eligendi reiciendis est delectus illo et quasi
            quia ea inventore necessitatibus. Nam magnam consequuntur aut dicta
            sint cum quidem omnis qui voluptatem amet a architecto
            necessitatibus ut tenetur debitis. A enim dolorem et velit
            voluptatibus et tempore sint. In expedita commodi ut amet obcaecati
            sit laborum pariatur et dolorem ipsa quo nihil galisum.
          </p>
          <img
            src={event.img_before_link}
            width="1248"
            className="aspect-[375/272] object-cover md:aspect-[1216/700] xl:rounded-2xl"
          />
          <p className="text-lg text-slate-500 md:text-xl">
            Lorem ipsum dolor sit amet. Nam aspernatur esse ut consectetur quod
            non molestiae incidunt et omnis nemo qui totam culpa. Ut ratione
            recusandae qui consequuntur aperiam et maxime obcaecati. Id
            accusantium provident hic accusantium voluptatem cum perspiciatis
            consequatur et beatae quae. Ex quia consequuntur est consectetur
            laboriosam ut voluptas mollitia est facere molestiae quo enim dolor
            sed voluptatem placeat. Ut incidunt odio sed molestiae odio sed
            repellendus velit vel eligendi reiciendis est delectus illo et quasi
            quia ea inventore necessitatibus. Nam magnam consequuntur aut dicta
            sint cum quidem omnis qui voluptatem amet a architecto
            necessitatibus ut tenetur debitis. A enim dolorem et velit
            voluptatibus et tempore sint. In expedita commodi ut amet obcaecati
            sit laborum pariatur et dolorem ipsa quo nihil galisum.
          </p>
          <img
            src={event.img_after_link}
            width="1248"
            className="aspect-[375/272] object-cover md:aspect-[1216/700] xl:rounded-2xl "
          />

          <div className="py-18 mt-8 mb-8 flex justify-center">
            <div className="flex flex-1 flex-wrap justify-between gap-16">
              <p className="md:text-1.5xl text-2xl text-slate-500">
                Share this post
              </p>
              <div className="flex gap-8">
                <button
                  className=" flex items-center gap-4 rounded-md border-2 border-slate-500 p-2.5 shadow-sm"
                  onClick={handleCopyLink}
                >
                  <Copy colour="gray" size={30} />
                  Copy Link
                </button>
                {event.twitter_link ? (
                  <a
                    className="rounded-md border-2 border-slate-500 p-2.5 shadow-sm"
                    href={event.twitter_link}
                  >
                    <Twitter color="gray" size={30} />
                  </a>
                ) : null}
                {event.twitter_link ? (
                  <a
                    className="rounded-md border-2 border-slate-500 p-2.5 shadow-sm"
                    href={event.facebook_link}
                  >
                    <Facebook color="gray" size={30} />
                  </a>
                ) : null}
                {event.twitter_link ? (
                  <a
                    className="rounded-md border-2 border-slate-500 p-2.5 shadow-sm"
                    href={event.linkedin_link}
                  >
                    <Linkedin color="gray" size={30} />
                  </a>
                ) : null}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default EventPage;
