import React from 'react';
import { Link  } from 'react-router-dom';
import '../styles/menu.css'
const Menu = () => {

    return (
        <nav>
            <div className="nav-container">
                <div className="nav-links">
                    <ul className="nav-list">
                        <li><Link to="/timetable">Own timetable</Link></li>
                        <li><Link to="/other">Review other timetables</Link></li>
                        <li><Link to="/group">Check group of students</Link></li>
                        <li><Link to="/request">Send a request for a change in class time </Link></li>
                        <li><Link to="/notification">Send notification of group change</Link></li>
                        <li><Link to="/create">Create a new account</Link></li>
                        <li><Link to="/manage">Manage applications</Link></li>
                        <li><Link to="/implement">Implement changes</Link></li>
                    </ul>
                </div>
            </div>
        </nav>
    );
};

export default Menu;