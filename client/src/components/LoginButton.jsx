const LoginButton = ({ buttonText }) => {
  const handleLogin = () => {
    // TODO
  };

  return (
    <button
      onClick={handleLogin}
      className="flex items-center justify-center gap-2 rounded-lg border border-green-800 bg-green-800 py-2.5 px-4 text-white shadow-sm outline-slate-900 transition-colors hover:border-green-900 hover:bg-green-900 md:max-w-[132px]"
    >
      {buttonText}
    </button>
  );
};

export default LoginButton;
