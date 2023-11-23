-- migrate:up
start transaction;
INSERT INTO Media (category, price, quantity, title, value, imageUrl, fastShipping)
VALUES
    ('CD', 20, 50, '1989 (Taylor’s Version)', 15, NULL, true),
    ('Book', 22, 45, '1984', 27, NULL, true),
    ('DVD', 25, 60, 'The Shawshank Redemption', 20, NULL, true),
    ('CD', 15, 30, '1967–1970', 25, NULL, false),
    ('Book', 10, 20, 'The Lord of the Rings', 20, NULL, false),
    ('DVD', 18, 45, 'The Godfather', 30, NULL, false),
    ('CD', 10, 23, '1962–1966', 31, NULL, true),
    ('Book', 10, 14, 'The Kite Runner', 30, NULL, true),
    ('DVD', 15, 25, 'The Dark Knight', 35, NULL, true),
    ('CD', 25, 40, '2 RUFF, Vol. 1', 18, NULL, true),
    ('Book', 35, 40, 'Harry Potter and the Philosopher’s Stone', 19, NULL, true),
    ('DVD', 30, 55, 'Pulp Fiction', 25, NULL, true),
    ('CD', 12, 25, 'Stick Season', 22, NULL, false),
    ('Book', 12, 20, 'Slaughterhouse-Five', 30, NULL, false),
    ('DVD', 20, 30, 'Fight Club', 28, NULL, false),
    ('CD', 9, 10, 'GUTS', 30, NULL, true),
    ('Book', 8, 15, 'The Lion, the Witch, and the Wardrobe', 30, NULL, true),
    ('DVD', 12, 20, 'Forrest Gump', 40, NULL, true),
    ('CD', 18, 60, 'Hackney Diamonds', 20, NULL, false),
    ('Book', 15, 40, 'To Kill a Mockingbird', 30, NULL, false),
    ('DVD', 22, 70, 'Inception', 25, NULL, false),
    ('CD', 20, 35, 'All The Little Lights ', 28, NULL, true),
    ('Book', 25, 35, 'The Book Thief', 18, NULL, true),
    ('DVD', 28, 40, 'The Matrix', 32, NULL, true),
    ('CD', 12, 18, 'Substance', 25, NULL, true),
    ('Book', 24, 15, 'The Chosen and the Beautiful', 20, NULL, true),
    ('DVD', 14, 30, 'Goodfellas', 30, NULL, true),
    ('CD', 20, 55, 'Quarter Life Crisis', 14, NULL, true),
    ('Book', 34, 55, 'The Great Gatsby', 13, NULL, true),
    ('DVD', 30, 65, 'Interstellar', 18, NULL, true);


INSERT INTO Book (id, author, coverType, publisher, publishDate, numOfPages, language, bookCategory)
VALUES
    (2, 'George Orwell', 'hardcover', 'Secker & Warburg', '1949-06-08', 328, 'English', 'Dystopian, Political Fiction'),
    (5, 'J.R.R. Tolkien', 'paperback', 'George Allen & Unwin', '1954-07-29', 1178, 'English', 'Fantasy'),
    (8, 'Khaled Hosseini', 'hardcover', 'Riverhead Books', '2003-05-29', 372, 'English', 'Historical, Drama'),
    (11, 'J.K. Rowling', 'paperback', 'Bloomsbury Publishing (UK), Scholastic (US)', '1997-06-26', 223, 'English', 'Fantasy, Drama'),
    (14, 'Kurt Vonnegut', 'hardcover', 'Delacorte', '1969-03-31', 275, 'English', 'Science Fiction'),
    (17, 'C.S. Lewis', 'paperback', 'Geoffrey Bles', '1950-10-16', 208, 'English', 'Fantasy, Children’s Literature'),
    (20, 'Harper Lee', 'hardcover', 'J. B. Lippincott & Co.', '1960-07-11', 281, 'English', 'Southern Gothic, Bildungsroman'),
    (23, 'Markus Zusak', 'paperback', 'Picador (Australia)', '2005-01-01', 584, 'English', 'Historical Fiction'),
    (26, 'Nghi Vo', 'hardcover', 'Tordotcom', '2021-06-01', 272, 'English', 'Historical Fantasy'),
    (29, 'F. Scott Fitzgerald', 'paperback', 'Charles Scribner’s Sons', '1925-04-10', 180, 'English', 'Tragedy');

INSERT INTO CD (id, artist, recordLabel, musicType, releasedDate)
VALUES
    (1, 'Taylor Swift', 'Republic Records', 'Pop', '2023-09-19'),
    (4, 'The Beatles', 'Apple', 'Rock, psychedelia', '2023-11-10'),
    (7, 'The Beatles', 'Apple', 'Rock, pop', '2023-11-10'),
    (10, 'Chase & Status', 'Nettwerk Music Group Inc.', 'Electronic, Jungle/Drum’n’Bass', '2023-11-10'),
    (13, 'Noah Kahan', 'Mercury Records and Republic Records', 'Folk', '2022-10-14'),
    (16, 'Olivia Rodrigo', 'Geffen Records', 'Pop', '2023-11-10'),
    (19, 'The Rolling Stones', 'Polydor', 'Rock', '2023-10-20'),
    (22, 'Passenger', 'Black Crow Records', 'Folk', '2023-11-10'),
    (25, 'New Order', 'Factory Records', 'Alternative Dance', '1987-08-01'),
    (28, 'Baby Queen', 'Polydor', 'Pop', '2023-11-10');

INSERT INTO DVD (id, discType, director, runtime, studio, subtitle, releasedDate)
VALUES
    (3, 'Blu-ray', 'Frank Darabont', 142, 'Castle Rock Entertainment', 'English, French', '1994-01-01'),
    (6, 'Blu-ray', 'Francis Ford Coppola', 540, 'Paramount Pictures', 'English', '1972-01-01'),
    (9, 'HD-DVD', 'Christopher Nolan', 152, 'Warner Bros. Pictures', 'English', '2008-07-18'),
    (12, 'Blu-ray', 'Quentin Tarantino', 154, 'Miramax Films', 'English', '1994-01-01'),
    (15, 'HD-DVD', 'David Fincher', 139, '20th Century Fox', 'English', '1999-01-01'),
    (18, 'Blu-ray', 'Robert Zemeckis', 142, 'Paramount Pictures', 'English, Danish, Swedish, Dutch', '1994-07-06'),
    (21, 'HD-DVD', 'Christopher Nolan', 148, 'Warner Bros. Pictures', 'English', '2010-07-16'),
    (24, 'Blu-ray', 'The Wachowskis', 136, 'Warner Bros. Pictures', 'English', '1999-03-31'),
    (27, 'HD-DVD', 'Martin Scorsese', 146, 'Warner Bros. Pictures', 'English', '1990-09-19'),
    (30, 'Blu-ray', 'Christopher Nolan', 169, 'Paramount Pictures, Warner Bros. Pictures', 'English', '2014-11-05');

COMMIT;

INSERT INTO Users (uname, createdAt, externalUID)
VALUES
    ('user123', NOW(), '123e4567-e89b-12d3-a456-426614174000');

-- migrate:down
truncate table media;
truncate table book;
truncate table cd;
truncate table dvd;
truncate table users;
