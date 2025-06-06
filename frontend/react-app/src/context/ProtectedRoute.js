import React from 'react';
import { Navigate } from 'react-router-dom';
import { useRole } from './RoleContext';

const ProtectedRoute = ({ children, allowedRoles }) => {
    const { role } = useRole();

    if (role === 'guest') {
        return <Navigate to="/" replace />;
    }

    if (!allowedRoles.includes(role)) {
        return <h2 style={{ textAlign: 'center', color: 'red' }}>403 Forbidden: Access denied</h2>;
    }

    return children;
};

export default ProtectedRoute;