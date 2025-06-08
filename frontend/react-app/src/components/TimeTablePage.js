import React, {useEffect, useState} from "react";
import FullCalendar from "@fullcalendar/react";
import timeGridPlugin from "@fullcalendar/timegrid";
import axios from "axios";
import "../styles/timetable.css";
import Cookies from "js-cookie";

const TimeTablePage = () => {
    const [events, setEvents] = useState([]);
    const role = Cookies.get("role");
    const groupId = Cookies.get("groupId");
    const professorId = Cookies.get("userId");

    useEffect(() => {
        const fetchLessons = async () => {
            try {
                if (role === "STUDENT" && groupId) {
                    const res = await axios.get(`http://localhost:8080/lessons/group/${groupId}`);
                    setEvents(res.data);
                } else if (role === "PROFESSOR" && professorId) {
                    const res = await axios.get(`http://localhost:8080/lessons/professor/${professorId}`);
                    setEvents(res.data);
                }
            } catch (err) {
                console.error("Error fetching lessons", err);
            }
        };

        fetchLessons();
    }, [role, groupId, professorId]);

    return (
        <div className="content timetable-page">
            <h1 className="title">TIMETABLE</h1>
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
        </div>
    );
};

export default TimeTablePage;
