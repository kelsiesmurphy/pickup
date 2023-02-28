import { Plus } from "react-feather";
import { useState } from "react";
import Rodal from "rodal";
// Include rodal styles
import "rodal/lib/rodal.css";

const AddEventButton = () => {
  const [openModal, setOpenModal] = useState(false);

  const handleNewEventModal = () => {
    setOpenModal(true);
  };

  const handleClose = () => {
    setOpenModal(false);
  };

  return (
    <>
      <button
        onClick={handleNewEventModal}
        className="flex items-center gap-2 rounded-lg border border-green-800 bg-green-800 py-2.5 px-4 text-white shadow-sm outline-slate-900 transition-colors hover:border-green-900 hover:bg-green-900"
      >
        <Plus color="#fff" size={20} />
        New event
      </button>
      <Rodal
        visible={openModal}
        onClose={handleClose}
        width={688}
        height={610}
        customStyles={{ borderRadius: "12px" }}
      >
        <div>
          <div className="border-b px-3 py-4">
            <h2 className="text-lg font-semibold text-slate-900">
              Add your event
            </h2>
            <p className="text-slate-500">
              Create your event and share with your community members.
            </p>
          </div>
          <form>
            <div className="px-3">
              <div className="flex flex-wrap justify-between gap-8 border-b border-slate-300 py-4">
                <label
                  htmlFor="title"
                  className="text-sm font-medium text-slate-800"
                >
                  Event title*
                </label>
                <input
                  type="text"
                  className="min-w-[280px] max-w-[448px] flex-1 rounded-lg border border-slate-300 py-3 px-3.5 shadow-sm outline-slate-900 placeholder:text-slate-500"
                  id="title"
                  placeholder="e.g. Monthly litter pick"
                />
              </div>
              <div className="flex flex-wrap justify-between gap-8 border-b border-slate-300 py-4">
                <label
                  htmlFor="location"
                  className="text-sm font-medium text-slate-800"
                >
                  Event location*
                </label>
                <input
                  type="text"
                  className="min-w-[280px] max-w-[448px] flex-1 rounded-lg border border-slate-300 py-3 px-3.5 shadow-sm outline-slate-900 placeholder:text-slate-500"
                  id="location"
                  placeholder="e.g. Community playing fields"
                />
              </div>
              <div className="flex flex-wrap justify-between gap-8 border-b border-slate-300 py-4">
                <label
                  htmlFor="time"
                  className="text-sm font-medium text-slate-800"
                >
                  Event time*
                </label>
                <input
                  type="text"
                  className="min-w-[280px] max-w-[448px] flex-1 rounded-lg border border-slate-300 py-3 px-3.5 shadow-sm outline-slate-900 placeholder:text-slate-500"
                  id="time"
                  placeholder="12/07/2023, 3pm-6pm"
                />
              </div>
              <div className="flex flex-wrap justify-between gap-8 border-b border-slate-300 py-4">
                <label
                  htmlFor="image"
                  className="text-sm font-medium text-slate-800"
                >
                  Cover image*
                </label>
                <input
                  type="text"
                  id="image"
                  placeholder="image link"
                  className="min-w-[280px] max-w-[448px] flex-1 rounded-lg border border-slate-300 py-3 px-3.5 shadow-sm outline-slate-900 placeholder:text-slate-500"
                />
              </div>
              <div className="flex flex-wrap justify-between gap-8 py-4">
                <label
                  htmlFor="Description"
                  className="text-sm font-medium text-slate-800"
                >
                  Event title*
                </label>
                <textarea
                  id="title"
                  placeholder="Write a few sentences about the event..."
                  className=" min-w-[280px] max-w-[448px] flex-1 resize-none rounded-lg border border-slate-300 py-3 px-3.5 shadow-sm outline-slate-900 placeholder:text-slate-500"
                ></textarea>
              </div>
            </div>
            <div className="flex flex-wrap gap-3 px-3">
              <button
                className="flex flex-1 items-center justify-center gap-2 rounded-lg border border-slate-300 bg-white py-2.5 px-4 text-slate-700 shadow-sm outline-slate-900 transition-colors hover:bg-slate-50"
                onClick={handleClose}
              >
                Cancel
              </button>
              <button
                className="flex flex-1 items-center justify-center gap-2 rounded-lg border border-green-800 bg-green-800 py-2.5 px-4 text-white shadow-sm outline-slate-900 transition-colors hover:border-green-900 hover:bg-green-900"
                onClick={handleClose}
              >
                Add Event
              </button>
            </div>
          </form>
        </div>
      </Rodal>
    </>
  );
};

export default AddEventButton;
