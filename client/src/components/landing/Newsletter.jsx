import { Link } from "react-router-dom";
import { useState } from "react";
import EmailHandler from "../../handlers/EmailHandler";
import toast, { Toaster } from "react-hot-toast";

const Newsletter = () => {
  const [emailText, setEmailText] = useState("");

  const notify = () =>
    toast.success("Thank you for subscribing!", {
      iconTheme: {
        primary: "#166534",
        secondary: "#fff",
      },
    });

  const handleNewsletter = (e) => {
    e.preventDefault();
    const currentDate = new Date();
    const emailToPost = {
      email: emailText,
      signup_date_time: currentDate,
    };
    const emailHandler = new EmailHandler();
    emailHandler.handleEmailPost(emailToPost);
    setEmailText("");
    notify();
  };

  return (
    <div className="flex flex-wrap justify-between gap-8 bg-green-800 py-24 px-4 lg:px-32">
      <Toaster position="top-right" />
      <div className="space-y-4">
        <h2 className="text-3xl font-medium text-white md:text-4xl">
          Sign up for our newsletter
        </h2>
        <p className="text-lg text-green-200">
          Be the first to know about our updates and news.
        </p>
      </div>
      <div className="max-w-md flex-1 space-y-2">
        <form className="flex flex-wrap gap-4" onSubmit={handleNewsletter}>
          <input
            placeholder="Enter your email"
            type="email"
            value={emailText}
            required
            onChange={(e) => setEmailText(e.target.value)}
            className="min-w-[280px] flex-1 rounded-lg border border-white py-3 px-3.5 shadow-sm outline-slate-900"
          />
          <button className="flex flex-1 items-center justify-center gap-2 rounded-lg border border-slate-300 bg-white py-2.5 px-4 text-slate-700 shadow-sm outline-slate-900 transition-colors hover:bg-slate-50 md:flex-none">
            Subscribe
          </button>
        </form>
        <p className="text-sm text-green-200">
          We care about your data in our{" "}
          <Link to="/privacy" className="underline outline-slate-900">
            privacy policy
          </Link>
          .
        </p>
      </div>
    </div>
  );
};

export default Newsletter;
