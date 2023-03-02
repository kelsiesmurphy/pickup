import Request from "../helpers/request";

class communityHandlers {
  getCommunities = () => {
    const request = new Request();
    request.get(`api/communities`);
  };

  findCommunity = (id) => {
    const request = new Request();
    request.get(`/api/communities/` + id);
  };

  handleCommunityPut = (community) => {
    const request = new Request();
    request.put(`/api/community/${community.id}`, community).then(() => {
      window.location = "/communities";
    });
  };

  handleCommunityDelete = (id) => {
    const request = new Request();
    const url = "/api/communities/" + id;
    request.delete(url).then(() => {
      window.location = "/communities";
    });
  };

  handleCommunityPost = (community) => {
    const request = new Request();
    request.post("/api/communities", community).then(() => {
      window.location = "/communities";
    });
  };
}

export default communityHandlers;
