import { BASE_API_URL } from "../../constants/baseApi";
import { CREATE_RESERVATION } from "../../constants/endpoints";

export const postReservation = async ({ data, tokenType, accessToken }) => {
  const response = await fetch(`${BASE_API_URL}${CREATE_RESERVATION}`, {
    method: "POST",
    headers: {
      Authorization: `${tokenType} ${accessToken}`,
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });

  return response.ok;
};
