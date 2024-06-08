INSERT INTO author (first_name, last_name) VALUES ('Craig', 'Walls');

INSERT INTO book (isbn, publisher, title, author_id) VALUES (
    '978-1617294945',
    'Simon & Schuster',
    'Spring in Action, 5th Edition',
    (SELECT id FROM author WHERE first_name = 'Craig' AND last_name = 'Walls')
);

INSERT INTO book (isbn, publisher, title, author_id) VALUES (
    '978-1617292545',
    'Simon & Schuster',
    'Spring Boot in Action, 1st Edition',
    (SELECT id FROM author WHERE first_name = 'Craig' AND last_name = 'Walls')
);

INSERT INTO book (isbn, publisher, title, author_id) VALUES (
    '978-1617297571',
    'Simon & Schuster',
    'Spring in Action, 6th Edition',
    (SELECT id FROM author WHERE first_name = 'Craig' AND last_name = 'Walls')
);

-- Author: Eric Evans
INSERT INTO author (first_name, last_name) VALUES ('Eric', 'Evans');

INSERT INTO book (isbn, publisher, title, author_id) VALUES (
    '978-0321125217',
    'Addison Wesley',
    'Domain-Driven Design',
    (SELECT id FROM author WHERE first_name = 'Eric' AND last_name = 'Evans')
);

-- Author: Robert Martin
INSERT INTO author (first_name, last_name) VALUES ('Robert', 'Martin');

INSERT INTO book (isbn, publisher, title, author_id) VALUES (
    '978-0134494166',
    'Addison Wesley',
    'Clean Code',
    (SELECT id FROM author WHERE first_name = 'Robert' AND last_name = 'Martin')
);
