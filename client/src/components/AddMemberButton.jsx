import { Plus, Upload } from "react-feather";
import { useState } from "react";
import Rodal from "rodal";
// Include rodal styles
import "rodal/lib/rodal.css";
import EventHandlers from "../handlers/eventHandlers";

const AddUserButton = ({ communityId }) => {
  const [openModal, setOpenModal] = useState(false);

  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [profileImage, setProfileImage] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("submission");
  };

  const closeModal = (e) => {
    e.preventDefault();
    setOpenModal(false);
  }

  return (
    <>
      <button
        onClick={() => setOpenModal(true)}
        className="flex flex-1 items-center justify-center gap-2 rounded-lg border border-green-800 bg-green-800 py-2.5 px-4 text-white shadow-sm outline-slate-900 transition-colors hover:border-green-900 hover:bg-green-900 md:flex-none"
      >
        <Plus color="#fff" size={20} />
        Add member
      </button>
      <Rodal
        visible={openModal}
        onClose={closeModal}
        width={688}
        height={500}
        customStyles={{ borderRadius: "12px" }}
      >
        <div>
          <div className="border-b px-3 py-4">
            <h2 className="text-lg font-semibold text-slate-900">
              Add a new member
            </h2>
            <p className="text-slate-500">
              Create a member and we'll invite them to Pickup with an email.
            </p>
          </div>
          <form onSubmit={handleSubmit}>
            <div className="px-3">
              <div className="flex flex-wrap justify-between gap-8 border-b border-slate-300 py-4">
                <label
                  htmlFor="title"
                  className="text-sm font-medium text-slate-800"
                >
                  Username*
                </label>
                <input
                  type="text"
                  name="title"
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                  className="min-w-[280px] max-w-[448px] flex-1 rounded-lg border border-slate-300 py-3 px-3.5 shadow-sm outline-slate-900 placeholder:text-slate-500"
                  id="title"
                  placeholder="e.g. JohnSmith"
                />
              </div>
              <div className="flex flex-wrap justify-between gap-8 border-b border-slate-300 py-4">
                <label
                  htmlFor="email"
                  className="text-sm font-medium text-slate-800"
                >
                  Email address*
                </label>
                <input
                  type="text"
                  name="email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  className="min-w-[280px] max-w-[448px] flex-1 rounded-lg border border-slate-300 py-3 px-3.5 shadow-sm outline-slate-900 placeholder:text-slate-500"
                  id="email"
                  placeholder="e.g. johnsmith@example.com"
                />
              </div>
              <div className="flex flex-wrap justify-between gap-8 py-4">
                <label
                  htmlFor="email"
                  className="text-sm font-medium text-slate-800"
                >
                  Profile image*
                </label>
                <div className="min-w-[280px] max-w-[448px] flex flex-1 items-center justify-center">
                  <label
                    htmlFor="file"
                    className="flex h-32 w-full cursor-pointer flex-col items-center justify-center rounded-lg border border-slate-300 bg-white shadow-sm transition-colors hover:bg-slate-100"
                  >
                    <div className="mx-4 flex flex-col items-center justify-center pt-5 pb-6">
                      <Upload size={24} color="#475569" className="mb-3" />
                      <p className="mb-2 text-sm text-slate-500 ">
                        <span className="font-semibold text-green-900">
                          Click to upload
                        </span>{" "}
                        or drag and drop a profile image.
                      </p>
                      <p className="text-xs text-slate-500 ">
                        SVG, PNG, JPG or GIF (max. 500x500px)
                      </p>
                    </div>
                    <input id="file" type="file" className="hidden" />
                  </label>
                </div>
              </div>
            </div>
            <div className="flex flex-wrap gap-3 px-3">
              <button
                className="flex flex-1 items-center justify-center gap-2 rounded-lg border border-slate-300 bg-white py-2.5 px-4 text-slate-700 shadow-sm outline-slate-900 transition-colors hover:bg-slate-50"
                onClick={closeModal}
              >
                Cancel
              </button>
              <input
                type="submit"
                className="flex flex-1 cursor-pointer items-center justify-center gap-2 rounded-lg border border-green-800 bg-green-800 py-2.5 px-4 text-white shadow-sm outline-slate-900 transition-colors hover:border-green-900 hover:bg-green-900"
                value="Add Event"
              />
            </div>
          </form>
        </div>
      </Rodal>
    </>
  );
};

export default AddUserButton;
