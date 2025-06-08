import React, { useEffect, useState } from "react";
import axios from "axios";
import "../styles/notification.css";
import Cookies from "js-cookie";
import { useNavigate } from "react-router-dom";

const NotificationPage = () => {
    const [notifications, setNotifications] = useState([]);
    const navigate = useNavigate();
    const user = Cookies.get("user");
    const studentId = user ? JSON.parse(user).id : null;
    console.log('student id:', studentId);

    useEffect(() => {
        axios.get(`http://localhost:8080/notifications/student/${studentId}`)
            .then(res => setNotifications(res.data))
            .catch(err => {
                console.error("Error fetching notifications", err);
                setNotifications([]);
            });
    }, [studentId]);

    const handleCreate = () => {
        navigate("/notification/create");
    };

    return (
        <div className="notification-background">
            <div className="notification-container">
                <div className="notification-header">
                    <h1>My Notifications</h1>
                    <button className="create-button" onClick={handleCreate}>+ New Notification</button>
                </div>

                <div className="notification-scroll">
                    {notifications.length > 0 ? (
                        <ul className="notification-list">
                            {notifications.map(n => (
                                <li key={n.id} className="notification-item">
                                    📣 <strong>Status: {n.notificationStatus}</strong><br />
                                    From: {n.currentGroup} → To: {n.targetGroup}<br />
                                    Message: {n.description}
                                </li>
                            ))}
                        </ul>
                    ) : (
                        <p>No notifications found.</p>
                    )}
                </div>
            </div>
        </div>
    );
};

export default NotificationPage;