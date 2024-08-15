import { Route, Routes } from "react-router-dom";
import { SlideDetail } from "../components/SlideDetail";
import { SlideManagement } from "../components/SlideManagement";
import { FavoriteSlideManagement } from "../components/FavoriteSlideManagement";
import { TrashManagement } from "../components/TrashManagement";
import { Outline } from "../components/Outline";
export const SlideRoutes = () => {
  return (
    <Routes>
      <Route path="/home" element={<SlideManagement />} />
      <Route path="/detail/:id" element={<SlideDetail />} />
      <Route path="/favorites" element={<FavoriteSlideManagement />} />
      <Route path="/trash" element={<TrashManagement />} />
      <Route path="/outline" element={<Outline />} />
    </Routes>
  );
};