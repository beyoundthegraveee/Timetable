import React, { useEffect, useState } from 'react';
import axios from 'axios';
import '../styles/implement.css';

const ImplementPage = () => {
    const [notifications, setNotifications] = useState([]);
    const [requests, setRequests] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8080/notifications/approval')
            .then(res => setNotifications(res.data))
            .catch(err => console.error('Failed to fetch notifications:', err));

        axios.get('http://localhost:8080/requests/accepted')
            .then(res => setRequests(res.data))
            .catch(err => console.error('Failed to fetch requests:', err));
    }, []);


    const calculateEndTime = (startTime) => {
        const [hours, minutes] = startTime.split(':').map(Number);
        const endHours = hours + 1;
        const endMinutes = minutes + 30;
        const finalHours = endHours + Math.floor(endMinutes / 60);
        const finalMinutes = endMinutes % 60;
        return `${String(finalHours).padStart(2, '0')}:${String(finalMinutes).padStart(2, '0')}`;
    };

    const handleImplementRequest = async (request) => {
        try {
            const endTime = calculateEndTime(request.classesTime);
            await axios.post(`http://localhost:8080/lessons/update-time`, {
                professorId: request.professorId,
                date: request.classesDate,
                startTime: request.classesTime,
                endTime: endTime
            });
            alert('Lesson time updated successfully.');
        } catch (err) {
            console.error('Failed to update lesson:', err);
        }
    };

    const handleImplementNotification = async (notification) => {
        try {
            await axios.post(`http://localhost:8080/groups/transfer-student`, {
                studentId: notification.studentId,
                fromGroupId: notification.currentGroup,
                toGroupName: notification.targetGroup
            });
            alert('Student transferred successfully.');
        } catch (err) {
            console.error('Failed to transfer student:', err);
        }
    };

    return (
        <div className="implement-background">
            <div className="implement-page">
                <h2>Implement Approved Changes</h2>

                <div className="implement-section">
                    <h3>Requests (ACCEPTED)</h3>
                    {requests.map(r => (
                        <div key={r.id} className="implement-item">
                            <p>{r.description} ({r.classesDate} at {r.classesTime})</p>
                            <button onClick={() => handleImplementRequest(r)}>Implement</button>
                        </div>
                    ))}
                </div>

                <div className="implement-section">
                    <h3>Notifications (APPROVAL)</h3>
                    {notifications.map(n => (
                        <div key={n.id} className="implement-item">
                            <p>{n.description} (From Group {n.currentGroup} → To Group {n.targetGroup})</p>
                            <button onClick={() => handleImplementNotification(n)}>Implement</button>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default ImplementPage;