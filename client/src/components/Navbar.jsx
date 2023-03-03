import { Link } from "react-router-dom";
import { wordmark } from "../assets/AssetFiles";
import AddEventButton from "./AddEventButton";
import LoginButton from "./LoginButton";
import { useAuth0 } from "@auth0/auth0-react";
import SignupButton from "./SignupButton";
import { Heart } from "react-feather";
import { Menu } from "react-feather";
import { useState } from "react";
import { X } from "react-feather";

const Navbar = ({ communityId }) => {
  const { user, isAuthenticated, logout } = useAuth0();

  const [navOpen, setNavOpen] = useState(false);

  const handleNavOpen = () => {
    setNavOpen(!navOpen);
  };

  const logoutWithRedirect = () => {
    logout({
      logoutParams: {
        returnTo: window.location.origin,
      },
    });
  };

  // closes mobile navbar when resizing back to desktop size
  window.onresize = function () {
    var w = window.outerWidth;
    if (w > 768) {
      setNavOpen(false);
    }
  };

  return (
    <>
      <nav className="flex basis-full justify-center">
        <div className="flex flex-1 items-center justify-between py-4 px-4 transition-all lg:px-28">
          <Link to="/" className="outline-slate-900">
            <img src={wordmark} className="aspect-[156/48] h-12" />
          </Link>
          {!isAuthenticated && (
            <>
              <div className="hidden items-center gap-4 sm:flex">
                <LoginButton buttonText={"Log in"} />
                <SignupButton buttonText={"Sign up"} />
              </div>
              <button
                className="p-2.5 sm:hidden"
                onClick={() => handleNavOpen()}
              >
                {navOpen ? (
                  <X color="#667085" size={24} />
                ) : (
                  <Menu color="#667085" size={24} />
                )}
              </button>
            </>
          )}
          {isAuthenticated && (
            <>
              <div className="hidden items-center gap-4 md:flex">
                <Link
                  to={`/communities`}
                  className=" flex items-center gap-2 rounded-lg border border-slate-300 bg-white py-2.5 px-4 text-slate-700 shadow-sm outline-slate-900 transition-colors hover:bg-slate-50"
                >
                  <Heart color="#334155" size={20} />
                  Communities
                </Link>
                <AddEventButton communityId={communityId} />
                <img
                  src={user.picture}
                  className="aspect-square h-10 rounded-full"
                />
                <button
                  onClick={() => logoutWithRedirect()}
                  className="flex items-center justify-center gap-2 rounded-lg py-2.5 px-4 text-slate-700 outline-slate-900 transition-colors hover:border-slate-100 hover:bg-slate-100 md:max-w-[132px]"
                >
                  Log out
                </button>
              </div>
              <div className="flex items-center gap-4 md:hidden">
                <img
                  src={user.picture}
                  className="aspect-square h-10 rounded-full"
                />
                <button className="p-2.5" onClick={() => handleNavOpen()}>
                  {navOpen ? (
                    <X color="#667085" size={24} />
                  ) : (
                    <Menu color="#667085" size={24} />
                  )}
                </button>
              </div>
            </>
          )}
        </div>
      </nav>
      {navOpen && !isAuthenticated && (
        <>
          <div className="flex flex-col justify-center gap-4 p-4">
            <LoginButton buttonText={"Log in"} />
            <SignupButton buttonText={"Sign up"} />
          </div>
        </>
      )}
      {navOpen && isAuthenticated && (
        <div className="flex flex-col justify-center gap-4 p-4">
          <Link
            to={`/communities/${communityId}`}
            className=" flex flex-1 items-center justify-center gap-2 rounded-lg border border-slate-300 bg-white py-2.5 px-4 text-slate-700 shadow-sm outline-slate-900 transition-colors hover:bg-slate-50 md:flex-none"
          >
            <Heart color="#334155" size={20} />
            Community
          </Link>
          <AddEventButton communityId={communityId} />
          <button
            onClick={() => logoutWithRedirect()}
            className="flex flex-1 items-center justify-center gap-2 rounded-lg py-2.5 px-4 text-slate-700 outline-slate-900 transition-colors hover:border-slate-100 hover:bg-slate-100 md:max-w-[132px] md:flex-none"
          >
            Log out
          </button>
        </div>
      )}
    </>
  );
};

export default Navbar;
