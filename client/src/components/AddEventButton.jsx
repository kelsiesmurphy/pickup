import { Plus } from "react-feather";
import { useState } from "react";
import Rodal from 'rodal';
// Include rodal styles
import 'rodal/lib/rodal.css'


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
      <Rodal visible={openModal} onClose={handleClose} width="688" height="610" customStyles={{ borderRadius: '12px' }}>
        <div>
          <div className="px-3 border-b py-4">
            <h2 className="text-slate-900 text-lg font-semibold">Add your event</h2>
            <p className="text-slate-500">Create your event and share with your community members.</p>
          </div>
          <form>
            <div className="px-3">
              <div className="flex flex-wrap gap-8 justify-between border-b border-slate-300 py-4">
                <label for="title" className="text-sm text-slate-800 font-medium">
                  Event title*
                </label>
                <input type="text" className="min-w-[280px] max-w-[448px] flex-1 rounded-lg border border-slate-300 placeholder:text-slate-500 py-3 px-3.5 shadow-sm outline-slate-900" id="title" placeholder="e.g. Monthly litter pick" />
              </div>
              <div className="flex flex-wrap gap-8 justify-between border-b border-slate-300 py-4">
                <label for="location" className="text-sm text-slate-800 font-medium">
                  Event location*
                </label>
                <input type="text" className="min-w-[280px] max-w-[448px] flex-1 rounded-lg border border-slate-300 placeholder:text-slate-500 py-3 px-3.5 shadow-sm outline-slate-900" id="location" placeholder="e.g. Community playing fields" />
              </div>
              <div className="flex flex-wrap gap-8 justify-between border-b border-slate-300 py-4">
                <label for="time" className="text-sm text-slate-800 font-medium">
                  Event time*
                </label>
                <input type="text" className="min-w-[280px] max-w-[448px] flex-1 rounded-lg border border-slate-300 placeholder:text-slate-500 py-3 px-3.5 shadow-sm outline-slate-900" id="time" placeholder="12/07/2023, 3pm-6pm" />
              </div>
              <div className="flex flex-wrap gap-8 justify-between border-b border-slate-300 py-4">
                <label for="image" className="text-sm text-slate-800 font-medium">
                  Cover image*
                </label>
                <input type="text" id="image" placeholder="image link" className="min-w-[280px] max-w-[448px] flex-1 rounded-lg border border-slate-300 placeholder:text-slate-500 py-3 px-3.5 shadow-sm outline-slate-900" />
              </div>
              <div className="flex flex-wrap gap-8 justify-between py-4">
                <label for="Description" className="text-sm text-slate-800 font-medium">
                  Event title*
                </label>
                <textarea id="title" placeholder="Write a few sentences about the event..." className=" resize-none min-w-[280px] max-w-[448px] flex-1 rounded-lg border border-slate-300 placeholder:text-slate-500 py-3 px-3.5 shadow-sm outline-slate-900"></textarea>
              </div>
            </div>
            <div className="flex flex-wrap gap-3 px-3">
              <button className="flex items-center justify-center gap-2 rounded-lg border border-slate-300 bg-white py-2.5 px-4 text-slate-700 shadow-sm outline-slate-900 transition-colors hover:bg-slate-50 flex-1" onClick={handleClose}>Cancel</button>
              <button className="flex items-center justify-center gap-2 rounded-lg border border-green-800 bg-green-800 py-2.5 px-4 text-white shadow-sm outline-slate-900 transition-colors hover:border-green-900 hover:bg-green-900 flex-1" onClick={handleClose}>Add Event</button>
            </div>
          </form>
        </div>
      </Rodal>
    </>
  );
};

export default AddEventButton;
