// import { RealEstateListRoutes } from '../feature/page/routes'

import { SlideRoutes } from "../feature/Slide/routes"
import { AccountRoutes } from "../feature/Account/routes"
import { SettingRoutes } from "../feature/Setting/routes"
export const PublicRoutes = [
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
