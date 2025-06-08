import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Modal from 'react-modal';
import '../styles/implement.css';
import '../styles/modal.css';

const ImplementPage = () => {
    const [notifications, setNotifications] = useState([]);
    const [requests, setRequests] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [modalMessage, setModalMessage] = useState('');

    const fetchData = async () => {
        try {
            const [notificationsRes, requestsRes] = await Promise.all([
                axios.get('http://localhost:8080/notifications/approval'),
                axios.get('http://localhost:8080/requests/accepted')
            ]);
            setNotifications(notificationsRes.data);
            setRequests(requestsRes.data);
        } catch (err) {
            console.error('Failed to fetch data:', err);
        }
    };

    useEffect(() => {
        fetchData();
    }, []);

    const calculateEndTime = (startTime) => {
        const [hours, minutes] = startTime.split(':').map(Number);
        const endHours = hours + 1;
        const endMinutes = minutes + 30;
        const finalHours = endHours + Math.floor(endMinutes / 60);
        const finalMinutes = endMinutes % 60;
        return `${String(finalHours).padStart(2, '0')}:${String(finalMinutes).padStart(2, '0')}`;
    };

    const openModal = (message) => {
        setModalMessage(message);
        setIsModalOpen(true);
    };

    const closeModal = () => {
        setIsModalOpen(false);
        setModalMessage('');
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
            openModal('Lesson time updated successfully.');
            fetchData();
        } catch (err) {
            console.error('Failed to update lesson:', err);
            openModal('Failed to update lesson.');
        }
    };

    const handleImplementNotification = async (notification) => {
        try {
            await axios.post(`http://localhost:8080/groups/transfer-student`, {
                studentId: notification.studentId,
                fromGroupId: notification.currentGroup,
                toGroupId: notification.targetGroup
            });
            openModal('Student transferred successfully.');
            fetchData();
        } catch (err) {
            console.error('Failed to transfer student:', err);
            openModal('Failed to transfer student.');
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

            <Modal
                isOpen={isModalOpen}
                onRequestClose={closeModal}
                ariaHideApp={false}
                contentLabel="Implementation Result"
                className="modal"
                overlayClassName="modal-overlay"
            >
                <h2>{modalMessage}</h2>
                <button onClick={closeModal}>Close</button>
            </Modal>
        </div>
    );
};

export default ImplementPage;