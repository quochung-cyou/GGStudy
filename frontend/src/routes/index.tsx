import {PublicRoutes} from "./PublicRoutes";
import {useRoutes} from "react-router-dom";
import {LandingPage} from "../feature/LandingPage/LandingPage";
import React from "react";
import {AnimatePresence} from "framer-motion";

export const AppRoutes = () => {
    const commonRoutes = [{path: "/", element: <LandingPage/>}];
    const routes = PublicRoutes;
    const element = useRoutes([...commonRoutes, ...routes]);

    return (
        <AnimatePresence mode={"wait"}>
            <>{element}</>
        </AnimatePresence>
    )
};