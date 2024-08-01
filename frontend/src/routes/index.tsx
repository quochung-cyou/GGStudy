import { PublicRoutes } from "./PublicRoutes";
import { useRoutes } from "react-router-dom";

export const AppRoutes = () => {
    const commonRoutes = [{ path: "/", element: <div>HOME</div> }];
    const routes = PublicRoutes;
    const element = useRoutes([...commonRoutes, ...routes]);
    
    return (
        <>{element}</>
    )
};