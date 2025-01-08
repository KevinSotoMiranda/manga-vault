CREATE SEQUENCE IF NOT EXISTS user_sequence START WITH 1 INCREMENT BY 1;

ALTER TABLE users ALTER COLUMN user_id SET DEFAULT nextval('user_sequence');

CREATE SEQUENCE IF NOT EXISTS book_sequence START WITH 1 INCREMENT BY 1;

ALTER TABLE books ALTER COLUMN book_id SET DEFAULT nextval('book_sequence');

CREATE SEQUENCE IF NOT EXISTS collection_sequence START WITH 1 INCREMENT BY 1;

ALTER TABLE collections ALTER COLUMN collection_id SET DEFAULT nextval('collection_sequence');

CREATE SEQUENCE IF NOT EXISTS series_sequence START WITH 1 INCREMENT BY 1;

ALTER TABLE series ALTER COLUMN series_id SET DEFAULT nextval('series_sequence');