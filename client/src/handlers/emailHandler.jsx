import Request from "../helpers/request";

class emailHandler {
    handleEmailPost = (email) => {
        const request = new Request();
        request.post(`/api/email-signup`, email).then(() => {
            window.location = `/`
        })
    }
}

export default emailHandler;