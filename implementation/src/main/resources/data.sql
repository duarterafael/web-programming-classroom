Delete from `db_banck_qualiti`.`customer`;
Delete from  `db_banck_qualiti`.`account`;

INSERT INTO `db_banck_qualiti`.`customer`
(`cpf`, `name`)
VALUES
("07698187487" , "Rafael Batista Duarte");


INSERT INTO `db_banck_qualiti`.`account`
(`balance`, `number`, `customer_id`)
VALUES
(0,"123", (select id from `db_banck_qualiti`.`customer` where name = "Rafael Batista Duarte"));