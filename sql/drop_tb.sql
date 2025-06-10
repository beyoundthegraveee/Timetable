USE timetable;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS 
   --  admin_access_rights,
    lessons,
    exam,
    courses,
    semesters,
    requests,
    subjects,
    notifications,
    access_rights,
    administrators,
    professors,
    students,
    group_of_students,
    users;

SET FOREIGN_KEY_CHECKS = 1;

DROP SCHEMA timetable;