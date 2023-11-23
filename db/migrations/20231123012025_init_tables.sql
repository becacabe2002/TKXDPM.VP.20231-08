-- migrate:up
CREATE DATABASE IF NOT EXISTS aims;
USE aims;

CREATE TABLE Media(
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      category VARCHAR(45) NOT NULL,
                      price INT NOT NULL,
                      quantity INT NOT NULL,
                      title VARCHAR(45) NOT NULL,
                      value INT NOT NULL,
                      imageUrl VARCHAR(45) NOT NULL,
                      fastShipping BOOLEAN NOT NULL
);

CREATE TABLE CD(
                   id INT PRIMARY KEY,
                   artist VARCHAR(45) NOT NULL,
                   recordLabel VARCHAR(45) NOT NULL,
                   musicType VARCHAR(45) NOT NULL,
                   releasedDate DATE,
                   FOREIGN KEY(id) REFERENCES Media(id) on delete cascade
);

CREATE TABLE DVD(
                    id INT PRIMARY KEY,
                    discType VARCHAR(45) NOT NULL,
                    director VARCHAR(45) NOT NULL,
                    runtime INT NOT NULL,
                    studio VARCHAR(45) NOT NULL,
                    subtitle VARCHAR(45) NOT NULL,
                    releasedDate DATE,
                    FOREIGN KEY(id) REFERENCES Media(id) on delete cascade
);

CREATE TABLE Book(
                     id INT PRIMARY KEY,
                     author VARCHAR(45) NOT NULL,
                     coverType VARCHAR(45) NOT NULL,
                     publisher VARCHAR(45) NOT NULL,
                     publishDate date NOT NULL,
                     numOfPages INT NOT NULL,
                     language VARCHAR(45) NOT NULL,
                     bookCategory VARCHAR(45) NOT NULL,
                     FOREIGN KEY(id) REFERENCES Media(id) on delete cascade
);

CREATE TABLE DeliveryInfo(
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             name VARCHAR(45),
                             province VARCHAR(45),
                             instructions VARCHAR(200),
                             address VARCHAR(100)
);

CREATE TABLE RushDeliveryInfo(
                                 id INT PRIMARY KEY,
                                 shippingTime DATETIME NOT NULL,
                                 rushDeliveryInstructions VARCHAR(200),
                                 FOREIGN KEY(id) REFERENCES DeliveryInfo(id)
);


CREATE TABLE OrderInfo(
                          id INT AUTO_INCREMENT NOT NULL,
                          shippingFees int,
                          DeliveryInfoId INT NOT NULL,
                          PRIMARY KEY(id, DeliveryInfoId),
                          FOREIGN KEY(DeliveryInfoId) REFERENCES DeliveryInfo(id)
);

CREATE TABLE OrderMedia(
                           orderID INT NOT NULL,
                           price INT NOT NULL,
                           quantity INT NOT NULL,
                           mediaId INT NOT NULL,
                           PRIMARY KEY(orderID, mediaId),
                           FOREIGN KEY(orderID) REFERENCES OrderInfo(id),
                           FOREIGN KEY(mediaId) REFERENCES Media(id)
);

CREATE TABLE Invoice(
                        id INT PRIMARY KEY,
                        totalAmount INT NOT NULL,
                        orderId INT NOT NULL,
                        FOREIGN KEY(orderId) REFERENCES OrderInfo(id)
);

CREATE TABLE Card(
                     id INT AUTO_INCREMENT PRIMARY KEY,
                     cardCode VARCHAR(15) NOT NULL,
                     owner VARCHAR(45) NOT NULL,
                     cvvCode VARCHAR(3) NOT NULL,
                     dateExpired VARCHAR(4) NOT NULL
);

CREATE TABLE PaymentTransaction(
                                   id INT auto_increment,
                                   createAt DATETIME NOT NULL default CURRENT_TIMESTAMP,
                                   content VARCHAR(45) NOT NULL,
                                   method VARCHAR(45),
                                   cardId INT NOT NULL,
                                   invoiceId INT NOT NULL,
                                   PRIMARY KEY(id, cardId, invoiceId),
                                   FOREIGN KEY(cardId) REFERENCES Card(id),
                                   FOREIGN KEY(invoiceId) REFERENCES Invoice(id)
);

create table Users(
                      id INT primary key auto_increment,
                      uname varchar(45) not null,
                      createdAt datetime,
                      role ENUM('admin', 'user') not null,
                      externalUID char(36) unique not null
);

create table orderHistory(
                             id INT auto_increment,
                             uid char(36) not null,
                             orderId int not null,
                             paid tinyint(1) not null default false,
                             primary key(id, uid, orderId),
                             foreign key(uid) references Users(externalUID),
                             foreign key(orderId) references OrderInfo(id)
);

-- migrate:down

DROP DATABASE IF EXISTS aims;