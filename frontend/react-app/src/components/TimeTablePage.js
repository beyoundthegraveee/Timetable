import React, {useEffect, useState} from "react";
import FullCalendar from "@fullcalendar/react";
import timeGridPlugin from "@fullcalendar/timegrid";
import axios from "axios";
import "../styles/timetable.css";

const TimeTablePage = ({ groupId = 1 }) => {
    const [events, setEvents] = useState([])

    useEffect(() => {
        axios.get(`http://localhost:8080/lessons/group/${groupId}`)
            .then(res => setEvents(res.data))
            .catch(err => console.error("Error fetching lessons", err));
    }, [groupId]);

    return (

            <div className="content">
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
