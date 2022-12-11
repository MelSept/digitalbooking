import { BASE_API_URL } from "../../constants/baseApi";
import { SIGN_UP, SIGN_IN } from "../../constants/endpoints";

export const login = async ({ data }) => {
  const response = await fetch(`${BASE_API_URL}${SIGN_IN}`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });

  if (response.ok) {
    const parsedResult = await response.json();
    return parsedResult;
  }
};

export const register = async ({ data }) => {
  const response = await fetch(`${BASE_API_URL}${SIGN_UP}`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });

  return response.ok;
  //const parsedResult = await response.json();
};
