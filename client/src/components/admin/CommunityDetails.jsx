import { useState } from "react";
import { Upload } from "react-feather";
import CommunityHandler from "../../handlers/CommunityHandler";

const CommunityDetails = ({ community }) => {
  const [stateCommunity, setStateCommunity] = useState({
    id: community.id,
    name: community.name,
    description: community.description,
    img_hero_link: community.img_hero_link,
    img_logo_link: community.img_logo_link,
    create_date: community.create_date,
    is_private: community.is_private,
  });

  const handleChange = function (event) {
    let propertyName = event.target.name;
    let copiedCommunity = { ...stateCommunity };
    copiedCommunity[propertyName] = event.target.value;
    setStateCommunity(copiedCommunity);
  };

  const handleSave = (e) => {
    e.preventDefault();
    console.log("handle saved");
    const communityHandler = new CommunityHandler();
    communityHandler.handleCommunityPut(stateCommunity);
  };

  return (
    <form onSubmit={handleSave} className="mx-4 max-w-2xl flex-1">
      <div className="flex flex-col gap-2 border-b py-5">
        <label htmlFor="image" className="text-sm font-medium text-slate-600">
          Community name
        </label>
        <input
          type="text"
          name="name"
          value={stateCommunity.name}
          onChange={handleChange}
          id="name"
          placeholder="Enter your communities name"
          required
          className="flex-1 rounded-lg border border-slate-300 py-3 px-3.5 shadow-sm outline-slate-900 placeholder:text-slate-500"
        />
      </div>
      <div className="flex flex-col gap-2 border-b py-5">
        <label
          htmlFor="description"
          className="text-sm font-medium text-slate-600"
        >
          Community description
        </label>
        <textarea
          id="description"
          name="description"
          value={stateCommunity.description}
          onChange={handleChange}
          placeholder="Enter your communities description"
          className="min-h-[80px] resize-y rounded-lg border border-slate-300 py-3 px-3.5 shadow-sm outline-slate-900 placeholder:text-slate-500"
        ></textarea>
      </div>
      <div className="flex flex-1 flex-wrap gap-6 border-b py-5">
        <div className="flex flex-1 items-center justify-center">
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
                or drag and drop a logo image.
              </p>
              <p className="text-xs text-slate-500 ">
                SVG, PNG or JPG (max. 500x500px)
              </p>
            </div>
            <input id="file" type="file" className="hidden" />
          </label>
        </div>
        {stateCommunity.img_logo_link ? (
          <img
            src={stateCommunity.img_logo_link}
            className="flex aspect-square h-32 rounded-full border border-slate-300 object-cover shadow-sm"
          />
        ) : null}
      </div>
      <div className="flex flex-1 flex-col flex-wrap gap-6 border-b py-5">
        {stateCommunity.img_hero_link ? (
          <img
            src={stateCommunity.img_hero_link}
            className="flex h-32 rounded-lg border border-slate-300 object-cover shadow-sm"
          />
        ) : null}
        <div className="flex flex-1 items-center justify-center">
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
                or drag and drop a hero image.
              </p>
              <p className="text-xs text-slate-500 ">
                SVG, PNG, JPG or GIF (max. 1500x500px)
              </p>
            </div>
            <input id="file" type="file" className="hidden" />
          </label>
        </div>
      </div>

      <div className="flex justify-end">
        <button className="my-5 flex flex-1 items-center justify-center gap-2 rounded-lg border border-green-800 bg-green-800 py-2.5 px-4 text-white shadow-sm outline-slate-900 transition-colors hover:border-green-900 hover:bg-green-900 md:flex-none">
          Save
        </button>
      </div>
    </form>
  );
};

export default CommunityDetails;
