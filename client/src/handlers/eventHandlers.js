import Request from "../helpers/request";

class EventHandlers {

    getEvents = (id) => {
        const request = new Request();
        return request.get(`/api/events?communityId=${id}`)
    }

    findEvent = (id) => {
        const request = new Request();
        console.log(id);
        return request.get(`/api/events/${id}`)
    }

    handleEventPut = (event) => {
        const request = new Request();
        request.put(`/api/events/${event.id}`, event).then(() => {
            window.location = '/events'
        })
    }

    handleEventDelete = (id) => {
        const request = new Request();
        const url = '/api/events/' + id;
        request.delete(url).then(() => {
            window.location = '/events';
        })
    }

    handleEventPost = (event) => {
        const request = new Request();
        request.post('/api/events', event).then(() => {
            window.location = '/events'
        })
    }

    handleUpdateEvent = (event) => {
        const request = new Request();
        request.patch(`/api/events/${event.id}`, event).then(() => {
            window.location = '/events'
        })
    };

    handleComment = (event) => {
        const request = new Request();
        request.post(`/api/events/${event.id}/comment`, event).then(() => {
            window.location = '/events';
        })
    }
};

export default EventHandlers;