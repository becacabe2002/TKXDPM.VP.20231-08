-- migrate:up
alter table `paymenttransaction`
    modify `createAt` datetime not null default current_timestamp;

alter table `rushdeliveryinfo`
    modify `shippingTime` date null;

alter table `users`
    modify `createdAt` datetime not null default current_timestamp;

-- migrate:down

