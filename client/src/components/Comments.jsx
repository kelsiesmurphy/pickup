import { useState } from "react";
import { Send } from "react-feather";
import EventHandlers from "../handlers/EventHandlers";

const Comments = ({ event, allComments, setAllComments, loggedInUserData }) => {
  const [comment, setComment] = useState({
    event_id: event.id,
    user: {},
    comment_date_time: "",
    text_body: "",
  });

  const handleCommentChange = (e) => {
    const currentDate = new Date();
    const commentSetup = {
      event_id: event.id,
      comment_date_time: currentDate,
      text_body: e.target.value,
      user: {
        id: loggedInUserData.id,
        user_name: loggedInUserData.user_name,
        img_profile_link: loggedInUserData.img_profile_link,
      },
    };
    setComment(commentSetup);
  };

  const handleCommentSubmit = (e) => {
    e.preventDefault();
    const eventHandlers = new EventHandlers();
    const commentForPosting = {
      event_id: comment.event_id,
      user_id: comment.user.id,
      comment_date_time: comment.comment_date_time,
      text_body: comment.text_body,
    };
    eventHandlers.handleComment(commentForPosting);
    console.log(commentForPosting);
    const allCommentsClone = [...allComments];
    allCommentsClone.push(comment);
    setAllComments(allCommentsClone);
    setComment({
      event_id: event.id,
      user: {},
      comment_date_time: "",
      text_body: "",
    });
  };

  let commentNodes;
  if (allComments) {
    const sortedComments = allComments.sort(function (a, b) {
      return new Date(b.comment_date_time) - new Date(a.comment_date_time);
    });
    commentNodes = sortedComments.map((comment, index) => {
      return (
        <li key={index} className="flex items-start gap-4">
          <img
            src={comment.user.img_profile_link}
            className="flex-0 aspect-square w-10 rounded-full object-cover shadow-sm"
          />
          <div className="flex flex-1 flex-col gap-3 rounded-lg rounded-tl-sm border bg-slate-100 py-2.5 px-3.5 text-slate-800 shadow-sm">
            <h4 className="text-xs font-medium text-slate-500">
              {comment.user.user_name}
            </h4>
            <p>{comment.text_body}</p>
          </div>
        </li>
      );
    });
  }

  return (
    <div className="space-y-4">
      <h3 className="text-2xl font-medium text-slate-900">Comments</h3>
      <div>
        <form
          onSubmit={handleCommentSubmit}
          className="items-between flex flex-col flex-wrap gap-2 py-4"
        >
          <label htmlFor="description" className="font-medium text-slate-800">
            Add comment
          </label>
          <textarea
            id="description"
            name="description"
            value={comment.text_body}
            onChange={handleCommentChange}
            placeholder="Write a comment..."
            className="flex flex-1 resize-none rounded-lg border border-slate-300 py-3 px-3.5 shadow-sm outline-slate-900 placeholder:text-slate-500"
          ></textarea>
          <button className="flex flex-1 items-center justify-center gap-2 rounded-lg border border-green-800 bg-green-800 py-2.5 px-4 text-white shadow-sm outline-slate-900 transition-colors hover:border-green-900 hover:bg-green-900 md:flex-none">
            <Send color="#fff" size={20} />
            Send
          </button>
        </form>
      </div>
      <ul className="flex flex-col gap-4">{commentNodes}</ul>
    </div>
  );
};

export default Comments;
