import React from "react";
import { Route, Routes } from "react-router-dom";
import Home from "../../pages/Home/Home";
import Product from "../../pages/Product/Product";
import Login from "../../pages/Login/Login";
import Register from "../../pages/Register/Register";
import Reservation from "../../pages/Reservation/Reservation";
import Administration from "../../pages/Administration/Administration";
import PrivateRoute from "../PrivateRoute/PrivateRoute";
import SuccessReservation from "../../pages/Success/SuccessReservation";
import Create from "../../pages/Create/Create";
import {
  HOME,
  LOGIN,
  PRODUCT,
  REGISTER,
  RESERVATION,
  SUCCESS,
  ADMINISTRATION,
  CREATE,
  USERPROFILE,
  ADMINPROFILE,
} from "../../router/routes";
import UserProfile from "../../pages/UserProfile/UserProfile";
import AdminProfile from "../../pages/AdminProfile/AdminProfile";
import OnlyAdminRoute from "../OnlyAdminRoute/OnlyAdminRoute";

const Router = () => {
  return (
    <div>
      <Routes>
        <Route exact path={HOME} element={<Home />} />
        <Route exact path={PRODUCT} element={<Product />} />
        <Route exact path={LOGIN} element={<Login />} />
        <Route exact path={REGISTER} element={<Register />} />
        <Route
          exact
          path={RESERVATION}
          element={
            <PrivateRoute>
              <Reservation />
            </PrivateRoute>
          }
        />
        <Route exact path={SUCCESS} element={<SuccessReservation />} />
        <Route exact path={ADMINISTRATION} element={<Administration />} />
        <Route exact path={CREATE} element={<Create />} />
        <Route exact path={USERPROFILE} element={<UserProfile />} />
        <Route
          exact
          path={ADMINPROFILE}
          element={
            <OnlyAdminRoute>
              <AdminProfile />
            </OnlyAdminRoute>
          }
        />
      </Routes>
    </div>
  );
};

export default Router;
