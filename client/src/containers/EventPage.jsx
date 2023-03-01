import { useParams } from "react-router-dom";
import { Copy, Twitter, Facebook, Linkedin } from "react-feather";


const EventPage = () => {


  const { id } = useParams();

  const event = {
    id: 1,
    communityId: 4,
    name: "Beach CleanUp",
    description: "Each month we do a cleanup of our local beach. All equipment provided by the Western Rotary Club",
    text_body: "At this beach we are gonna clean all the shit up and make it look really good and smell so much better than it is.",
    location: "Troon",
    event_date_time: "12/04/23, 3:57pm",
    before_img_link: "https://ichef.bbci.co.uk/news/976/cpsprodpb/06A1/production/_101879610_d7429587-d1e4-4b02-87ca-1dcb34cf1d53.jpg",
    after_img_link: "https://www.rd.com/wp-content/uploads/2020/04/tropic-of-cancer-beach-in-exuma-bahamas.jpg?fit=700,467",
    is_active: true
  }

  return (
    <>
      <div className="flex justify-center pt-24 pb-6">
        <div className="flex flex-1 max-w-7xl flex-col gap-16">
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
            src="https://images.pexels.com/photos/9034664/pexels-photo-9034664.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
            width="1248"
            className="aspect-[375/272] object-cover md:aspect-[1216/480] xl:rounded-2xl "
          />
        </div>
      </div>
      <div className="flex justify-center px-8">
        <div className="flex flex-1 max-w-7xl flex-col gap-4">
          <p className="text-teal-500 text-lg">
            Event ran on
          </p>
          <p className="text-lg text-slate-500 md:text-xl">
            {event.event_date_time}
          </p>
        </div>
      </div>
      <div className="flex justify-center py-18 mt-24">
        <div className="flex flex-1 max-w-4xl flex-col gap-16">
          <h1 className="text-4xl font-semibold text-slate-900 transition-all md:text-3.5xl">
            Introduction
          </h1>
          <p className="text-lg text-slate-500 md:text-xl">
            Lorem ipsum dolor sit amet. Nam aspernatur esse ut consectetur
            quod non molestiae incidunt et omnis nemo qui totam culpa.
            Ut ratione recusandae qui consequuntur aperiam et maxime
            obcaecati. Id accusantium provident hic accusantium
            voluptatem cum perspiciatis consequatur et beatae quae.
            Ex quia consequuntur est consectetur laboriosam ut
            voluptas mollitia est facere molestiae quo enim
            dolor sed voluptatem placeat.
            Ut incidunt odio sed molestiae odio sed repellendus
            velit vel eligendi reiciendis est delectus illo et quasi quia ea
            inventore necessitatibus. Nam magnam consequuntur aut dicta sint cum
            quidem omnis qui voluptatem amet a architecto necessitatibus ut tenetur
            debitis. A enim dolorem et velit voluptatibus et tempore sint. In expedita
            commodi ut amet obcaecati sit laborum pariatur et dolorem ipsa quo nihil galisum.
          </p>
          <p className="text-lg text-slate-500 md:text-xl">
            Lorem ipsum dolor sit amet. Nam aspernatur esse ut consectetur
            quod non molestiae incidunt et omnis nemo qui totam culpa.
            Ut ratione recusandae qui consequuntur aperiam et maxime
            obcaecati. Id accusantium provident hic accusantium
            voluptatem cum perspiciatis consequatur et beatae quae.
            Ex quia consequuntur est consectetur laboriosam ut
            voluptas mollitia est facere molestiae quo enim
            dolor sed voluptatem placeat.
            Ut incidunt odio sed molestiae odio sed repellendus
            velit vel eligendi reiciendis est delectus illo et quasi quia ea
            inventore necessitatibus. Nam magnam consequuntur aut dicta sint cum
            quidem omnis qui voluptatem amet a architecto necessitatibus ut tenetur
            debitis. A enim dolorem et velit voluptatibus et tempore sint. In expedita
            commodi ut amet obcaecati sit laborum pariatur et dolorem ipsa quo nihil galisum.
          </p>
          <img
            src={event.before_img_link}
            width="1248"
            className="aspect-[375/272] object-cover md:aspect-[1216/700] xl:rounded-2xl"
          />
          <p className="text-lg text-slate-500 md:text-xl">
            Lorem ipsum dolor sit amet. Nam aspernatur esse ut consectetur
            quod non molestiae incidunt et omnis nemo qui totam culpa.
            Ut ratione recusandae qui consequuntur aperiam et maxime
            obcaecati. Id accusantium provident hic accusantium
            voluptatem cum perspiciatis consequatur et beatae quae.
            Ex quia consequuntur est consectetur laboriosam ut
            voluptas mollitia est facere molestiae quo enim
            dolor sed voluptatem placeat.
            Ut incidunt odio sed molestiae odio sed repellendus
            velit vel eligendi reiciendis est delectus illo et quasi quia ea
            inventore necessitatibus. Nam magnam consequuntur aut dicta sint cum
            quidem omnis qui voluptatem amet a architecto necessitatibus ut tenetur
            debitis. A enim dolorem et velit voluptatibus et tempore sint. In expedita
            commodi ut amet obcaecati sit laborum pariatur et dolorem ipsa quo nihil galisum.
          </p>
          <img
            src={event.after_img_link}
            width="1248"
            className="aspect-[375/272] object-cover md:aspect-[1216/700] xl:rounded-2xl "
          />
          <footer>
            <div className="flex justify-center py-18 mt-8 mb-8">
              <div className="flex flex-1 justify-between flex-wrap gap-16">
                <p className="text-2xl text-slate-500 md:text-1.5xl">
                  Share this post
                </p>
                <div className="flex gap-8">
                  <div className=" flex gap-4 items-center border-2 border-slate-500 rounded-md p-2.5">
                    <Copy colour="gray" size={30} />
                    Copy Link
                  </div>
                  <div className="border-2 border-slate-500 rounded-md p-2.5">
                    <Twitter color="gray" size={30} />
                  </div>
                  <div className="border-2 border-slate-500 rounded-md p-2.5">
                    <Facebook color="gray" size={30} />
                  </div>
                  <div className="border-2 border-slate-500 rounded-md p-2.5">
                    <Linkedin color="gray" size={30} />
                  </div>
                </div>
              </div>
            </div>
          </footer>
        </div>
      </div >
    </>
  )
};


export default EventPage;
