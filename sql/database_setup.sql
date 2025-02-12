CREATE TABLE "groups" (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL
);

CREATE TABLE teacher (
                         id BIGSERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         daily_hours INT NOT NULL,
                         weekly_hours INT NOT NULL
);

CREATE TABLE course (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        credits INT NOT NULL
);

CREATE TABLE room (
                      id BIGSERIAL PRIMARY KEY,
                      room_num VARCHAR(255) NOT NULL,
                      type TEXT CHECK (type IN ('LAB', 'CLASSROOM')) NOT NULL
);

CREATE TABLE course_group_teacher (
                                      id BIGSERIAL PRIMARY KEY,
                                      group_id BIGINT NOT NULL REFERENCES "groups"(id) ON DELETE CASCADE,
                                      course_id BIGINT NOT NULL REFERENCES course(id) ON DELETE CASCADE,
                                      hours_a_week INT NOT NULL,
                                      hours_total INT NOT NULL,
                                      teacher_id BIGINT NOT NULL REFERENCES teacher(id) ON DELETE CASCADE,
                                      type TEXT CHECK (type IN ('OBLIGATORY', 'ELECTIVE')) NOT NULL,
                                      required_room_type TEXT CHECK (required_room_type IN ('LAB', 'CLASSROOM')) NOT NULL
);


CREATE TABLE schedule (
                          id BIGSERIAL PRIMARY KEY,
                          course_group_teacher_id BIGINT NOT NULL REFERENCES course_group_teacher(id) ON DELETE CASCADE,
                          day TEXT CHECK (day IN ('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY')) NOT NULL,
                          start_time TIME NOT NULL,
                          end_time TIME NOT NULL,
                          room_id BIGINT NOT NULL REFERENCES room(id) ON DELETE CASCADE,
                          teaching_mode TEXT CHECK (teaching_mode IN ('ONLINE', 'CLASSROOM')) NOT NULL
);

CREATE TABLE schedule_lunch (
                                id BIGSERIAL PRIMARY KEY,
                                group_id BIGINT NOT NULL REFERENCES "groups"(id),
                                day VARCHAR(10) NOT NULL CHECK (day IN ('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY')),
                                start_time TIME NOT NULL,
                                end_time TIME NOT NULL
);

INSERT INTO course (name, credits) VALUES
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

INSERT INTO room (room_num, type) VALUES
                                      ('B101', 'CLASSROOM'), ('B102', 'CLASSROOM'), ('B103', 'CLASSROOM'),
                                      ('B104', 'CLASSROOM'), ('B109 (APPLE LAB)', 'LAB'), ('B201', 'CLASSROOM'),
                                      ('B202', 'CLASSROOM'), ('B203', 'CLASSROOM'), ('B204', 'CLASSROOM'),
                                      ('B205', 'CLASSROOM'), ('BIGLAB', 'LAB'), ('LAB3(210)', 'LAB'),
                                      ('LAB4(211)', 'LAB'), ('LAB5(213)', 'LAB'), ('C006/A309', 'LAB');


INSERT INTO "groups" (name) VALUES
                                ('COMCEH-24'), ('COMSE-24'), ('COMFCI-24'), ('COMSEP-23'), ('COMCEH-23'),
                                ('COMSE-23'), ('COMFCI-23'), ('COM-22a'), ('COM-22b'), ('COM-21'),
                                ('MATDAIS-24'), ('MATMIE-24'), ('MATDAIS-23'), ('MATMIE-23'), ('MATH-22'),
                                ('MATH-21'), ('EEAIR-24'), ('EEAIR-23'), ('IEMIT-24'), ('IEMIT-23');

INSERT INTO teacher (name, daily_hours, weekly_hours) VALUES
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
