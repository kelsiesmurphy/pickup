import { useState, useEffect } from "react";
import MainContainer from "./containers/MainContainer";
// import communityHandlers from "./handlers/communityHandler";
import Request from "./helpers/request";


function App() {
  // const [communities, setCommunities] = useState([]);

  // useEffect(() => {
  //   const request = new Request();

  //   request.get(`api/communities`).then((data) => setCommunities(data));
  // }, []);

  return <MainContainer />;
}

export default App;
