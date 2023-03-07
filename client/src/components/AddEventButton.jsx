import { Plus } from "react-feather";
import { useState, useEffect } from "react";
import Rodal from "rodal";
// Include rodal styles
import "rodal/lib/rodal.css";
import EventHandlers from "../handlers/EventHandlers";
import UploadImage from "../s3bucket/pages/uploadImage";
import handleFileUpload from "../s3bucket/pages/api/upload_url";

const AddEventButton = ({ communityId }) => {
  const [openModal, setOpenModal] = useState(false);

  const [title, setTitle] = useState("");
  const [location, setLocation] = useState("");
  const [eventStart, setEventStart] = useState("");
  const [eventEnd, setEventEnd] = useState("");
  const [coverImage, setCoverImage] = useState("");
  const [description, setDescription] = useState("");


  useEffect(() => {
    handleFileUpload(file);
  }, [coverImage]);

  const handleNewEventModal = () => {
    setOpenModal(true);
  };
  const handleClose = () => {
    setOpenModal(false);
  };

  const handleTitle = (event) => {
    setTitle(event.target.value);
  };
  const handleLocation = (event) => {
    setLocation(event.target.value);
  };
  const handleEventStart = (event) => {
    setEventStart(event.target.value);
  };
  const handleEventEnd = (event) => {
    setEventEnd(event.target.value);
  };
  const handleCoverImage = (file) => {
    setCoverImage(file);
  };
  const handleDescription = (event) => {
    setDescription(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    const formSubmission = {
      community_id: communityId,
      name: title,
      description: description,
      location: location,
      event_date_time_start: eventStart,
      event_date_time_end: eventEnd,
      img_before_link: coverImage,
    };

    const eventHandlers = new EventHandlers();
    eventHandlers.handleEventPost(formSubmission);

    handleClose();
  };
  return (
    <>
      <button
        onClick={handleNewEventModal}
        className="flex flex-1 items-center justify-center gap-2 rounded-lg border border-green-800 bg-green-800 py-2.5 px-4 text-white shadow-sm outline-slate-900 transition-colors hover:border-green-900 hover:bg-green-900 md:flex-none"
      >
        <Plus color="#fff" size={20} />
        New event
      </button>
      <Rodal
        visible={openModal}
        onClose={handleClose}
        customStyles={{
          width: "auto",
          height: "auto",
          borderRadius: "12px",
          display: "flex",
          maxWidth: 688,
          flex: 1,
          margin: "16px",
        }}
      >
        <div className="flex flex-col">
          <div className="border-b px-3 py-4">
            <h2 className="text-lg font-semibold text-slate-900">
              Add your event
            </h2>
            <p className="text-slate-500">
              Create your event and share with your community members.
            </p>
          </div>
          <form onSubmit={handleSubmit}>
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
                  name="title"
                  value={title}
                  onChange={handleTitle}
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
                  name="location"
                  value={location}
                  onChange={handleLocation}
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
                  Event start and finish*
                </label>
                <div className="flex max-w-[448px] flex-1 gap-4">
                  <input
                    type="datetime-local"
                    name="date_time_start"
                    value={eventStart}
                    onChange={handleEventStart}
                    className="flex-1 rounded-lg border border-slate-300 py-3 px-3.5 shadow-sm outline-slate-900 placeholder:text-slate-500"
                    id="time"
                    placeholder="12/07/2023, 3pm"
                  />
                  <input
                    type="datetime-local"
                    name="date_time_end"
                    value={eventEnd}
                    onChange={handleEventEnd}
                    className="flex-1 rounded-lg border border-slate-300 py-3 px-3.5 shadow-sm outline-slate-900 placeholder:text-slate-500"
                    id="time"
                    placeholder="12/07/2023, 6pm"
                  />
                </div>
              </div>
              <UploadImage onUpload={handleCoverImage} />
              <div className="flex flex-wrap justify-between gap-8 py-4">
                <label
                  htmlFor="description"
                  className="text-sm font-medium text-slate-800"
                >
                  Event description*
                </label>
                <textarea
                  id="description"
                  name="description"
                  value={description}
                  onChange={handleDescription}
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

export default AddEventButton;
