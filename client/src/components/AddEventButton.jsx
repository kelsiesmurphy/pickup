import { Plus } from "react-feather";

const AddEventButton = () => {
  const handleNewEventModal = () => {
    // TODO, perhaps using https://github.com/davidtheclark/react-aria-modal??
  };

  return (
    <button
      onClick={handleNewEventModal}
      className="flex items-center gap-2 rounded-lg border border-green-800 bg-green-800 py-2.5 px-4 text-white shadow-sm outline-slate-900 transition-colors hover:border-green-900 hover:bg-green-900"
    >
      <Plus color="#fff" size={20} />
      New event
    </button>
  );
};

export default AddEventButton;
