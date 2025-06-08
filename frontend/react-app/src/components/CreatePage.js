import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import Modal from 'react-modal';
import '../styles/create_user.css';
import '../styles/modal.css';

const CreatePage = () => {
    const [role, setRole] = useState('');
    const navigate = useNavigate();
    const [onModalClose, setOnModalClose] = useState(null);
    const [formData, setFormData] = useState({
        firstName: '', lastName: '', birthDate: '', email: '', login: '', password: '',
        studentNumber: '', nationality: '', currentSemester: '', groupId: '',
        phoneNumber: '', academicDegree: '', departmentName: '',
        employmentDate: '', accountStatus: 'ACTIVE'
    });

    const [isModalOpen, setIsModalOpen] = useState(false);
    const [modalMessage, setModalMessage] = useState('');

    const openModal = (message, callback) => {
        setModalMessage(message);
        setIsModalOpen(true);
        setOnModalClose(() => callback);
    };

    const closeModal = () => {
        setIsModalOpen(false);
        if (onModalClose) {
            onModalClose();
            setOnModalClose(null);
        }
    };

    const handleChange = (e) => {
        setFormData({...formData, [e.target.name]: e.target.value});
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.post(`http://localhost:8080/users/create/${role.toLowerCase()}`, formData);
            openModal('User created successfully.', () => navigate('/group'));
        } catch (err) {
            console.error('Creation failed:', err);
            openModal('User creation failed.', () => navigate('/group'));
        }
    };

    return (
        <div className="create-background">
            <div className="create-user-page">
                <h2>Create New User</h2>
                <form onSubmit={handleSubmit}>
                    <label>Role:
                        <select value={role} onChange={(e) => setRole(e.target.value)} required>
                            <option value="">-- Select role --</option>
                            <option value="STUDENT">Student</option>
                            <option value="PROFESSOR">Professor</option>
                            <option value="ADMIN">Administrator</option>
                        </select>
                    </label>

                    <label>First Name: <input name="firstName" onChange={handleChange} required /></label>
                    <label>Last Name: <input name="lastName" onChange={handleChange} required /></label>
                    <label>Birth Date: <input type="date" name="birthDate" onChange={handleChange} required /></label>
                    <label>Email: <input name="email" onChange={handleChange} required /></label>
                    <label>Login: <input name="login" onChange={handleChange} required /></label>
                    <label>Password: <input name="password" onChange={handleChange} required /></label>

                    {role === 'STUDENT' && (
                        <>
                            <label>Student Number: <input name="studentNumber" onChange={handleChange} required /></label>
                            <label>Nationality: <input name="nationality" onChange={handleChange} required /></label>
                            <label>Current Semester: <input name="currentSemester" type="number" onChange={handleChange} required /></label>
                            <label>Group ID: <input name="groupId" type="number" onChange={handleChange} required /></label>
                        </>
                    )}

                    {role === 'PROFESSOR' && (
                        <>
                            <label>Phone Number: <input name="phoneNumber" onChange={handleChange} required /></label>
                            <label>Academic Degree: <input name="academicDegree" onChange={handleChange} required /></label>
                            <label>Department Name: <input name="departmentName" onChange={handleChange} required /></label>
                            <label>Employment Date: <input type="date" name="employmentDate" onChange={handleChange} required /></label>
                        </>
                    )}

                    {role === 'ADMIN' && (
                        <>
                            <label>Employment Date: <input type="date" name="employmentDate" onChange={handleChange} required /></label>
                            <label>Account Status:
                                <select name="accountStatus" onChange={handleChange}>
                                    <option value="ACTIVE">ACTIVE</option>
                                    <option value="SUSPENDED">SUSPENDED</option>
                                </select>
                            </label>
                        </>
                    )}
                    <button type="submit">Create</button>
                </form>
            </div>

            <Modal
                isOpen={isModalOpen}
                onRequestClose={closeModal}
                ariaHideApp={false}
                contentLabel="User Creation Result"
                className="modal"
                overlayClassName="modal-overlay"
            >
                <h3>{modalMessage}</h3>
                <button onClick={closeModal}>Close</button>
            </Modal>
        </div>
    );
};

export default CreatePage;
