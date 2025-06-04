import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';


const LoginPage = () => {
    const [login, setLogin] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();
};

export default LoginPage;