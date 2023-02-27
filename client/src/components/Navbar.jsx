import { Link } from "react-router-dom";
import { wordmark } from "../assets/AssetFiles";
import { Edit } from "react-feather";
import AddEventButton from "./AddEventButton";
import LoginButton from "./LoginButton";
import { useAuth0 } from "@auth0/auth0-react";
import SignupButton from "./SignupButton";

const Navbar = ({ community }) => {
  const { user, isAuthenticated, logout } = useAuth0();

  const logoutWithRedirect = () =>
    logout({
      logoutParams: {
        returnTo: window.location.origin,
      },
    });

  return (
    <nav className="flex basis-full justify-center">
      <div className="flex flex-1 items-center justify-between py-4 px-4 transition-all lg:px-28">
        <Link to="/" className="outline-slate-900">
          <img src={wordmark} className="aspect-[156/48] h-12" />
        </Link>
        {!isAuthenticated && (
          <div className="flex items-center gap-4">
            <LoginButton buttonText={"Log in"} />
            <SignupButton buttonText={"Sign up"} />
          </div>
        )}
        {isAuthenticated && (
          <div className="flex items-center gap-4">
            <Link
              to={`/communities/${community.id}/edit`}
              className=" flex items-center gap-2 rounded-lg border border-slate-300 bg-white py-2.5 px-4 text-slate-700 shadow-sm outline-slate-900 transition-colors hover:bg-slate-50"
            >
              <Edit color="#334155" size={20} />
              Edit
            </Link>
            <AddEventButton />
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
        )}
      </div>
    </nav>
  );
};

export default Navbar;
