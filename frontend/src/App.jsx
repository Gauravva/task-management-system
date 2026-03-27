import { Route, Routes } from "react-router-dom";
import Register from "./pages/register";

function App() {
  return (
    <Routes>
      <Route path="/task-management-system">
        <Route path="register" element={<Register />} />
      </Route>
    </Routes>
  );
}

export default App;
