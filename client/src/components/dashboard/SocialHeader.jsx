const SocialHeader = ({ community }) => {
  return (
    <div className="flex flex-col">
      <img
        src={community.hero_img_link}
        className="aspect-[375/160] flex-1 object-cover md:aspect-[1440/261]"
      />
      <div className="flex flex-col gap-6 pt-10 pb-6 px-4 md:flex-row lg:px-28">
        <img
          src={community.logo_img_link}
          className="mt-[-80px] aspect-square w-24 rounded-full border-4 border-white shadow-md md:w-40"
        />
        <h1 className="text-2xl font-semibold text-slate-900 md:text-3xl">
          {community.name}
        </h1>
      </div>
    </div>
  );
};

export default SocialHeader;
