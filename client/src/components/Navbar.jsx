import { Link } from "react-router-dom";
import { wordmark } from "../assets/AssetFiles";
import { Plus, Edit } from "react-feather";

const Navbar = ({ community }) => {

  const handleNewEventModal = () => {
    // TODO, perhaps using https://github.com/davidtheclark/react-aria-modal??
  };

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
          <button
            onClick={handleNewEventModal}
            className="flex items-center gap-2 rounded-lg border border-green-800 bg-green-800 py-2.5 px-4 text-white shadow-sm transition-colors hover:border-green-900 hover:bg-green-900"
          >
            <Plus color="#fff" size={20} />
            New event
          </button>
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
