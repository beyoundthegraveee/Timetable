import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "../styles/create_not.css";
import Cookies from "js-cookie";
import '../styles/modal.css';
import Modal from 'react-modal';

const CreateNotificationPage = () => {
    const [targetGroupName, setTargetGroupName] = useState("");
    const [description, setDescription] = useState("");
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [errorMessage, setErrorMessage] = useState("");
    const currentGroup = Cookies.get("groupId");
    const user = Cookies.get("user");
    const studentId = user ? JSON.parse(user).id : null;
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        setErrorMessage("");

        try {
            const res = await axios.get(`http://localhost:8080/groups/id/${targetGroupName}`);
            const targetGroupId = res.data;
            if (!targetGroupId) {
                setErrorMessage("Target group not found");
                return;
            }

            const notification = {
                studentId: parseInt(studentId),
                currentGroup: parseInt(currentGroup),
                targetGroup: parseInt(targetGroupId),
                description: description,
                notificationStatus: "POSTED"
            };
            await axios.post("http://localhost:8080/notifications/create", notification);
            setIsModalOpen(true);
        } catch (err) {
            if (err.response && err.response.status === 404) {
                setErrorMessage("Group with that name does not exist.");
            } else {
                setErrorMessage("An error occurred while creating notification.");
                console.error("Error creating notification", err);
            }
        }
    };

    const closeModal = () => {
        setIsModalOpen(false);
        navigate("/notification");
    };

    return (
        <div className="notification-page">
            <div className="notification-container">
                <h2>Create New Notification</h2>
                <form onSubmit={handleSubmit} className="notification-form">
                    <label>Target Group Name:</label>
                    <input
                        type="text"
                        value={targetGroupName}
                        onChange={(e) => setTargetGroupName(e.target.value)}
                        required
                    />

                    <label>Description (optional):</label>
                    <textarea
                        value={description}
                        onChange={(e) => setDescription(e.target.value)}
                    />

                    <button type="submit">Submit Notification</button>
                    {errorMessage && <p className="error-message">{errorMessage}</p>}
                </form>
            </div>
            <Modal
                isOpen={isModalOpen}
                onRequestClose={closeModal}
                ariaHideApp={false}
                contentLabel="Notification Created"
                className="modal"
                overlayClassName="modal-overlay"
            >
                <h2>Notification Created Successfully!</h2>
                <button onClick={closeModal}>OK</button>
            </Modal>
        </div>

    );
};

export default CreateNotificationPage;