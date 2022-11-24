import { useEffect, useState } from "react";
import { BASE_API_URL } from "../constants/baseApi";

const useFetch = (url, options) => {
  const [data, setData] = useState(null);
  const [error, setError] = useState(null);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    let suscribed = false;

    const fetchData = async () => {
      if (!url) return;

      setIsLoading(true);
      setData(null);
      setError(null);

      try {
        const result = await fetch(`${BASE_API_URL}${url}`, { ...options });
        const parsedResult = await result.json();
        if (!suscribed) {
          setData(parsedResult);
        }
      } catch (error) {
        console.error(error);
        setError(error);
      } finally {
        setIsLoading(false);
      }
    };

    fetchData();

    return () => {
      suscribed = true;
    };
  }, [url]);

  return { data, isLoading, error };
};

export default useFetch;
