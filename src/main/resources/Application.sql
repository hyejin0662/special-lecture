
DROP TABLE IF EXISTS lecture;
DROP TABLE IF EXISTS lecture_application;


CREATE TABLE lecture (
                         lecture_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         topic VARCHAR(255) NOT NULL,
                         description TEXT NOT NULL,
                         instructor VARCHAR(255) NOT NULL,
                         lecture_status ENUM('COMPLETED', 'PENDING', 'CANCELLED') NOT NULL,
                         create_at DATETIME NOT NULL,
                         update_at DATETIME NOT NULL,
                         capacity INT NOT NULL DEFAULT 30
);


CREATE TABLE lecture_application (
                                     lecture_application_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     user_id BIGINT NOT NULL,
                                     lecture_id BIGINT NOT NULL,
                                     FOREIGN KEY (lecture_id) REFERENCES lecture(lecture_id)
);


INSERT INTO lecture (topic, description, instructor, lecture_status, create_at, update_at, capacity) VALUES
                                                                                                         ('Spring Boot Introduction', 'Introduction to Spring Boot framework', 'John Doe', 'PENDING', '2024-06-01 10:00:00', '2024-06-01 10:00:00', 30),
                                                                                                         ('Advanced Java', 'Deep dive into Java advanced topics', 'Jane Smith', 'PENDING', '2024-06-08 14:00:00', '2024-06-08 14:00:00', 30),
                                                                                                         ('Kotlin for Java Developers', 'Learn Kotlin for Java developers', 'Alice Johnson', 'PENDING', '2024-06-15 16:00:00', '2024-06-15 16:00:00', 30);


INSERT INTO lecture_application (user_id, lecture_id) VALUES
                                                          (1, 1),
                                                          (2, 1),
                                                          (3, 2),
                                                          (1, 2),
                                                          (4, 3),
                                                          (5, 3);
