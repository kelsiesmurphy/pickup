import Request from "../helpers/request";

class UserHandlers {
  getUsers = () => {
    const request = new Request();
    request.get("/api/users");
  };

  findUser = (id) => {
    const request = new Request();
    request.get(`/api/users/${id}`);
  };

  findUsersFromCommunity = (id) => {
    const request = new Request();
    return request.get(`/api/users?communityId=${id}`);
  };

  handleUserPut = (user) => {
    const request = new Request();
    request.put(`/api/users/${user.id}`, user);
  };

  handleUserDelete = (id) => {
    const request = new Request();
    const url = `/api/users/${id}`;
    request.delete(url);
  };

  handleUserPost = (user) => {
    const request = new Request();
    request.post("/api/users", user);
  };

  handleUpdateUser = (user) => {
    const request = new Request();
    request.patch(`/api/users/${user.id}`, user);
  };
}

export default UserHandlers;
