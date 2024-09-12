import {PublicRoutes} from "./PublicRoutes";
import {useRoutes} from "react-router-dom";
import {LandingPage} from "../feature/LandingPage/LandingPage";
import React from "react";
import {AnimatePresence} from "framer-motion";
import {ToastContainer} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';

export const AppRoutes = () => {
    const commonRoutes = [{path: "/", element: <LandingPage/>}];
    const routes = PublicRoutes;
    const element = useRoutes([...commonRoutes, ...routes]);

    return (
        <AnimatePresence mode={"wait"}>
            <ToastContainer />
            <>{element}</>


        </AnimatePresence>
    )
};