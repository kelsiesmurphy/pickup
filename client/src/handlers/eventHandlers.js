import Request from "../helpers/request";

class eventHandlers {

    getEvents = () => {
        const request = new Request();
        request.get(`/api/events/`)
    }

    findEvent = (id) => {
        const request = new Request();
        request.get(`/api/events/` + id)
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

export default eventHandlers;