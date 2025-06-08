import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Modal from 'react-modal';
import '../styles/manage.css';
import '../styles/modal.css';
import { useNavigate } from 'react-router-dom';


const ManagePage = () => {
    const [notifications, setNotifications] = useState([]);
    const [requests, setRequests] = useState([]);
    const [selectedItem, setSelectedItem] = useState(null);
    const [type, setType] = useState('');
    const navigate = useNavigate();
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [modalMessage, setModalMessage] = useState('');

    const openModal = (message) => {
        setModalMessage(message);
        setIsModalOpen(true);
    };

    const closeModal = () => {
        setIsModalOpen(false);
    };

    useEffect(() => {
        axios.get('http://localhost:8080/notifications/pending')
            .then(res => setNotifications(res.data))
            .catch(err => console.error('Failed to fetch notifications:', err));

        axios.get('http://localhost:8080/requests/submitted')
            .then(res => setRequests(res.data))
            .catch(err => console.error('Failed to fetch requests:', err));
    }, []);

    const handleSelect = (item, itemType) => {
        setSelectedItem(item);
        setType(itemType);
    };

    const handleUpdateStatus = async (action) => {
        if (!selectedItem || !type) return;

        let status;
        if (type === 'notification') {
            status = action === 'APPROVE' ? 'APPROVAL' : 'DENIED';
        } else if (type === 'request') {
            status = action === 'APPROVE' ? 'ACCEPTED' : 'REJECTED';
        }
        try {
            const url = type === 'notification'
                ? `http://localhost:8080/notifications/${selectedItem.id}/status`
                : `http://localhost:8080/requests/${selectedItem.id}/status`;

            await axios.patch(url, { status });
            if (type === 'notification') {
                const res = await axios.get('http://localhost:8080/notifications/pending');
                setNotifications(res.data);
            } else if (type === 'request') {
                const res = await axios.get('http://localhost:8080/requests/submitted');
                setRequests(res.data);
            }
            setSelectedItem(null);
            setType('');

            openModal(`${type.charAt(0).toUpperCase() + type.slice(1)} updated successfully.`);
        } catch (err) {
            console.error('Failed to update status:', err);
            openModal('Update failed.');
        }
    };

    return (
        <div className="manage-background">
            <div className="manage-page">
                <div className="list-container">
                    <div className="list-section">
                        <h3>Notifications from Students</h3>
                        <div className="scrollable">
                            {notifications.map(n => (
                                <div key={n.id} className="item" onClick={() => handleSelect(n, 'notification')}>
                                    [{n.notificationStatus}] {n.description}
                                </div>
                            ))}
                        </div>
                    </div>

                    <div className="list-section">
                        <h3>Requests from Professors</h3>
                        <div className="scrollable">
                            {requests.map(r => (
                                <div key={r.id} className="item" onClick={() => handleSelect(r, 'request')}>
                                    [{r.requestStatus}] {r.description}
                                </div>
                            ))}
                        </div>
                    </div>
                </div>

                {selectedItem && (
                    <div className="action-panel">
                        <h4>Selected: {selectedItem.description}</h4>
                        <div className="button-group">
                            <button className="approve" onClick={() => handleUpdateStatus('APPROVE')}>Approve</button>
                            <button className="reject" onClick={() => handleUpdateStatus('REJECT')}>Reject</button>
                        </div>
                    </div>
                )}
            </div>
            <Modal
                isOpen={isModalOpen}
                onRequestClose={closeModal}
                ariaHideApp={false}
                contentLabel="Status Update"
                className="modal"
                overlayClassName="modal-overlay"
            >
                <h3>{modalMessage}</h3>
                <button onClick={() => {
                    closeModal();
                    navigate("/manage");
                }}>
                    Close
                </button>
            </Modal>
        </div>
    );
};

export default ManagePage;
