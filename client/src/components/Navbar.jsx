import { Link } from "react-router-dom";
import { wordmark } from "../assets/AssetFiles";
import { Edit } from "react-feather";
import AddEventButton from "./AddEventButton";

const Navbar = ({ community }) => {
  return (
    <div className="flex basis-full justify-center">
      <div className="flex flex-1 items-center justify-between py-4 px-4 transition-all lg:px-28">
        <img src={wordmark} className="aspect-[156/48] h-12" />
        <div className="flex items-center gap-4">
          <Link
            to={`/communities/${community.id}/edit`}
            className="flex items-center gap-2 rounded-lg border border-slate-300 bg-white py-2.5 px-4 text-slate-700 shadow-sm transition-colors hover:bg-slate-50"
          >
            <Edit color="#334155" size={20} />
            Edit
          </Link>
          <AddEventButton />
          <img
            src={community.logo_img_link}
            className="aspect-square h-10 rounded"
          />
        </div>
      </div>
    </div>
  );
};

export default Navbar;
