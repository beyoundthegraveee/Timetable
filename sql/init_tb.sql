use timetable;

INSERT INTO users (id, first_name, last_name, birth_date, email, login, password, role) VALUES
(1, 'Alice', 'Admin', '1980-01-10', 'alice.admin@example.com', 'alice_admin', 'pass123', 'ADMINISTRATOR'),
(2, 'Bob', 'Student', '2002-03-15', 'bob.student@example.com', 'bob_student', 'pass123', 'STUDENT'),
(3, 'Carol', 'Professor', '1975-06-21', 'carol.prof@example.com', 'carol_prof', 'pass123', 'PROFESSOR'),
(4, 'David', 'Nowak', '2001-07-11', 'david.nowak@example.com', 'david_n', 'pass123', 'STUDENT'),
(5, 'Eva', 'Kowalska', '2000-05-19', 'eva.k@example.com', 'eva_k', 'pass123', 'STUDENT'),
(6, 'Frank', 'Zielinski', '2002-08-23', 'frank.z@example.com', 'frank_z', 'pass123', 'STUDENT'),
(7, 'Grace', 'Wójcik', '1999-12-02', 'grace.w@example.com', 'grace_w', 'pass123', 'STUDENT'),
(8, 'Henry', 'Kaminski', '2003-03-10', 'henry.k@example.com', 'henry_k', 'pass123', 'STUDENT'),
(9, 'Isabella', 'Mazur', '2001-10-27', 'isabella.m@example.com', 'isabella_m', 'pass123', 'STUDENT'),
(10, 'Jack', 'Lewandowski', '2000-11-04', 'jack.l@example.com', 'jack_l', 'pass123', 'STUDENT'),
(11, 'Klara', 'Szymczak', '2002-09-15', 'klara.s@example.com', 'klara_s', 'pass123', 'STUDENT'),
(12, 'Leo', 'Grabowski', '2001-06-30', 'leo.g@example.com', 'leo_g', 'pass123', 'STUDENT'),
(13, 'Maya', 'Dąbrowska', '2003-01-20', 'maya.d@example.com', 'maya_d', 'pass123', 'STUDENT'),
(14, 'Nina', 'Baran', '2002-02-14', 'nina.b@example.com', 'nina_b', 'pass123', 'STUDENT'),
(15, 'Oskar', 'Witkowski', '2001-09-25', 'oskar.w@example.com', 'oskar_w', 'pass123', 'STUDENT');

INSERT INTO group_of_students (id, name, max_count_of_students) VALUES
(1, 'Group A', 30),
(2, 'Group B', 25),
(3, 'Group C', 28),
(4, 'Group D', 32),
(5, 'Group E', 20),
(6, 'Group F', 27),
(7, 'Group G', 24),
(8, 'Group H', 26);

INSERT INTO students (id, student_number, nationality, current_semester, group_id) VALUES
(2, 'S2023-001', 'Polish', 3, 1),
(4, 'S2023-002', 'Polish', 2, 1),
(5, 'S2023-003', 'German', 1, 1),
(6, 'S2023-004', 'Ukrainian', 4, 1),
(7, 'S2023-005', 'Polish', 3, 1),
(8, 'S2023-006', 'Belarusian', 2, 1),
(9, 'S2023-007', 'Polish', 1, 1),
(10, 'S2023-008', 'Polish', 5, 1),
(11, 'S2023-009', 'Ukrainian', 3, 1),
(12, 'S2023-010', 'Polish', 2, 1),
(13, 'S2023-011', 'German', 1, 2),
(14, 'S2023-012', 'Polish', 2, 3),
(15, 'S2023-013', 'Polish', 3, 4);

INSERT INTO professors (id, employment_date, phone_number, academic_degree, department_name) VALUES
(3, '2010-09-01', '+48123123123', 'PhD', 'Computer Science');

INSERT INTO administrators (id, employment_date, account_status) VALUES
(1, '2015-05-15', 'ACTIVE');

INSERT INTO access_rights (id, description, creation_date, administrator_id) VALUES
(1, 'Can manage schedules', '2024-01-01', 1),
(2, 'Can manage users', '2024-02-01', 1);

-- INSERT INTO admin_access_rights (admin_id, access_right_id) VALUES
-- (1, 1),
-- (1, 2);

INSERT INTO notifications (student_id, current_group, target_group, notification_status, description) VALUES
(2, 1, 2, 'POSTED', 'Student requests to transfer to another group.'),
(2, 1, 2, 'POSTED', 'Student requests to transfer to another group.'),
(2, 1, 3, 'APPROVAL', 'Student wishes to join a more advanced group.'),
(2, 1, 4, 'DENIED', 'Request to transfer to Group D was rejected.'),
(7, 2, 1, 'POSTED', 'Change of group due to schedule conflict.'),
(7, 2, 3, 'APPROVAL', 'Student accepted into Group C.'),
(7, 2, 4, 'DENIED', 'Student cannot join Group D.'),
(4, 1, 2, 'POSTED', 'Wants to be transferred to Group B.'),
(4, 1, 3, 'POSTED', 'Student interested in switching to Group C.'),
(4, 1, 4, 'APPROVAL', 'Request approved for Group D.'),
(5, 1, 2, 'POSTED', 'Needs to change group due to personal reasons.'),
(5, 1, 3, 'APPROVAL', 'Approved to join Group C.'),
(5, 1, 4, 'DENIED', 'Group D is full, request denied.'),
(6, 1, 2, 'POSTED', 'Student applied for a group change.'),
(6, 1, 3, 'APPROVAL', 'Group C change accepted.'),
(6, 1, 4, 'DENIED', 'Cannot assign student to Group D.'),
(2, 1, 5, 'POSTED', 'Request to move to Group E.'),
(7, 2, 5, 'APPROVAL', 'Approved change to Group E.'),
(4, 1, 5, 'DENIED', 'Request to join Group E denied.'),
(5, 1, 5, 'POSTED', 'Applying for transfer to Group E.'),
(6, 1, 5, 'APPROVAL', 'Moved to Group E as requested.');


INSERT INTO subjects (id, name, abbreviation, number_of_ect, subject_type) VALUES
(1, 'Databases', 'DB', 5, 'MANDATORY'),
(2, 'Computer Networks', 'NET', 4, 'ADDITIONAL'),
(3, 'Operating Systems', 'OS', 5, 'MANDATORY'),
(4, 'Software Engineering', 'SE', 6, 'MANDATORY'),
(5, 'Artificial Intelligence', 'AI', 5, 'ADDITIONAL'),
(6, 'Web Development', 'WEB', 4, 'ADDITIONAL');


INSERT INTO semesters (id, academic_year, number_of_semester, start_date, end_date, session_start_date, session_end_date) VALUES
(1, '2024/2025', 2, '2025-02-01', '2025-06-30', '2025-06-01', '2025-06-20');

INSERT INTO courses (id, name, subject_id, semester_id) VALUES
(1, 'DB Course Spring', 1, 1),
(2, 'Networks and Protocols', 2, 1),
(3, 'Operating Systems', 3, 1),
(4, 'Software Engineering', 4, 1),
(5, 'Artificial Intelligence', 5, 1),
(6, 'Web Development', 6, 1);

INSERT INTO exams (id, course_id, exam_date, start_time, end_time, exam_type, max_points) VALUES
(1, 1, '2025-06-15', '09:00:00', '11:00:00', 'STATIONARY', 100);

INSERT INTO lessons (id, course_id, group_id, professor_id, date_of_class, start_time, end_time, building, room) VALUES
(1, 1, 1, 3, '2025-03-03', '08:00:00', '09:30:00', 'Building A', '101'),
(2, 2, 1, 3, '2025-03-03', '10:00:00', '11:30:00', 'Building B', '102'),
(3, 3, 1, 3, '2025-03-03', '12:00:00', '13:30:00', 'Building C', '103'),
(4, 4, 1, 3, '2025-03-03', '14:00:00', '15:30:00', 'Building D', '104'),

(5, 1, 1, 3, '2025-06-02', '08:00:00', '09:30:00', 'Building A', '101'),
(6, 2, 1, 3, '2025-06-02', '10:00:00', '11:30:00', 'Building B', '102'),

(7, 3, 1, 3, '2025-06-03', '08:00:00', '09:30:00', 'Building C', '103'),
(8, 4, 1, 3, '2025-06-03', '10:00:00', '11:30:00', 'Building D', '104'),

(9, 1, 1, 3, '2025-06-04', '08:00:00', '09:30:00', 'Building A', '101'),
(10, 2, 1, 3, '2025-06-04', '10:00:00', '11:30:00', 'Building B', '102'),

(11, 3, 1, 3, '2025-06-05', '08:00:00', '09:30:00', 'Building C', '103'),
(12, 4, 1, 3, '2025-06-05', '10:00:00', '11:30:00', 'Building D', '104'),

(13, 1, 1, 3, '2025-06-06', '08:00:00', '09:30:00', 'Building A', '101'),
(14, 2, 1, 3, '2025-06-06', '10:00:00', '11:30:00', 'Building B', '102');

INSERT INTO lessons (id, course_id, group_id, professor_id, date_of_class, start_time, end_time, building, room) VALUES
(15, 3, 1, 3, '2025-06-09', '08:00:00', '09:30:00', 'Building C', '103'),
(16, 4, 1, 3, '2025-06-09', '10:00:00', '11:30:00', 'Building D', '104'),

(17, 1, 1, 3, '2025-06-10', '08:00:00', '09:30:00', 'Building A', '101'),
(18, 2, 1, 3, '2025-06-10', '10:00:00', '11:30:00', 'Building B', '102'),

(19, 3, 1, 3, '2025-06-11', '08:00:00', '09:30:00', 'Building C', '103'),
(20, 4, 1, 3, '2025-06-11', '10:00:00', '11:30:00', 'Building D', '104'),

(21, 1, 1, 3, '2025-06-12', '08:00:00', '09:30:00', 'Building A', '101'),
(22, 2, 1, 3, '2025-06-12', '10:00:00', '11:30:00', 'Building B', '102'),

(23, 3, 1, 3, '2025-06-13', '08:00:00', '09:30:00', 'Building C', '103'),
(24, 4, 1, 3, '2025-06-13', '10:00:00', '11:30:00', 'Building D', '104'),

(25, 1, 1, 3, '2025-06-16', '08:00:00', '09:30:00', 'Building A', '101'),
(26, 2, 1, 3, '2025-06-16', '10:00:00', '11:30:00', 'Building B', '102'),

(27, 3, 1, 3, '2025-06-17', '08:00:00', '09:30:00', 'Building C', '103'),
(28, 4, 1, 3, '2025-06-17', '10:00:00', '11:30:00', 'Building D', '104'),

(29, 1, 1, 3, '2025-06-18', '08:00:00', '09:30:00', 'Building A', '101'),
(30, 2, 1, 3, '2025-06-18', '10:00:00', '11:30:00', 'Building B', '102'),

(31, 3, 1, 3, '2025-06-19', '08:00:00', '09:30:00', 'Building C', '103'),
(32, 4, 1, 3, '2025-06-19', '10:00:00', '11:30:00', 'Building D', '104'),

(33, 1, 1, 3, '2025-06-20', '08:00:00', '09:30:00', 'Building A', '101'),
(34, 2, 1, 3, '2025-06-20', '10:00:00', '11:30:00', 'Building B', '102'),

(35, 3, 1, 3, '2025-06-23', '08:00:00', '09:30:00', 'Building C', '103'),
(36, 4, 1, 3, '2025-06-23', '10:00:00', '11:30:00', 'Building D', '104'),

(37, 1, 1, 3, '2025-06-24', '08:00:00', '09:30:00', 'Building A', '101'),
(38, 2, 1, 3, '2025-06-24', '10:00:00', '11:30:00', 'Building B', '102'),

(39, 3, 1, 3, '2025-06-25', '08:00:00', '09:30:00', 'Building C', '103'),
(40, 4, 1, 3, '2025-06-25', '10:00:00', '11:30:00', 'Building D', '104'),

(41, 1, 1, 3, '2025-06-26', '08:00:00', '09:30:00', 'Building A', '101'),
(42, 2, 1, 3, '2025-06-26', '10:00:00', '11:30:00', 'Building B', '102'),

(43, 3, 1, 3, '2025-06-27', '08:00:00', '09:30:00', 'Building C', '103'),
(44, 4, 1, 3, '2025-06-27', '10:00:00', '11:30:00', 'Building D', '104');

INSERT INTO requests (id, classes_date, classes_time, request_status, professor_id, subject_id, description) VALUES
(2, '2025-06-07', '09:00:00', 'SUBMITTED', 3, 2, 'Too short notice – will be rejected by logic'),
(3, '2025-06-08', '14:00:00', 'SUBMITTED', 3, 3, 'Conflict with another event'),
(4, '2025-06-12', '11:00:00', 'SUBMITTED', 3, 4, 'Reschedule due to planned maintenance'),
(5, '2025-06-14', '13:00:00', 'SUBMITTED', 3, 5, 'Prefer different room'),
(6, '2025-06-20', '08:00:00', 'SUBMITTED', 3, 6, 'Lecture overlaps with seminar');

INSERT INTO requests (id, classes_date, classes_time, request_status, professor_id, subject_id, description) VALUES
(1, '2025-06-01', '10:00:00', 'SUBMITTED', 3, 1, 'Need to reschedule class'),
(7, '2025-06-08', '09:00:00', 'ACCEPTED', 3, 2, 'Change class time due to personal conflict'),
(8, '2025-06-10', '13:30:00', 'ACCEPTED', 3, 3, 'Lecture moved due to external seminar'),
(9, '2025-06-12', '15:00:00', 'ACCEPTED', 3, 1, 'Reschedule due to public event'),
(10, '2025-06-13', '08:00:00', 'ACCEPTED', 3, 4, 'Adjust time for conference attendance'),
(11, '2025-06-14', '11:30:00', 'ACCEPTED', 3, 2, 'Reschedule for technical maintenance'),
(12, '2025-06-15', '12:00:00', 'ACCEPTED', 3, 3, 'Avoid overlap with faculty meeting');

