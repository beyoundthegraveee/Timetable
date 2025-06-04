import React from 'react';
import { Link, useNavigate  } from 'react-router-dom';

const Menu = () => {
    const navigate = useNavigate();

    const handleLogout = (event) => {
        event.preventDefault();
    };

    return (
        <nav>
            <div className="nav-container">
                <div className="nav-links">
                    <ul className="nav-list">
                        <li><Link to="/timetable">Timetable</Link></li>
                        <li><Link to="/group">Check group of students</Link></li>
                    </ul>
                </div>
            </div>
        </nav>
    );
};

export default Menu;