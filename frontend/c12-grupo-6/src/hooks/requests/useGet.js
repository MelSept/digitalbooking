import useFetch from "../useFetch";

const useGet = (url, start) => useFetch(url, { method: "GET" }, start);

export default useGet;
