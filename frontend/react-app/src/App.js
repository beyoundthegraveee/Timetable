import './styles/App.css';
import {Route, BrowserRouter as Router, Routes, useLocation} from 'react-router-dom';
import LoginPage from './components/LoginPage';
import Menu from "./components/Menu";
import NotFound from "./components/NotFoundPage";
import TimeTablePage from "./components/TimeTablePage";
import OtherTablesPage from "./components/OtherTablesPage";
import GroupPage from "./components/GroupPage";
import RequestPage from "./components/RequestPage";
import NotificationPage from "./components/NotificationPage";
import CreatePage from "./components/CreatePage";
import ManagePage from "./components/ManagePage";
import ImplementPage from "./components/ImplementPage";
import { RoleProvider } from './context/RoleContext';
import ProtectedRoute from './context/ProtectedRoute';

const AppContent = () => {
    const location = useLocation();
    const hideMenuRoutes = ['/login'];

    return (
        <div className="app-wrapper">
            {!hideMenuRoutes.includes(location.pathname) && <Menu />}
            <div className="page-content">
                <Routes>
                    <Route path="/" element={<LoginPage />} />
                    <Route
                        path="/timetable"
                        element={
                            <ProtectedRoute allowedRoles={['student', 'professor']}>
                                <TimeTablePage />
                            </ProtectedRoute>
                        }
                    />

                    <Route
                        path="/other"
                        element={
                            <ProtectedRoute allowedRoles={['student', 'professor','admin']}>
                                <OtherTablesPage />
                            </ProtectedRoute>
                        }
                    />

                    <Route
                        path="/group"
                        element={
                            <ProtectedRoute allowedRoles={['student', 'professor','admin']}>
                                <GroupPage />
                            </ProtectedRoute>
                        }
                    />

                    <Route
                        path="/request"
                        element={
                            <ProtectedRoute allowedRoles={['professor']}>
                                <RequestPage />
                            </ProtectedRoute>
                        }
                    />

                    <Route
                        path="/notification"
                        element={
                            <ProtectedRoute allowedRoles={['student']}>
                                <NotificationPage />
                            </ProtectedRoute>
                        }
                    />

                    <Route
                        path="/create"
                        element={
                            <ProtectedRoute allowedRoles={['admin']}>
                                <CreatePage />
                            </ProtectedRoute>
                        }
                    />

                    <Route
                        path="/manage"
                        element={
                            <ProtectedRoute allowedRoles={['admin']}>
                                <ManagePage />
                            </ProtectedRoute>
                        }
                    />

                    <Route
                        path="/implement"
                        element={
                            <ProtectedRoute allowedRoles={['admin']}>
                                <ImplementPage />
                            </ProtectedRoute>
                        }
                    />
                    <Route path="*" element={<NotFound />} />
                </Routes>
            </div>
        </div>
    );
};

const App = () => (
    <RoleProvider>
        <Router>
            <AppContent />
        </Router>
    </RoleProvider>
);

export default App;
