Delete from customer;
Delete from  account;

INSERT INTO customer
(`cpf`, `name`)
VALUES
("07698187417" , "Rafael123 Batista Duarte");


INSERT INTO account
(`balance`, `number`, `customer_id`)
VALUES
(0,"123", (select id from `db_banck_qualiti`.`customer` where name = "Rafael123 Batista Duarte"));