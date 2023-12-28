-- migrate:up
start transaction;
INSERT INTO Media (category, price, quantity, title, value, imageUrl, fastShipping)
VALUES
    ('CD', 20, 50, '1989 (Taylor’s Version)', 15, 'https://i.imgur.com/7NDh3Ll.jpg', true),
    ('Book', 22, 45, '1984', 27, 'https://i.imgur.com/rMTcmd0.jpg', true),
    ('DVD', 25, 60, 'The Shawshank Redemption', 20, 'https://i.imgur.com/ejlisxI.jpg', true),
    ('CD', 15, 30, '1967–1970', 25, 'https://i.imgur.com/XTo3fQ2.jpg', false),
    ('Book', 10, 20, 'The Lord of the Rings', 20, 'https://i.imgur.com/NY6uuua.jpg', false),
    ('DVD', 18, 45, 'The Godfather', 30, 'https://i.imgur.com/lcsHOC2.jpg', false),
    ('CD', 10, 23, '1962–1966', 31, 'https://i.imgur.com/9x4jHfr.jpg', true),
    ('Book', 10, 14, 'The Kite Runner', 30, 'https://i.imgur.com/05yFvzA.jpg', true),
    ('DVD', 15, 25, 'The Dark Knight', 35, 'https://i.imgur.com/kibzJWz.jpg', true),
    ('CD', 25, 40, '2 RUFF, Vol. 1', 18, 'https://i.imgur.com/1oSev7c.jpg', true),
    ('Book', 35, 40, 'Harry Potter and the Philosopher’s Stone', 19, 'https://i.imgur.com/6SZbTbn.jpg', true),
    ('DVD', 30, 55, 'Pulp Fiction', 25, 'https://i.imgur.com/6FLbwzm.jpg', true),
    ('CD', 12, 25, 'Stick Season', 22, 'https://i.imgur.com/dr5MWzY.jpg', false),
    ('Book', 12, 20, 'Slaughterhouse-Five', 30, 'https://i.imgur.com/uU38VOI.jpg', false),
    ('DVD', 20, 30, 'Fight Club', 28, 'https://i.imgur.com/LDNJFGB.jpg', false),
    ('CD', 9, 10, 'GUTS', 30, 'https://i.imgur.com/11SFEk7.jpg', true),
    ('Book', 8, 15, 'The Lion, the Witch, and the Wardrobe', 30, 'https://i.imgur.com/FJpQxHx.jpg', true),
    ('DVD', 12, 20, 'Forrest Gump', 40, 'https://i.imgur.com/HyVOFC2.jpg', true),
    ('CD', 18, 60, 'Hackney Diamonds', 20, 'https://i.imgur.com/ikKdK6e.png', false),
    ('Book', 15, 40, 'To Kill a Mockingbird', 30, 'https://i.imgur.com/mdGHUpQ.jpg', false),
    ('DVD', 22, 70, 'Inception', 25, 'https://i.imgur.com/Nq9yx1y.jpg', false),
    ('CD', 20, 35, 'All The Little Lights ', 28, 'https://i.imgur.com/ogAggOG.png', true),
    ('Book', 25, 35, 'The Book Thief', 18, 'https://i.imgur.com/GO4NLFR.jpg', true),
    ('DVD', 28, 40, 'The Matrix', 32, 'https://i.imgur.com/4yohX5k.jpg', true),
    ('CD', 12, 18, 'Substance', 25, 'https://i.imgur.com/mLPt3rJ.jpg', true),
    ('Book', 24, 15, 'The Chosen and the Beautiful', 20, 'https://i.imgur.com/EtwAAHk.jpg', true),
    ('DVD', 14, 30, 'Goodfellas', 30, 'https://i.imgur.com/ZdEwcGc.jpg', true),
    ('CD', 20, 55, 'Quarter Life Crisis', 14, 'https://i.imgur.com/sPyOBcV.jpg', true),
    ('Book', 34, 55, 'The Great Gatsby', 13, 'https://i.imgur.com/3ouAHAd.jpg', true),
    ('DVD', 30, 65, 'Interstellar', 18, 'https://i.imgur.com/TWjUxLP.jpg', true);


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
    (3, 'Blu_ray', 'Frank Darabont', 142, 'Castle Rock Entertainment', 'English, French', '1994-01-01'),
    (6, 'Blu_ray', 'Francis Ford Coppola', 540, 'Paramount Pictures', 'English', '1972-01-01'),
    (9, 'HD_DVD', 'Christopher Nolan', 152, 'Warner Bros. Pictures', 'English', '2008-07-18'),
    (12, 'Blu_ray', 'Quentin Tarantino', 154, 'Miramax Films', 'English', '1994-01-01'),
    (15, 'HD_DVD', 'David Fincher', 139, '20th Century Fox', 'English', '1999-01-01'),
    (18, 'Blu_ray', 'Robert Zemeckis', 142, 'Paramount Pictures', 'English, Danish, Swedish, Dutch', '1994-07-06'),
    (21, 'HD_DVD', 'Christopher Nolan', 148, 'Warner Bros. Pictures', 'English', '2010-07-16'),
    (24, 'Blu_ray', 'The Wachowskis', 136, 'Warner Bros. Pictures', 'English', '1999-03-31'),
    (27, 'HD_DVD', 'Martin Scorsese', 146, 'Warner Bros. Pictures', 'English', '1990-09-19'),
    (30, 'Blu_ray', 'Christopher Nolan', 169, 'Paramount Pictures, Warner Bros. Pictures', 'English', '2014-11-05');

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
