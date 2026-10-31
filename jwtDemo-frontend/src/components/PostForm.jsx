
import { useActionState, useState } from "react";
import SubmitButton from "../SubmitButton";
import api from "../api";

const PostForm = () => {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(false);
  const [postError, setPostError] = useState("");

  const getAllPosts = async () => {
    setLoading(true);
    setPostError("");

    try {
      const response = await api.get("/posts");
      setPosts(response.data);
    } catch (error) {
      setPostError("Failed to get posts. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  const submitAction = async (prev, formData) => {
    const title = formData.get("title")?.trim();
    const content = formData.get("content")?.trim();

    if (!title || !content) {
      return {
        error: "Title and content are required",
        msg: "",
        title,
        content,
      };
    }

    try {
      await api.post("/posts", { title, content });

      const response = await api.get("/posts");
      setPosts(response.data);

      return {
        error: null,
        msg: "Post created successfully",
        title: "",
        content: "",
      };
    } catch (error) {
      return {
        error: error.response?.data?.error || "Failed to create post",
        msg: "",
        title,
        content,
      };
    }
  };

  const [state, formAction] = useActionState(submitAction, {
    error: null,
    msg: "",
    title: "",
    content: "",
  });

  return (
    <div className="post-page">

      {/* Create Post */}
      <div className="create-post-card">
        <h2>Create a Post</h2>
        <p>Share your thoughts with everyone.</p>

        <form action={formAction}>
          <input
            type="text"
            name="title"
            placeholder="Enter post title"
            defaultValue={state.title}
            required
          />

          <textarea
            name="content"
            placeholder="Write your post here..."
            rows={5}
            defaultValue={state.content}
            required
          />

          <SubmitButton />

          {state.msg && <p className="success-message">{state.msg}</p>}
          {state.error && <p className="error-message">{state.error}</p>}
        </form>
      </div>

      {/* All Posts */}
      <div className="posts-section">
        <div className="posts-header">
          <div>
            <h2>All Posts</h2>
            <p>Explore posts shared by everyone.</p>
          </div>

          <button
            className="get-posts-btn"
            type="button"
            onClick={getAllPosts}
            disabled={loading}
          >
            {loading ? "Loading..." : "Get All Posts"}
          </button>
        </div>

        {postError && <p className="error-message">{postError}</p>}

        <div className="posts-grid">
          {posts.length === 0 ? (
            <div className="empty-posts">
              <h3>No posts yet</h3>
              <p>Click Get All Posts to load posts.</p>
            </div>
          ) : (
            posts.map((post) => (
              <div className="post-card" key={post.id}>
                <h3>{post.title}</h3>
                <p>{post.content}</p>

                <hr className="post-divider" />

                <div className="post-author">
                  Created by: <span>{post.username}</span>
                </div>
              </div>
            ))
          )}
        </div>
      </div>

    </div>
  );
};

export default PostForm;
