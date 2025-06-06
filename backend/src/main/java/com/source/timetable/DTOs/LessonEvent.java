package com.source.timetable.DTOs;

import com.source.timetable.models.Lesson;

public class LessonEvent {

    public String title;
    public String start;
    public String end;

    public LessonEvent(String title, String start, String end) {
        this.title = title;
        this.start = start;
        this.end = end;
    }

    public static LessonEvent fromLesson(Lesson lesson) {
        String title = lesson.getCourse().getSubject().getName();
        String start = lesson.getDateOfClass() + "T" + lesson.getStartTime();
        String end = lesson.getDateOfClass() + "T" + lesson.getEndTime();
        return new LessonEvent(title, start, end);
    }
}
