import Request from "../helpers/request";

class homeHandlers {

    getLitterByParameters = () => {
        const request = new Request();
        request.get(`/api/home_stats`)
    }
}

export default homeHandlers;