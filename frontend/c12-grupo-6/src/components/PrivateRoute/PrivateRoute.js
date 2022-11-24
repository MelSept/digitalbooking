import { useContext } from "react";
import { Navigate } from "react-router-dom";
import { HOME } from "../../router/routes";
import UserContext from "../../context/UserContext";

const PrivateRoute = ({ children }) => {
  const { user, isLoading } = useContext(UserContext);

  if (isLoading) {
    return <div>Loading...</div>;
  }

  return user !== null ? children : <Navigate to={HOME} />;
};

export default PrivateRoute;
