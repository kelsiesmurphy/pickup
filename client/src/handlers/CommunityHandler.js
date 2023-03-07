import Request from "../helpers/request";

class CommunityHandler {
  getCommunities = () => {
    const request = new Request();
    return request.get("/api/communities");
  };

  findCommunity = (id) => {
    const request = new Request();
    return request.get(`/api/communities/${id}`);
  };

  handleCommunityPut = (community) => {
    const request = new Request();
    return request.put(`/api/communities/${community.id}`, community);
  };

  handleCommunityDelete = (id) => {
    const request = new Request();
    const url = `/api/communities/${id}`;
    request.delete(url);
  };

  handleCommunityPost = (community) => {
    const request = new Request();
    request.post("/api/communities", community);
  };
}

export default CommunityHandler;
