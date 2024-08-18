// import { RealEstateListRoutes } from '../feature/page/routes'

import { SlideRoutes } from "../feature/Slide/routes"
import { AccountRoutes } from "../feature/Account/routes"
import { SettingRoutes } from "../feature/Setting/routes"
import RedirectCallback from "./RedirectCallback.tsx";
export const PublicRoutes = [
    {
        path: '/callback/*',
        element: <RedirectCallback />,
    },
    {
        path: '/slides/*',
        element: <SlideRoutes />,
    },
    {
        path: '/account/*',
        element: <AccountRoutes />,
    },
    {
        path: '/settings/*',
        element: <SettingRoutes />,
    },
]
