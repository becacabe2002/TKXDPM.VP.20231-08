-- migrate:up
alter table `media`
     modify `category` ENUM('Book','CD','DVD') NOT NULL DEFAULT 'Book',
     modify `imageUrl` VARCHAR(45) null;

alter table `dvd`
    modify `discType` ENUM('HD_DVD','Blu_ray') NOT NULL DEFAULT 'HD_DVD';

alter table `book`
    modify `coverType` ENUM('hardcover', 'paperback') NOT NULL default 'hardcover';

alter table `users`
    modify `role` ENUM('admin', 'user') NOT NULL DEFAULT 'user';

-- migrate:down

alter table `media`
    modify `category` VARCHAR(45) NOT NULL,
    modify `imageUrl` VARCHAR(45) not null;

alter table `dvd`
    modify `discType` VARCHAR(45) NOT NULL;

alter table `book`
    modify `coverType` VARCHAR(45) NOT NULL;

