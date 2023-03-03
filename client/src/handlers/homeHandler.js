import Request from "../helpers/request";

class HomeHandler {
  getHomeStats = () => {
    const request = new Request();
    request.get("/api/home-stats");
  };
}

export default HomeHandler;
