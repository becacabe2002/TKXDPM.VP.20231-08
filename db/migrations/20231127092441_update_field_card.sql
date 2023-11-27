-- migrate:up
alter table `card`
    change `dateExpired` `dateReleased` varchar(4) not null;

-- migrate:down

