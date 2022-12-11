import { useContext } from "react";
import UserContext from "../../context/UserContext";
import useFetch from "../useFetch";

const usePost = (url, data, start) => {
  const { user } = useContext(UserContext);

  return useFetch(
    url,
    {
      method: "POST",
      body: JSON.stringify(data),
      headers: {
        Authorization: `${user.tokenType} ${user.accessToken}`,
        "Content-Type": "application/json",
      },
    },
    start
  );
};

export default usePost;
