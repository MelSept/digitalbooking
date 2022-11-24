import React from "react";
import { Route, Routes } from "react-router-dom";
import Home from "../../pages/Home/Home";
import Product from "../../pages/Product/Product";
import Login from "../../pages/Login/Login";
import Register from "../../pages/Register/Register";
import Reservation from "../../pages/Reservation/Reservation";
import {
  HOME,
  LOGIN,
  PRODUCT,
  REGISTER,
  RESERVATION,
  SUCCESS,
} from "../../router/routes";
import PrivateRoute from "../PrivateRoute/PrivateRoute";
import SuccessReservation from "../../pages/Success/SuccessReservation";

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
      </Routes>
    </div>
  );
};

export default Router;
