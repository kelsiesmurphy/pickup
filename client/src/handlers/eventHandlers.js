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
    request.put(`/api/events/${event.id}`, event);
  };

  handleEventDelete = (id) => {
    const request = new Request();
    const url = `/api/events/${id}`;
    request.delete(url);
  };

  handleEventPost = (event) => {
    const request = new Request();
    request.post("/api/events", event);
  };

  handleUpdateEvent = (event) => {
    const request = new Request();
    request.patch(`/api/events/${event.id}`, event);
  };

  handleComment = (event) => {
    const request = new Request();
    request.post("/api/comments", event);
  };
}

export default EventHandlers;
