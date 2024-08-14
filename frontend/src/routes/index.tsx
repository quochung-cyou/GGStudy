import { PublicRoutes } from "./PublicRoutes";
import { useRoutes } from "react-router-dom";
import { LandingPage } from "../feature/LandingPage/LandingPage";
export const AppRoutes = () => {
    const commonRoutes = [{ path: "/", element: <LandingPage /> }];
    const routes = PublicRoutes;
    const element = useRoutes([...commonRoutes, ...routes]);
    
    return (
        <>{element}</>
    )
};