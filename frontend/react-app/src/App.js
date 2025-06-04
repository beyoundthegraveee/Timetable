import logo from './logo.svg';
import './styles/App.css';
import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import NotFoundPage from './components/NotFoundPage';
import LoginPage from './components/LoginPage';
import HomePage from './components/HomePage';
import GroupPage from './components/GroupPage';
import Menu from "./components/Menu";

const App = () => {
  return (
      <Router>
          <Menu/>
          <Routes>
              <Route path="/" element={<LoginPage />} />
              <Route path="/home" element={<HomePage />} />
              <Route path="/group" element={<GroupPage />} />
              <Route path="*" element={<NotFoundPage/>} />
          </Routes>
        </Router>
  );
};

export default App;
