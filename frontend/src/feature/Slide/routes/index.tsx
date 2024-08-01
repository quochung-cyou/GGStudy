import { Route, Routes } from "react-router-dom";
import { SlideDetail } from "../SlideDetail";
import { SlideManagement } from "../SlideManagement";

export const SlideRoutes = () => {
  return (
    <Routes>
      <Route path="/" element={<SlideManagement />} />
      <Route path="/detail/:id" element={<SlideDetail />} />
    </Routes>
  );
};