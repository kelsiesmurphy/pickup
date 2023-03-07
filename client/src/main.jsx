import React from "react";
import { Auth0Provider } from "@auth0/auth0-react";
import { BrowserRouter as Router } from "react-router-dom";
import ReactDOM from "react-dom/client";
import App from "./App";
import ScrollToTop from "./components/scrollToTop";
import "./index.css";

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <Router>
      <Auth0Provider
        domain="dev-xytuy5bn1opxvetk.uk.auth0.com"
        clientId="mUGHIwAYQuvpHNWCM0ziPkAyaGqqWT8l"
        authorizationParams={{ redirect_uri: window.location.origin }}
      >
        <ScrollToTop />
        <App />
      </Auth0Provider>
    </Router>
  </React.StrictMode>
);
