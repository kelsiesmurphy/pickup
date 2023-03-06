import Request from "../helpers/request";

class EmailHandler {
    handleEmailPost = (email) => {
        const request = new Request();
        request.post("/api/email-signup", email)
    }
}

export default EmailHandler;