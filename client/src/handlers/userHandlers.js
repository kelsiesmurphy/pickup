import Request from "../helpers/request";

class userHandlers {

    getUsers = () => {
        const request = new Request();
        request.get(`api/users`)
    }

    findUser = (id) => {
        const request = new Request();
        request.get(`/api/users/` + id)
    }
}

findUserFromCommunity = (id) => {
    const request = new Request();
    request.get(`/api/users/?communityId=${communityId}` + id);
}


handleUserPut = (user) => {
    const request = new Request();
    request.put(`/api/users/${user.id}`, user).then(() => {
        window.location = '/users'
    })
}

handleUserDelete = (id) => {
    const request = new Request();
    const url = '/api/users/' + id;
    request.delete(url).then(() => {
        window.location = '/users';
    })
}

handleUserPost = (user) => {
    const request = new Request();
    request.post('/api/users', user).then(() => {
        window.location = '/users'
    })
}
handleUpdateUser = (user) => {
    const request = new Request();
    request.patch(`/api/users/${user.id}`, user).then(() => {
        window.location = '/users'
    })
};


export default userHandlers;
