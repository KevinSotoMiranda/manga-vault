CREATE TABLE users (
    user_id SERIAL NOT NULL,
    username VARCHAR(255) NOT NULL,
    PRIMARY KEY(user_id),
    UNIQUE(username)
);

CREATE TABLE series(
    series_id SERIAL NOT NULL,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    PRIMARY KEY(series_id),
    UNIQUE(title, author)
);

CREATE TABLE books(
    book_id SERIAL NOT NULL,
    title VARCHAR(255) NOT NULL,
    isbn_13 CHAR(13) NOT NULL,
    volume INT NOT NULL,
    series_id INT NOT NULL,
    PRIMARY KEY(book_id),
    UNIQUE(isbn_13),
    FOREIGN KEY(series_id) REFERENCES series(series_id),
);

CREATE TABLE collections(
    collection_id SERIAL NOT NULL,
    user_id INT NOT NULL,
    book_id INT NOT NULL,
    PRIMARY KEY(collection_id),
    UNIQUE(user_id, book_id),
    FOREIGN KEY(user_id) REFERENCES users(user_id),
    FOREIGN KEY(book_id) REFERENCES books(book_id)
);