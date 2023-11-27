-- migrate:up
# drop table card
alter table `paymenttransaction` drop foreign key `paymenttransaction_ibfk_1`;
drop table if exists `card`;

# drop table paymentTransaction, now transaction will be saved on Mongodb for general structure
drop table if exists `paymentTransaction`;

-- migrate:down

