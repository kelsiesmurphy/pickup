import { Link } from "react-router-dom";

import { image404 } from "../assets/AssetFiles";
import Footer from "../components/Footer";

const Error = () => {
  return (
    <>
      <div className="flex justify-center px-4">
        <div className="mt-16 mb-24 flex max-w-7xl flex-1 flex-wrap items-center justify-between gap-8">
          <div className="flex flex-1 flex-col gap-4 md:max-w-md">
            <h2 className="font-semibold text-green-800">404 error</h2>
            <h1 className="text-4xl font-semibold md:text-6xl">
              Page not found
            </h1>
            <p className="text-slate-500">
              Sorry, the page you are looking for doesn't exist. Go back, or
              head home
            </p>
            <Link
              to="/"
              className="mt-6 flex justify-center gap-2 rounded-lg border border-green-800 bg-green-800 py-2.5 px-4 text-white shadow-sm transition-colors hover:border-green-900 hover:bg-green-900 md:max-w-[152px]"
            >
              Take me home
            </Link>
          </div>
          <img src={image404} width="592" className="aspect-[592/640] object-cover" />
        </div>
      </div>
      <Footer />
    </>
  );
};

export default Error;
