import Request from "../helpers/request";

class EventHandlers {
  getEvents = (id) => {
    const request = new Request();
    return request.get(`/api/events?communityId=${id}`);
  };

  findEvent = (id) => {
    const request = new Request();
    return request.get(`/api/events/${id}`);
  };

  handleEventPut = (event) => {
    const request = new Request();
    request.put(`/api/events/${event.id}`, event).then(() => {
      window.location = "/events";
    });
  };

  handleEventDelete = (id) => {
    const request = new Request();
    const url = "/api/events/" + id;
    request.delete(url).then(() => {
      window.location = "/admin";
    });
  };

  handleEventPost = (event) => {
    const request = new Request();
    request
      .post("/api/events", event)
      .then(() => {
        console.log(event);
        // window.location = "/events";
      });
  };

  handleUpdateEvent = (event) => {
    const request = new Request();
    request.patch(`/api/events/${event.id}`, event).then(() => {
      window.location = "/events";
    });
  };

  handleComment = (event) => {
    const request = new Request();
    request.post("/api/comments", event)
  };
}

export default EventHandlers;
