import './styles/App.css';
import { Route, BrowserRouter as Router, Routes, useLocation } from 'react-router-dom';
import NotFoundPage from './components/NotFoundPage';
import LoginPage from './components/LoginPage';
import HomePage from './components/HomePage';
import GroupPage from './components/GroupPage';
import Menu from "./components/Menu";

const AppLayout = () => {
    const location = useLocation();
    const hideMenuOnPaths = ["/"];

    return (
        <>
            {!hideMenuOnPaths.includes(location.pathname) && <Menu/>}
            <Routes>
                <Route path="/" element={<LoginPage />} />
                <Route path="/home" element={<HomePage />} />
                <Route path="/group" element={<GroupPage />} />
                <Route path="*" element={<NotFoundPage />} />
            </Routes>
        </>
    );
};

const App = () => (
    <Router>
        <AppLayout />
    </Router>
);

export default App;
