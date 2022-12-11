import { useEffect, useState } from "react";
import { BASE_API_URL } from "../constants/baseApi";

const useFetch = (url, options, start) => {
  const [data, setData] = useState(null);
  const [error, setError] = useState(null);
  const [isLoading, setIsLoading] = useState(true);
  const [status, setStatus] = useState(null);

  useEffect(() => {
    //console.log("url", url, "options", options, "start", start);
    if (!start) return;

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
          setStatus(result.status);
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
  }, [start, url]);

  return { data, isLoading, error, status };
};

export default useFetch;
