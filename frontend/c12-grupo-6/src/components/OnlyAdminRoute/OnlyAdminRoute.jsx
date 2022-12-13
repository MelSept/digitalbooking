import { useContext } from "react";
import { Navigate } from "react-router-dom";
import { HOME } from "../../router/routes";
import UserContext from "../../context/UserContext";
import Loader from "../Loader/Loader";

const OnlyAdminRoute = ({ children }) => {
  const { user, isLoading } = useContext(UserContext);

  if (isLoading) {
    return <Loader />;
  }

  return user !== null && user.role === "admin" ? (
    children
  ) : (
    <Navigate to={HOME} />
  );
};

export default OnlyAdminRoute;
