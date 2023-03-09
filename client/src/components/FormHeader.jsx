import { X } from "react-feather";

const FormHeader = ({handleBanner}) => {
  return (
    <div className="flex basis-full justify-between bg-green-800 py-3.5 px-4 text-white">
      <div className="w-10"></div>
      <div className="flex flex-wrap gap-4">
        <p className="font-medium">
          Capstone project for the G34 CodeClan Professional Development Course.
        </p>
        <a
          href="https://mothjgknfp7.typeform.com/to/pOJLbUro"
          target="_blank"
          className="font-light underline"
        >
          Give us feedback!
        </a>
      </div>
      <div onClick={() => handleBanner()} className="flex w-10 items-center justify-center cursor-pointer">
        <X />
      </div>
    </div>
  );
};

export default FormHeader;
