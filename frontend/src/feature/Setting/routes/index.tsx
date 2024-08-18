import { Route, Routes } from "react-router-dom";
import { Setting } from "../Setting";
export const SettingRoutes = () => {
  return (
    <Routes>
      <Route path="/" element={<Setting />} />
    </Routes>
  );
};