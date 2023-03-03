import Request from "../helpers/request";

class litterHandlers {

    getLitter = () => {
        const request = new Request();
        request.get(`/api/litter-types`)
    }

    handleLitterPost = (litter) => {
        const request = new Request();
        request.post('/api/litter-types', litter).then(() => {
            window.location = '/litter-types'
        })
    }

    handleLitterRegister = (litterCollection) => {
        const request = new Request();
        request.post('/api/litter', litterCollection).then(() => {
            window.location = '/litter'
        })
    }




}

export default litterHandlers;