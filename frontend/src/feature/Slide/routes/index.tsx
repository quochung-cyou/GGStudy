import { Route, Routes } from "react-router-dom";
import { SlideDetail } from "../components/SlideDetail";
import { SlideManagement } from "../components/SlideManagement";
import { FavoriteSlideManagement } from "../components/FavoriteSlideManagement";
import { TrashManagement } from "../components/TrashManagement";
import { Outline } from "../components/Outline";
import ProtectedRoute from "../../../routes/ProtectedRoute.tsx";
export const SlideRoutes = () => {
    return (
        <Routes>
            <Route path="/home" element={<ProtectedRoute><SlideManagement /></ProtectedRoute>} />
            <Route path="/detail/:id" element={<ProtectedRoute><SlideDetail /></ProtectedRoute>} />
            <Route path="/favorites" element={<ProtectedRoute><FavoriteSlideManagement /></ProtectedRoute>} />
            <Route path="/trash" element={<ProtectedRoute><TrashManagement /></ProtectedRoute>} />
            <Route path="/outline" element={<ProtectedRoute><Outline /></ProtectedRoute>} />
        </Routes>
    );
};