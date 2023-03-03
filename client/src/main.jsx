import React from "react";
import { Auth0Provider } from "@auth0/auth0-react";
import { BrowserRouter as Router } from "react-router-dom";
import ReactDOM from "react-dom/client";
import App from "./App";
import ScrollToTop from "./components/scrollToTop"
import "./index.css";

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <Auth0Provider
      domain="dev-xytuy5bn1opxvetk.uk.auth0.com"
      clientId="mUGHIwAYQuvpHNWCM0ziPkAyaGqqWT8l"
      authorizationParams={{ redirect_uri: window.location.origin }}
    >
      <Router>
        <ScrollToTop />
        <App />
      </Router>
    </Auth0Provider>
  </React.StrictMode>
);