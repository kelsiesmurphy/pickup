import Request from "../helpers/request";

class LitterHandlers {
  getLitter = () => {
    const request = new Request();
    return request.get(`/api/litter-types`);
  };

  handleLitterTypesPost = (litter) => {
    const request = new Request();
    request.post("/api/litter-types", litter);
  };

  handleLitterRegister = (litterCollection) => {
    const request = new Request();
    return request.post("/api/litter", litterCollection);
  };
}

export default LitterHandlers;
