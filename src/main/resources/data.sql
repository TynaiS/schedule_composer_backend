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



INSERT INTO courses (name, credits) VALUES
    ('English', 3), ('Japan', 3), ('French', 3), ('German', 3),
    ('Discrete Math', 4), ('Calculus 2', 4), ('Design and Analysis of Algorithms', 4),
    ('Geography', 2), ('Backend', 4), ('Ethnic Hacking and Penetration Testing', 4),
    ('Physical Education', 2), ('Design Thinking for Product Solutions', 3),
    ('Philosophy', 3), ('Basics of Science Research', 3), ('Manas Tanuu', 2),
    ('Turkish Language II', 3), ('Data Analysis and Visualization', 4),
    ('The Art of Teaching and Methods of Teaching Computer Science', 4),
    ('Industrial Internship', 6), ('Pre-qualificational Internship', 6),
    ('Russian Language', 3), ('Computer Architecture and Operating Systems', 4),
    ('Digital Electronics', 4), ('Business Fundamentals and Process Management', 4),
    ('Programming Language 2', 4), ('History', 3), ('Metrology, Standardization and Certification', 4),
    ('Life Safety', 3), ('Russian Language for Foreign Students', 3),
    ('OOP', 4), ('Marketing', 3), ('Kyrgyz', 3),
    ('Kyrgyz Language for Foreign Students', 3), ('Kyrgyz Language', 3),
    ('Engineering Computer Graphics', 3), ('Competitive Programming', 4),
    ('Methodology and Practices for Ethical Hacking', 4), ('Interpersonal Communication in IT', 3),
    ('Public Speaking Skills', 3), ('Human-Computer Interaction', 4),
    ('Electives', 3), ('Startup from Start to Launch', 4), ('Computer Literacy', 3),
    ('Philosophy of Technology', 3), ('Fundamentals of Software Development', 4),
    ('Backend Development', 4), ('Computational Mathematics', 4), ('Database', 4),
    ('Introduction to Engineering and CS', 4), ('Algebra and Geometry', 4),
    ('Functional Analysis', 4), ('Calculus I', 4), ('Differential Equations', 4),
    ('Real and Complex Analysis', 4), ('Computer Networks & Telecommunication', 4),
    ('Programming Languages and Methods', 4), ('Theory of Numbers', 4),
    ('Methods of Optimization', 4), ('Educational Internship', 6),
    ('Data Science and Specialty Mathematics', 4), ('Digital Design', 4),
    ('Cybersecurity Foundation', 4), ('Frontend', 4), ('Graphic Design', 4),
    ('Public Speaking', 3), ('VR Design', 4), ('Critical Thinking', 3), ('Management', 3);


INSERT INTO departments(name) VALUES
    ('Computer Science'),
    ('Applied Mathematics and Informatics'),
    ('Artificial Intelligence and Robotics'),
    ('Management in Information Technology');


INSERT INTO rooms (room_num, type, size) VALUES
     ('B101', 'CLASSROOM', 'MEDIUM'),
     ('B102', 'CLASSROOM', 'MEDIUM'),
     ('B103', 'CLASSROOM', 'MEDIUM'),
     ('B104', 'CLASSROOM', 'MEDIUM'),
     ('B109 (APPLE LAB)', 'LAB', 'MEDIUM'),
     ('B201', 'CLASSROOM', 'MEDIUM'),
     ('B202', 'CLASSROOM', 'MEDIUM'),
     ('B203', 'CLASSROOM', 'MEDIUM'),
     ('B204', 'CLASSROOM', 'MEDIUM'),
     ('B205', 'CLASSROOM', 'LARGE'),
     ('BIGLAB', 'LAB', 'LARGE'),
     ('LAB3(210)', 'LAB', 'MEDIUM'),
     ('LAB4(211)', 'LAB', 'MEDIUM'),
     ('LAB5(213)', 'LAB', 'MEDIUM'),
     ('C006/A309', 'LAB', 'MEDIUM');

INSERT INTO groups (name, department_id, size) VALUES
      ('COMCEH-24', 1, 'MEDIUM'),
      ('COMSE-24', 1, 'MEDIUM'),
      ('COMFCI-24', 1, 'MEDIUM'),
      ('COMSEP-23', 1, 'MEDIUM'),
      ('COMCEH-23', 1, 'MEDIUM'),
      ('COMSE-23', 1, 'MEDIUM'),
      ('COMFCI-23', 1, 'MEDIUM'),
      ('COM-22a', 1, 'MEDIUM'),
      ('COM-22b', 1, 'MEDIUM'),
      ('COM-21', 1, 'MEDIUM'),
      ('MATDAIS-24', 2, 'MEDIUM'),
      ('MATMIE-24', 2, 'MEDIUM'),
      ('MATDAIS-23', 2, 'MEDIUM'),
      ('MATMIE-23', 2, 'MEDIUM'),
      ('MATH-22', 2, 'MEDIUM'),
      ('MATH-21', 2, 'MEDIUM'),
      ('EEAIR-24', 3, 'MEDIUM'),
      ('EEAIR-23', 3, 'MEDIUM'),
      ('IEMIT-24', 4, 'MEDIUM'),
      ('IEMIT-23', 4, 'MEDIUM');

INSERT INTO teachers (name, daily_hours, weekly_hours) VALUES
    ('Ms. Iskra', 6, 30),
    ('Ms. Erika', 6, 30),
    ('Dr. Remudin Mekuria', 6, 30),
    ('Mr. Hussein Chebsi', 6, 30),
    ('Mr. Meezan Chand', 6, 30),
    ('Ms. Mekia Gaso', 6, 30),
    ('Mr. Emilbek Joldoshbekov', 6, 30),
    ('Mr. Zhavlon Khamidov', 6, 30),
    ('Mr. Imtiyaz Gulbarga', 6, 30),
    ('Ms. Kamchybekova Z', 6, 30),
    ('Ms. Abdykadyrova N', 6, 30),
    ('Dr. Mariyam Edilova', 6, 30),
    ('Dr. Ruslan Isaev', 6, 30),
    ('Ms. Zhuzupekova Kunduz', 6, 30),
    ('Ms. Aigul Beisheeva', 6, 30),
    ('Dr. Sherali Matanov', 6, 30),
    ('Ms. Zhanara', 6, 30),
    ('Mr. Chynybekov Zamirbek', 6, 30),
    ('Ms. Alymbekova S.M.', 6, 30),
    ('Chokusheva G.V.', 6, 30),
    ('Ms. Gulnaz', 6, 30),
    ('Dr. Arslan Khan', 6, 30),
    ('Dr. Tauheed Khan', 6, 30),
    ('Ms. Kanykei Azhikulova', 6, 30),
    ('Dr. Cholpon Alieva', 6, 30),
    ('Mr. Zhenishbek Orozakhunov', 6, 30),
    ('Mr. Dim Shaiahmetov', 6, 30),
    ('Ms. Erdolatova Nurgul', 6, 30),
    ('Dr. Rustam Umarov', 6, 30),
    ('Orozova D.', 6, 30),
    ('Ms. Orozova Avashkhan', 6, 30),
    ('Ms. Orozalieva D.', 6, 30),
    ('Ms. Samatova G.', 6, 30),
    ('Ms. Duisheeva T.', 6, 30),
    ('Ms. Saidalieva A.', 6, 30),
    ('Ms. Akmatova Roza', 6, 30),
    ('Mr. Murrey Eldred', 6, 30),
    ('Mr. Adilet Abdykerimov', 6, 30),
    ('Ms. Jamby Djusubalieva', 6, 30),
    ('Ms. Burul Shambetova', 6, 30),
    ('Ms. Zhanara Kushueva', 6, 30),
    ('Ms. Gulnaz Gimaletdinova', 6, 30),
    ('Mr. Radmir Gumerov', 6, 30),
    ('Dr. Arskan Khan', 6, 30),
    ('Ms. Iskra Abdimetalieva', 6, 30),
    ('Ms. Roza Akmatova', 6, 30),
    ('Ms. Suurakan Alymbekova', 6, 30),
    ('Ms. Gulina Chokusheva', 6, 30),
    ('Ms. Avashan Orozova', 6, 30),
    ('Dr. Elzarbek Esharov', 6, 30),
    ('Dr. Tursunali Baimuradov', 6, 30),
    ('Dr. Maryam Edilova', 6, 30),
    ('Dr. Sherali Matanov', 6, 30),
    ('Dr. Ruslan Isaev', 6, 30),
    ('Dr. Tauheed Khan', 6, 30),
    ('Dr. Andrei Ermakov', 6, 30),
    ('Ms. Ainuru Zholchieva', 6, 30),
    ('Ms. Cholponai Kukanova', 6, 30),
    ('Ms. Dilyara Orozalieva', 6, 30),
    ('Mr. Zamir Chynybekov', 6, 30),
    ('Ms. Asina Bopusheva', 6, 30),
    ('Mr. Ilnar', 6, 30);

INSERT INTO time_slots (start_time, end_time) VALUES
    ('09:00', '09:55'),
    ('10:00', '10:40'),
    ('10:45', '11:25'),
    ('11:30', '12:10'),
    ('12:15', '12:55'),
    ('13:00', '13:40'),
    ('13:45', '14:25'),
    ('14:30', '15:10'),
    ('15:15', '15:55'),
    ('16:00', '16:40'),
    ('16:45', '17:25'),
    ('17:30', '18:10');

INSERT INTO setup_shared_names (name) VALUES
    ('Common Electives'),
    ('Kyrgyz Languages');
