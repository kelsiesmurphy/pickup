import { useState } from "react";
import { Trash2 } from "react-feather";
import Rodal from "rodal";
// Include rodal styles
import "rodal/lib/rodal.css";

const DeleteItem = ({ itemToRemove, removeItem }) => {
  const [openModal, setOpenModal] = useState(false);

  const handleClose = () => {
    setOpenModal(false);
  };

  const handleRemove = () => {
    removeItem()
    handleClose()
  }

  return (
    <>
      <Trash2
        size={20}
        className="cursor-pointer text-slate-500 transition-colors hover:text-red-700"
        onClick={() => setOpenModal(true)}
      />
      <Rodal
        visible={openModal}
        onClose={handleClose}
        width={400}
        height={210}
        customStyles={{ borderRadius: "12px" }}
      >
        <div>
          <div className="px-3 space-y-2 py-5">
            <h2 className="text-lg font-semibold text-slate-900">
              Remove {itemToRemove}
            </h2>
            <p className="text-slate-500">
              Are you sure you want to remove {itemToRemove}?
            </p>
          </div>
          <div className="flex flex-wrap gap-3 px-3">
              <button
                className="flex flex-1 items-center justify-center gap-2 rounded-lg border border-slate-300 bg-white py-2.5 px-4 text-slate-700 shadow-sm outline-slate-900 transition-colors hover:bg-slate-50"
                onClick={handleClose}
              >
                Cancel
              </button>
              <button
                className="flex flex-1 cursor-pointer items-center justify-center gap-2 rounded-lg border border-red-800 bg-red-800 py-2.5 px-4 text-white shadow-sm outline-slate-900 transition-colors hover:border-green-900 hover:bg-red-900"
                onClick={handleRemove}
              >Remove</button>
            </div>
        </div>
      </Rodal>
    </>
  );
};

export default DeleteItem;
