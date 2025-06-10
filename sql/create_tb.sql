Create database timetable;
use timetable;

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    email VARCHAR(255) NOT NULL,
    login VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMINISTRATOR', 'STUDENT', 'PROFESSOR') NOT NULL
);

CREATE TABLE group_of_students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    max_count_of_students INT NOT NULL
);

CREATE TABLE students (
    id INT PRIMARY KEY,
    student_number VARCHAR(100) NOT NULL UNIQUE,
    nationality VARCHAR(100) NOT NULL,
    current_semester INT NOT NULL,
    group_id INT NOT NULL,
    FOREIGN KEY (id) REFERENCES users(id),
    FOREIGN KEY (group_id) REFERENCES group_of_students(id)
);

CREATE TABLE professors (
    id INT PRIMARY KEY,
    employment_date DATE NOT NULL,
    phone_number VARCHAR(20) NOT NULL UNIQUE,
    academic_degree VARCHAR(100) NOT NULL,
    department_name VARCHAR(100) NOT NULL,
    FOREIGN KEY (id) REFERENCES users(id)
);

CREATE TABLE administrators (
    id INT PRIMARY KEY,
    employment_date DATE NOT NULL,
    account_status ENUM('ACTIVE', 'BLOCKED') NOT NULL,
    FOREIGN KEY (id) REFERENCES users(id)
);

CREATE TABLE access_rights (
    id INT PRIMARY KEY AUTO_INCREMENT,
    description TEXT NULL,
    creation_date DATE NOT NULL,
    administrator_id INT NOT NULL,
    FOREIGN KEY (administrator_id) REFERENCES administrators(id)
);

CREATE TABLE notifications (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    current_group INT NOT NULL,
    target_group INT NOT NULL,
    notification_status ENUM('POSTED', 'APPROVAL', 'DENIED', 'IMPLEMENTED') NOT NULL,
    description TEXT NULL,
    FOREIGN KEY (student_id) REFERENCES students(id),
    FOREIGN KEY (current_group) REFERENCES group_of_students(id),
    FOREIGN KEY (target_group) REFERENCES group_of_students(id)
);

CREATE TABLE subjects (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE,
    abbreviation VARCHAR(50) NOT NULL UNIQUE,
    number_of_ect INT NOT NULL,
    subject_type ENUM('MANDATORY', 'ADDITIONAL') NOT NULL
);

CREATE TABLE requests (
    id INT PRIMARY KEY AUTO_INCREMENT,
    classes_date DATE NOT NULL,
    classes_time TIME NOT NULL,
    request_status ENUM('SUBMITTED', 'ACCEPTED', 'REJECTED', 'IMPLEMENTED') NOT NULL,
    professor_id INT NOT NULL,
    subject_id INT NOT NULL,
    description TEXT NULL,
    FOREIGN KEY (professor_id) REFERENCES professors(id),
    FOREIGN KEY (subject_id) REFERENCES subjects(id)
);

CREATE TABLE semesters (
    id INT PRIMARY KEY AUTO_INCREMENT,
    academic_year VARCHAR(9) NOT NULL,
    number_of_semester INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    session_start_date DATE NOT NULL,
    session_end_date DATE NOT NULL
);

CREATE TABLE courses (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    subject_id INT NOT NULL,
    semester_id INT NOT NULL,
    FOREIGN KEY (subject_id) REFERENCES subjects(id),
    FOREIGN KEY (semester_id) REFERENCES semesters(id)
);

CREATE TABLE exams (
    id INT PRIMARY KEY AUTO_INCREMENT,
    course_id INT NOT NULL UNIQUE,
    exam_date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    exam_type ENUM('STATIONARY', 'REMOTE') NOT NULL,
    max_points INT NOT NULL,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);

CREATE TABLE lessons (
    id INT PRIMARY KEY AUTO_INCREMENT,
    course_id INT NOT NULL,
    group_id INT NOT NULL,
    professor_id INT NOT NULL,
    date_of_class DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    building VARCHAR(100) NOT NULL,
    room VARCHAR(100) NOT NULL,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE,
    FOREIGN KEY (group_id) REFERENCES group_of_students(id),
    FOREIGN KEY (professor_id) REFERENCES professors(id)
);

-- CREATE TABLE admin_access_rights (
--     admin_id INT NOT NULL,
--     access_right_id INT NOT NULL,
--     PRIMARY KEY (admin_id, access_right_id),
--     FOREIGN KEY (admin_id) REFERENCES administrators(id),
--     FOREIGN KEY (access_right_id) REFERENCES access_rights(id)
-- );
