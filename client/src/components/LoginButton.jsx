import { useAuth0 } from "@auth0/auth0-react";

const LoginButton = ({ buttonText }) => {
  const { loginWithRedirect } = useAuth0();

  return (
    <button
      onClick={() => loginWithRedirect({})}
      className="flex flex-1 items-center justify-center gap-2 rounded-lg py-2.5 px-4 text-slate-700 outline-slate-900 transition-colors hover:border-slate-100 hover:bg-slate-100 md:max-w-[132px] md:flex-none"
    >
      {buttonText}
    </button>
  );
};

export default LoginButton;
