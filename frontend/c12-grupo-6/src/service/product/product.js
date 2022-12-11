import { BASE_API_URL } from "../../constants/baseApi";
import { CREATE_PRODUCT } from "../../constants/endpoints";

export const createProduct = async ({ data, tokenType, accessToken }) => {
  const response = await fetch(`${BASE_API_URL}${CREATE_PRODUCT}`, {
    method: "POST",
    headers: {
      Authorization: `${tokenType} ${accessToken}`,
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });

  return response.ok;
};
