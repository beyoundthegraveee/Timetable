import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/login.css';
import '../styles/modal.css';
import Cookies from 'js-cookie';
import Modal from 'react-modal';
import axios from 'axios';
import { useRole } from '../context/RoleContext';

const LoginPage = () => {
    const [login, setLogin] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const [isModalOpen, setIsModalOpen] = useState(false);
    const navigate = useNavigate();
    const { setRole } = useRole();

    const handleSubmit = async (event) => {
        event.preventDefault();
        const User = {
            login: login,
            password: password,
        };
        try {
            const response = await axios.post('http://localhost:8080/users/login', User);
            if (response.status === 200) {
                const userData = response.data.user;
                const groupId = response.data.groupId ?? null;
                Cookies.set('user', JSON.stringify(userData), { expires: 1 });
                Cookies.set('role', userData.role, { expires: 1 });
                Cookies.set('userId', userData.id.toString(), { expires: 1 });
                if (groupId !== null) {
                    Cookies.set('groupId', groupId.toString(), { expires: 1 });
                }
                setRole(mapServerRole(userData.role));
                setIsModalOpen(true);
            } else {
                setError(response.data.message || 'Login failed');
            }
        } catch (error) {
            setError('An error occurred. Please try again later.');
            console.error("Login error:", error);
        }
    };

    const closeModal = () => {
        handleButtonClick();
        setIsModalOpen(false);
    };

    const handleButtonClick = () => {
        navigate(`/group`);
    };

    const mapServerRole = (serverRole) => {
        switch (serverRole) {
            case 'ADMINISTRATOR':
                return 'admin';
            case 'STUDENT':
                return 'student';
            case 'PROFESSOR':
                return 'professor';
            default:
                return 'guest';
        }
    };

    return (
        <div className="login-page">
            <h2>Hey!</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="login">Login:</label>
                    <input
                        type="text"
                        id="login"
                        value={login}
                        onChange={(e) => setLogin(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="password">Password:</label>
                    <input
                        type="password"
                        id="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                {error && <p className="error">{error}</p>}
                <button type="submit">Sign in</button>
            </form>

            <Modal
                isOpen={isModalOpen}
                onRequestClose={closeModal}
                ariaHideApp={false}
                contentLabel="Logged in Successful"
                className="modal"
                overlayClassName="modal-overlay"
            >
                <h2>Login Successful!</h2>
                <button onClick={closeModal}>Close</button>
            </Modal>
        </div>
    );
};

export default LoginPage;