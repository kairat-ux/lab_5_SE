INSERT INTO courses (name, description, duration_months, price) VALUES
                                                                    ('Front-end Development', 'Learn HTML, CSS, JavaScript, React', 6, 150000.0),
                                                                    ('Back-end Development', 'Learn Java, Spring Boot, PostgreSQL', 8, 180000.0),
                                                                    ('Full-stack Development', 'Complete web development course', 12, 250000.0),
                                                                    ('Mobile Development', 'Learn React Native and Flutter', 6, 160000.0),
                                                                    ('Data Science', 'Python, Machine Learning, AI', 10, 200000.0);

INSERT INTO operators (name, email, phone, active) VALUES
                                                       ('Aisha Bekenova', 'aisha@crm.kz', '+77011234567', true),
                                                       ('Nurzhan Aitbayev', 'nurzhan@crm.kz', '+77012345678', true),
                                                       ('Aliya Mukhanova', 'aliya@crm.kz', '+77013456789', true),
                                                       ('Timur Serikbayev', 'timur@crm.kz', '+77014567890', true),
                                                       ('Dana Zhaksylykova', 'dana@crm.kz', '+77015678901', false);

INSERT INTO application_requests (user_name, phone, commentary, course_id, operator_id, handled, created_at) VALUES
                                                                                                                 ('Bakhytzhan Tulegenov', '+77070000001', 'Interested in front-end', 1, 1, false, CURRENT_TIMESTAMP),
                                                                                                                 ('Zarina Kassymova', '+77070000002', 'Want to learn React', 1, 2, true, CURRENT_TIMESTAMP),
                                                                                                                 ('Arman Sydykov', '+77070000003', 'Need back-end skills', 2, 1, false, CURRENT_TIMESTAMP),
                                                                                                                 ('Madina Omarova', '+77070000004', 'Full-stack career change', 3, 3, true, CURRENT_TIMESTAMP),
                                                                                                                 ('Dias Bekmukhambetov', '+77070000005', 'Mobile app development', 4, 2, false, CURRENT_TIMESTAMP);