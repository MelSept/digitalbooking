import { createContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { HOME } from "../router/routes";

const UserContext = createContext();

const UserProvider = ({ children }) => {
  const navigate = useNavigate();
  const [user, setUser] = useState(null);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    let lsuser = localStorage.getItem("user");
    if (lsuser) {
      const userData = JSON.parse(lsuser);
      setUser(userData);
    }
    setIsLoading(false);
  }, []);

  const connectUser = (user) => {
    setUser(user);
    localStorage.setItem("user", JSON.stringify(user));
  };

  const disconnectUser = () => {
    setUser(null);
    localStorage.removeItem("user");
    navigate(HOME);
  };

  return (
    <UserContext.Provider
      value={{ user, isLoading, connectUser, disconnectUser }}
    >
      {children}
    </UserContext.Provider>
  );
};

export { UserProvider };
export default UserContext;
