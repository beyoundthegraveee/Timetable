import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';
import "../styles/request.css";
import { useNavigate } from 'react-router-dom';

const RequestPage = () => {
    const [requests, setRequests] = useState([]);
    const [subjects, setSubjects] = useState([]);
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        subjectId: '',
        date: '',
        time: '',
        description: '',
    });

    const professorId = Cookies.get('userId');

    useEffect(() => {
        axios.get("http://localhost:8080/subjects")
            .then(res => setSubjects(res.data))
            .catch(err => console.error("Failed to load subjects", err));
    }, []);

    useEffect(() => {
        if (professorId) {
            axios.get(`http://localhost:8080/requests/professor/${professorId}`)
                .then(res => setRequests(res.data))
                .catch(err => console.error('Failed to load requests', err));
        }
    }, [professorId]);

    const handleChange = (e) => {
        setFormData({...formData, [e.target.name]: e.target.value});
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const payload = {
            professorId: parseInt(professorId),
            subjectId: parseInt(formData.subjectId),
            classesDate: formData.date,
            classesTime: formData.time,
            description: formData.description,
        };

        try {
            const response = await axios.post('http://localhost:8080/requests/create', payload);
            if (response.status === 200 && response.data) {
                const created = response.data;
                const status = String(created.requestStatus).toUpperCase();

                console.log(status);

                if (status === "REJECTED") {
                    alert("Request was created, but rejected due to short notice.");
                } else {
                    alert("Request submitted successfully.");
                }
                navigate("/request");
            } else {
                alert("Something went wrong. Try again.");
            }
        } catch (err) {
            console.error("Error submitting request", err);
        }
    };

    return (
        <div className="request-background">
            <div className="request-page">
                <div className="request-list">
                    <h2>Your Requests</h2>
                    <ul>
                        {requests.map(req => (
                            <li key={req.id}>
                                {req.classesDate} {req.classesTime} — {req.description} (
                                <span
                                    className={req.requestStatus === 'REJECTED' ? 'status-rejected' : 'status-submitted'}>
                                    {req.requestStatus}
                                </span>
                                )
                            </li>
                            ))}
                    </ul>
                </div>

                <div className="request-form">
                    <h3>Create New Request</h3>
                    <form onSubmit={handleSubmit}>
                        <label>
                            Subject:
                            <select name="subjectId" value={formData.subjectId} onChange={handleChange} required>
                                <option value="">-- Select subject --</option>
                                {subjects.map(subject => (
                                    <option key={subject.id} value={subject.id}>
                                        {subject.name} ({subject.abbreviation})
                                    </option>
                                ))}
                            </select>
                        </label>
                        <label>
                            Date:
                            <input name="date" type="date" onChange={handleChange} required />
                        </label>
                        <label>
                            Time:
                            <input name="time" type="time" onChange={handleChange} required />
                        </label>
                        <label>
                            Description:
                            <input name="description" type="text" onChange={handleChange} required />
                        </label>
                        <button type="submit">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default RequestPage;