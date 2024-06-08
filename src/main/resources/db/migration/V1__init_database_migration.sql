DROP TABLE IF EXISTS book;
DROP SEQUENCE IF EXISTS book_seq;


CREATE TABLE book (
    id bigint PRIMARY KEY,
    isbn VARCHAR(255),
    publisher VARCHAR(255),
    title VARCHAR(255)
);

CREATE SEQUENCE book_seq
    START 1;

SELECT setval('book_seq', 1, false);