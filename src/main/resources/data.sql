-- Добавляем курсы
INSERT INTO courses (name, description, price) VALUES
                                                   ('Java Spring Boot', 'Изучение разработки на Spring Boot с нуля до профессионала', 45000),
                                                   ('Python Django', 'Создание веб-приложений на Django Framework', 40000),
                                                   ('React.js Frontend', 'Современная frontend разработка с React и Redux', 35000),
                                                   ('DevOps Engineer', 'Docker, Kubernetes, CI/CD, облачные технологии', 50000),
                                                   ('Data Science', 'Анализ данных, Machine Learning, Python', 55000);

-- Добавляем операторов
INSERT INTO operators (name, surname, department) VALUES
                                                      ('Алексей', 'Иванов', 'IT'),
                                                      ('Мария', 'Петрова', 'IT'),
                                                      ('Дмитрий', 'Сидоров', 'IT'),
                                                      ('Елена', 'Смирнова', 'Praja'),
                                                      ('Ольга', 'Козлова', 'Praja'),
                                                      ('Сергей', 'Морозов', 'Marketing'),
                                                      ('Анна', 'Новикова', 'Marketing'),
                                                      ('Иван', 'Соколов', 'IT');

-- Добавляем несколько тестовых заявок
INSERT INTO application_requests (user_name, commentary, phone, handled, course_id) VALUES
                                                                                        ('Айгуль Нурланова', 'Хочу научиться Java', '+77001234567', false, 1),
                                                                                        ('Ерлан Касымов', 'Интересует Python для анализа данных', '+77012345678', false, 5),
                                                                                        ('Динара Сапарова', 'Нужна консультация по курсу React', '+77023456789', false, 3);