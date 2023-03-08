import { wordmark } from "../assets/AssetFiles";

const Footer = () => {
  return (
    <footer className="flex basis-full justify-center pt-16 pb-12">
      <hr />
      <div className="mx-4 flex flex-wrap  flex-1 items-center justify-center md:justify-between border-t gap-6 py-8 transition-all lg:mx-28">
        <img src={wordmark} className="aspect-[156/48] h-12" />
        <p className="text-slate-500">© 2023 Pickup. All rights reserved.</p>
      </div>
    </footer>
  );
};

export default Footer;
