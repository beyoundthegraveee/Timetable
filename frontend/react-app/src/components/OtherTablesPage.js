import React, { useEffect, useState } from "react";
import FullCalendar from "@fullcalendar/react";
import timeGridPlugin from "@fullcalendar/timegrid";
import axios from "axios";
import "../styles/timetable.css";

const OtherTablesPage = () => {
    const [groups, setGroups] = useState([]);
    const [selectedGroupId, setSelectedGroupId] = useState(null);
    const [events, setEvents] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8080/groups")
            .then(res => setGroups(res.data))
            .catch(err => console.error("Error fetching groups", err));
    }, []);

    useEffect(() => {
        if (!selectedGroupId) return;
        axios.get(`http://localhost:8080/lessons/group/${selectedGroupId}`)
            .then(res => setEvents(res.data))
            .catch(err => console.error("Error fetching lessons", err));
    }, [selectedGroupId]);

    return (
        <div className="content timetable-page">
            <h1 className="title">Other Groups' Timetables</h1>

            <div style={{ marginBottom: "20px", textAlign: "center" }}>
                <label htmlFor="groupSelect" style={{ fontSize: "18px", marginRight: "10px" }}>
                    Select a group:
                </label>
                <select
                    id="groupSelect"
                    onChange={(e) => setSelectedGroupId(e.target.value)}
                    value={selectedGroupId || ""}
                    style={{ padding: "8px 12px", fontSize: "16px", borderRadius: "6px" }}
                >
                    <option value="">-- Choose a group --</option>
                    {groups.map(group => (
                        <option key={group.id} value={group.id}>
                            {group.name} (max: {group.maxCountOfStudents})
                        </option>
                    ))}
                </select>
            </div>

            {selectedGroupId && (
                <FullCalendar
                    plugins={[timeGridPlugin]}
                    initialView="timeGridWeek"
                    allDaySlot={false}
                    slotMinTime="06:00:00"
                    slotMaxTime="20:00:00"
                    events={events}
                    height="auto"
                    nowIndicator={true}
                    editable={false}
                    headerToolbar={{
                        start: "today prev,next",
                        center: "title",
                        end: "timeGridDay,timeGridWeek"
                    }}
                />
            )}
        </div>
    );
};

export default OtherTablesPage;
