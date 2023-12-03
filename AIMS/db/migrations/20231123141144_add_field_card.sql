-- migrate:up
alter table `card`
    add column `UID` char(36) unique not null,
    add constraint foreign key (`UID`) references `users`(`externalUID`) on delete cascade on update cascade;

-- migrate:down

