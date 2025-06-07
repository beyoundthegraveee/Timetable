import React, { useEffect, useState } from "react";
import axios from "axios";
import '../styles/group.css';

const GroupPage = () => {
    const [groups, setGroups] = useState([]);
    const [selectedGroup, setSelectedGroup] = useState(null);
    const [students, setStudents] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8080/groups")
            .then(res => {
                setGroups(res.data);
            })
            .catch(err => console.error("Error fetching groups", err));
    }, []);

    const handleGroupClick = (groupName) => {
        setSelectedGroup(groupName);
        axios.get(`http://localhost:8080/groups/name/${groupName}`)
            .then(res => setStudents(res.data))
            .catch(err => {
                setStudents([]);
                console.error("Error fetching students", err);
            });
    };

    return (
        <div className="content group-page">
            <h1 className="title">Group Viewer</h1>
            <div className="group-container">
                <section className="group-list">
                    <h2>Available Groups:</h2>
                    <ul>
                        {groups.map(group => (
                            <li key={group.id}>
                                <button className="group-button" onClick={() => handleGroupClick(group.name)}>
                                    {group.name} (max: {group.maxCountOfStudents})
                                </button>
                            </li>
                        ))}
                    </ul>
                </section>

                {selectedGroup && (
                    <section className="student-list">
                        <h2>Students in {selectedGroup}</h2>
                        {students.length > 0 ? (
                            <ul>
                                {students.map(student => (
                                    <li key={student.id}>
                                        {student.id}. {student.firstName} {student.lastName} — {student.studentNumber}
                                    </li>
                                ))}
                            </ul>
                        ) : (
                            <p>No students found in this group.</p>
                        )}
                    </section>
                )}
            </div>
        </div>
    );
};

export default GroupPage;
