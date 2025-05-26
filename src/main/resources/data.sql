-- CREATE TYPE course_type_enum AS ENUM ('OBLIGATORY', 'ELECTIVE');
-- CREATE TYPE course_priority_enum AS ENUM ('HIGH', 'MEDIUM', 'LOW');
-- CREATE TYPE room_type_enum AS ENUM ('LAB', 'CLASSROOM');
-- CREATE TYPE day_enum AS ENUM ('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY');
-- CREATE TYPE teaching_mode_enum AS ENUM ('ONLINE', 'CLASSROOM');
--
-- CREATE TABLE time_slots (
--     id SERIAL PRIMARY KEY,
--     name VARCHAR(20) NOT NULL,
--     start_time TIME NOT NULL,
--     end_time TIME NOT NULL
-- );
--
--
--
-- CREATE TABLE groups (
--     id BIGSERIAL PRIMARY KEY,
--     name VARCHAR(255) NOT NULL
-- );
--
-- CREATE TABLE teachers (
--     id BIGSERIAL PRIMARY KEY,
--     name VARCHAR(255) NOT NULL,
--     daily_hours INT NOT NULL,
--     weekly_hours INT NOT NULL
-- );
--
-- CREATE TABLE courses (
--     id BIGSERIAL PRIMARY KEY,
--     name VARCHAR(255) NOT NULL,
--     credits INT NOT NULL
-- );
--
-- CREATE TABLE rooms (
--     id BIGSERIAL PRIMARY KEY,
--     room_num VARCHAR(255) NOT NULL,
--     type room_type_enum NOT NULL
-- );
--
-- CREATE TABLE setupItem (
--     id BIGSERIAL PRIMARY KEY,
--
--     group_id BIGINT NOT NULL REFERENCES groups(id) ON DELETE CASCADE,
--
--     course_id BIGINT NOT NULL REFERENCES courses(id) ON DELETE CASCADE,
--     teacher_id BIGINT NOT NULL REFERENCES teachers(id) ON DELETE CASCADE,
--     course_priority course_type_enum NOT NULL,
--     hours_a_week INT NOT NULL,
--     hours_total INT NOT NULL,
--     weeks_total INT NOT NULL,
--     hours_in_lab INT NOT NULL,
--     preferred_room_type room_type_enum NOT NULL
-- );
--
-- CREATE TABLE setup_shared (
--     id BIGSERIAL PRIMARY KEY,
--
--     name VARCHAR(255) NOT NULL,
--
--     course_id BIGINT NOT NULL REFERENCES courses(id) ON DELETE CASCADE,
--     teacher_id BIGINT NOT NULL REFERENCES teachers(id) ON DELETE CASCADE,
--     course_priority course_type_enum NOT NULL,
--     hours_a_week INT NOT NULL,
--     hours_total INT NOT NULL,
--     weeks_total INT NOT NULL,
--     hours_in_lab INT NOT NULL,
--     preferred_room_type room_type_enum NOT NULL
-- );
--
-- CREATE TABLE shared_groups (
--     id BIGSERIAL PRIMARY KEY,
--     setup_shared_id BIGINT NOT NULL REFERENCES setup_shared(id) ON DELETE CASCADE,
--     group_id BIGINT NOT NULL REFERENCES groups(id) ON DELETE CASCADE
--
-- );
--
--
-- CREATE TABLE scheduleItem (
--     id BIGSERIAL PRIMARY KEY,
--     setup_id BIGINT NOT NULL REFERENCES setupItem(id) ON DELETE CASCADE,
--     room_id BIGINT NOT NULL REFERENCES rooms(id) ON DELETE CASCADE,
--     day day_enum NOT NULL,
--     start_time_slot_id INT NOT NULL REFERENCES time_slots(id) ON DELETE CASCADE,
--     teaching_mode teaching_mode_enum NOT NULL
-- );
--
--
-- CREATE TABLE schedule_shared (
--     id BIGSERIAL PRIMARY KEY,
--     setup_shared_id BIGINT NOT NULL REFERENCES setup_shared(id) ON DELETE CASCADE,
--     room_id BIGINT NOT NULL REFERENCES rooms(id) ON DELETE CASCADE,
--     day day_enum NOT NULL,
--     start_time_slot_id INT NOT NULL REFERENCES time_slots(id) ON DELETE CASCADE,
--     teaching_mode teaching_mode_enum NOT NULL
-- );
--
--
--
-- CREATE TABLE schedule_lunch (
--     id BIGSERIAL PRIMARY KEY,
--     group_id BIGINT NOT NULL REFERENCES groups(id),
--
--     day day_enum NOT NULL,
--     start_time_slot_id INT NOT NULL REFERENCES time_slots(id) ON DELETE CASCADE,
--     end_time_slot_id INT NOT NULL REFERENCES time_slots(id) ON DELETE CASCADE
-- );

INSERT INTO schedule (name, user_id) VALUES
    ('My first schedule', 1);


INSERT INTO schedule_version (name, schedule_id) VALUES
    ('My first schedule version', 1);

INSERT INTO course (name, credits, schedule_id) VALUES
    ('English', 3, 1), ('Japan', 3, 1), ('French', 3, 1), ('German', 3, 1),
    ('Discrete Math', 4, 1), ('Calculus 2', 4, 1), ('Design and Analysis of Algorithms', 4, 1),
    ('Geography', 2, 1), ('Backend', 4, 1), ('Ethnic Hacking and Penetration Testing', 4, 1),
    ('Physical Education', 2, 1), ('Design Thinking for Product Solutions', 3, 1),
    ('Philosophy', 3, 1), ('Basics of Science Research', 3, 1), ('Manas Tanuu', 2, 1),
    ('Turkish Language II', 3, 1), ('Data Analysis and Visualization', 4, 1),
    ('The Art of Teaching and Methods of Teaching Computer Science', 4, 1),
    ('Industrial Internship', 6, 1), ('Pre-qualificational Internship', 6, 1),
    ('Russian Language', 3, 1), ('Computer Architecture and Operating Systems', 4, 1),
    ('Digital Electronics', 4, 1), ('Business Fundamentals and Process Management', 4, 1),
    ('Programming Language 2', 4, 1), ('History', 3, 1), ('Metrology, Standardization and Certification', 4, 1),
    ('Life Safety', 3, 1), ('Russian Language for Foreign Students', 3, 1),
    ('OOP', 4, 1), ('Marketing', 3, 1), ('Kyrgyz', 3, 1),
    ('Kyrgyz Language for Foreign Students', 3, 1), ('Kyrgyz Language', 3, 1),
    ('Engineering Computer Graphics', 3, 1), ('Competitive Programming', 4, 1),
    ('Methodology and Practices for Ethical Hacking', 4, 1), ('Interpersonal Communication in IT', 3, 1),
    ('Public Speaking Skills', 3, 1), ('Human-Computer Interaction', 4, 1),
    ('Electives', 3, 1), ('Startup from Start to Launch', 4, 1), ('Computer Literacy', 3, 1),
    ('Philosophy of Technology', 3, 1), ('Fundamentals of Software Development', 4, 1),
    ('Backend Development', 4, 1), ('Computational Mathematics', 4, 1), ('Database', 4, 1),
    ('Introduction to Engineering and CS', 4, 1), ('Algebra and Geometry', 4, 1),
    ('Functional Analysis', 4, 1), ('Calculus I', 4, 1), ('Differential Equations', 4, 1),
    ('Real and Complex Analysis', 4, 1), ('Computer Networks & Telecommunication', 4, 1),
    ('Programming Languages and Methods', 4, 1), ('Theory of Numbers', 4, 1),
    ('Methods of Optimization', 4, 1), ('Educational Internship', 6, 1),
    ('Data Science and Specialty Mathematics', 4, 1), ('Digital Design', 4, 1),
    ('Cybersecurity Foundation', 4, 1), ('Frontend', 4, 1), ('Graphic Design', 4, 1),
    ('Public Speaking', 3, 1), ('VR Design', 4, 1), ('Critical Thinking', 3, 1), ('Management', 3, 1);


INSERT INTO department(name, schedule_id) VALUES
    ('Computer Science', 1),
    ('Applied Mathematics and Informatics', 1),
    ('Artificial Intelligence and Robotics', 1),
    ('Management in Information Technology', 1);


INSERT INTO room (room_num, type, size, schedule_id) VALUES
    ('B101', 'CLASSROOM', 'MEDIUM', 1),
    ('B102', 'CLASSROOM', 'MEDIUM', 1),
    ('B103', 'CLASSROOM', 'MEDIUM', 1),
    ('B104', 'CLASSROOM', 'MEDIUM', 1),
    ('B109 (APPLE LAB)', 'LAB', 'MEDIUM', 1),
    ('B201', 'CLASSROOM', 'MEDIUM', 1),
    ('B202', 'CLASSROOM', 'MEDIUM', 1),
    ('B203', 'CLASSROOM', 'MEDIUM', 1),
    ('B204', 'CLASSROOM', 'MEDIUM', 1),
    ('B205', 'CLASSROOM', 'LARGE', 1),
    ('BIGLAB', 'LAB', 'LARGE', 1),
    ('LAB3(210)', 'LAB', 'MEDIUM', 1),
    ('LAB4(211)', 'LAB', 'MEDIUM', 1),
    ('LAB5(213)', 'LAB', 'MEDIUM', 1),
    ('C006/A309', 'LAB', 'MEDIUM', 1);

INSERT INTO _group (name, department_id, size, schedule_id) VALUES
    ('COMCEH-24', 1, 'MEDIUM', 1),
    ('COMSE-24', 1, 'MEDIUM', 1),
    ('COMFCI-24', 1, 'MEDIUM', 1),
    ('COMSEP-23', 1, 'MEDIUM', 1),
    ('COMCEH-23', 1, 'MEDIUM', 1),
    ('COMSE-23', 1, 'MEDIUM', 1),
    ('COMFCI-23', 1, 'MEDIUM', 1),
    ('COM-22a', 1, 'MEDIUM', 1),
    ('COM-22b', 1, 'MEDIUM', 1),
    ('COM-21', 1, 'MEDIUM', 1),
    ('MATDAIS-24', 2, 'MEDIUM', 1),
    ('MATMIE-24', 2, 'MEDIUM', 1),
    ('MATDAIS-23', 2, 'MEDIUM', 1),
    ('MATMIE-23', 2, 'MEDIUM', 1),
    ('MATH-22', 2, 'MEDIUM', 1),
    ('MATH-21', 2, 'MEDIUM', 1),
    ('EEAIR-24', 3, 'MEDIUM', 1),
    ('EEAIR-23', 3, 'MEDIUM', 1),
    ('IEMIT-24', 4, 'MEDIUM', 1),
    ('IEMIT-23', 4, 'MEDIUM', 1);

INSERT INTO teacher (name, daily_hours, weekly_hours, schedule_id) VALUES
    ('Ms. Iskra', 6, 30, 1),
    ('Ms. Erika', 6, 30, 1),
    ('Dr. Remudin Mekuria', 6, 30, 1),
    ('Mr. Hussein Chebsi', 6, 30, 1),
    ('Mr. Meezan Chand', 6, 30, 1),
    ('Ms. Mekia Gaso', 6, 30, 1),
    ('Mr. Emilbek Joldoshbekov', 6, 30, 1),
    ('Mr. Zhavlon Khamidov', 6, 30, 1),
    ('Mr. Imtiyaz Gulbarga', 6, 30, 1),
    ('Ms. Kamchybekova Z', 6, 30, 1),
    ('Ms. Abdykadyrova N', 6, 30, 1),
    ('Dr. Mariyam Edilova', 6, 30, 1),
    ('Dr. Ruslan Isaev', 6, 30, 1),
    ('Ms. Zhuzupekova Kunduz', 6, 30, 1),
    ('Ms. Aigul Beisheeva', 6, 30, 1),
    ('Dr. Sherali Matanov', 6, 30, 1),
    ('Ms. Zhanara', 6, 30, 1),
    ('Mr. Chynybekov Zamirbek', 6, 30, 1),
    ('Ms. Alymbekova S.M.', 6, 30, 1),
    ('Chokusheva G.V.', 6, 30, 1),
    ('Ms. Gulnaz', 6, 30, 1),
    ('Dr. Arslan Khan', 6, 30, 1),
    ('Dr. Tauheed Khan', 6, 30, 1),
    ('Ms. Kanykei Azhikulova', 6, 30, 1),
    ('Dr. Cholpon Alieva', 6, 30, 1),
    ('Mr. Zhenishbek Orozakhunov', 6, 30, 1),
    ('Mr. Dim Shaiahmetov', 6, 30, 1),
    ('Ms. Erdolatova Nurgul', 6, 30, 1),
    ('Dr. Rustam Umarov', 6, 30, 1),
    ('Orozova D.', 6, 30, 1),
    ('Ms. Orozova Avashkhan', 6, 30, 1),
    ('Ms. Orozalieva D.', 6, 30, 1),
    ('Ms. Samatova G.', 6, 30, 1),
    ('Ms. Duisheeva T.', 6, 30, 1),
    ('Ms. Saidalieva A.', 6, 30, 1),
    ('Ms. Akmatova Roza', 6, 30, 1),
    ('Mr. Murrey Eldred', 6, 30, 1),
    ('Mr. Adilet Abdykerimov', 6, 30, 1),
    ('Ms. Jamby Djusubalieva', 6, 30, 1),
    ('Ms. Burul Shambetova', 6, 30, 1),
    ('Ms. Zhanara Kushueva', 6, 30, 1),
    ('Ms. Gulnaz Gimaletdinova', 6, 30, 1),
    ('Mr. Radmir Gumerov', 6, 30, 1),
    ('Dr. Arskan Khan', 6, 30, 1),
    ('Ms. Iskra Abdimetalieva', 6, 30, 1),
    ('Ms. Roza Akmatova', 6, 30, 1),
    ('Ms. Suurakan Alymbekova', 6, 30, 1),
    ('Ms. Gulina Chokusheva', 6, 30, 1),
    ('Ms. Avashan Orozova', 6, 30, 1),
    ('Dr. Elzarbek Esharov', 6, 30, 1),
    ('Dr. Tursunali Baimuradov', 6, 30, 1),
    ('Dr. Maryam Edilova', 6, 30, 1),
    ('Dr. Sherali Matanov', 6, 30, 1),
    ('Dr. Ruslan Isaev', 6, 30, 1),
    ('Dr. Tauheed Khan', 6, 30, 1),
    ('Dr. Andrei Ermakov', 6, 30, 1),
    ('Ms. Ainuru Zholchieva', 6, 30, 1),
    ('Ms. Cholponai Kukanova', 6, 30, 1),
    ('Ms. Dilyara Orozalieva', 6, 30, 1),
    ('Mr. Zamir Chynybekov', 6, 30, 1),
    ('Ms. Asina Bopusheva', 6, 30, 1),
    ('Mr. Ilnar', 6, 30, 1);

INSERT INTO time_slot (start_time, end_time) VALUES
    ('09:00', '09:55',1),
    ('10:00', '10:40',1),
    ('10:45', '11:25',1),
    ('11:30', '12:10',1),
    ('12:15', '12:55',1),
    ('13:00', '13:40',1),
    ('13:45', '14:25',1),
    ('14:30', '15:10',1),
    ('15:15', '15:55',1),
    ('16:00', '16:40',1),
    ('16:45', '17:25',1),
    ('17:30', '18:10',1);

INSERT INTO setup_shared_set (name, hours_a_week, schedule_version_id) VALUES
    ('Common Electives', 4, 1),
    ('Kyrgyz Languages',6, 1);