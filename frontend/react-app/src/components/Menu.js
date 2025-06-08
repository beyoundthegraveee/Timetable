import React from 'react';
import {Link, useLocation, useNavigate} from 'react-router-dom';
import '../styles/menu.css'
import {useRole} from "../context/RoleContext";
import Cookies from "js-cookie";
const Menu = () => {
    const { role, setRole } = useRole();
    const navigate = useNavigate();
    const location = useLocation();


    const handleLogout = () => {
        Cookies.remove('user');
        Cookies.remove('groupId');
        localStorage.clear();
        sessionStorage.clear();
        setRole('guest');
        navigate('/');
    };

    if (location.pathname === '/') {
        return null;
    }

    return (
        <nav>
            <div className="nav-container">
                <div className="nav-links">
                    <ul className="nav-list">
                        {(role === 'student' || role === 'professor') && (
                            <li><Link to="/timetable">Own timetable</Link></li>
                        )}
                        {['student', 'professor', 'admin'].includes(role) && (
                            <li><Link to="/other">Review other timetables</Link></li>
                        )}
                        {['student', 'professor', 'admin'].includes(role) && (
                            <li><Link to="/group">Check group of students</Link></li>
                        )}
                        {role === 'professor' && (
                            <li><Link to="/request">Send a request for a change in class time</Link></li>
                        )}
                        {role === 'student' && (
                            <li><Link to="/notification">Send notification of group change</Link></li>
                        )}
                        {role === 'admin' && (
                            <>
                                <li><Link to="/create">Create a new account</Link></li>
                                <li><Link to="/manage">Manage applications</Link></li>
                                <li><Link to="/implement">Implement changes</Link></li>
                            </>
                        )}
                        <li>
                            <button className="logout-button" onClick={handleLogout}>Logout</button>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    );
};

export default Menu;