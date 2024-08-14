import { Route, Routes } from "react-router-dom";
import { AccountInfor } from "../AccountInfor";
export const AccountRoutes = () => {
  return (
    <Routes>
      <Route path="/" element={<AccountInfor />} />
    </Routes>
  );
};