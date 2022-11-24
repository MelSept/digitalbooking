import { createContext, useState } from "react";

export const LoginContext = createContext();

export const LoginProvider = ({ children }) => {
  const [stateUserName, stateUserLastName] = useState(false);

  return (
    <LoginContext.Provider value={{ stateUserName, stateUserLastName }}>
      {children}
    </LoginContext.Provider>
  );
};
