Delete from customer;
Delete from  account;

INSERT INTO customer
(`cpf`, `name`)
VALUES
("07698187417" , "Rafael Batista Duarte"),
("12345678910" , "Ricardo de Melo"),
("10987654321" , "Jonas da Baleia"),
("11122233344" , "Leandro Leonardo");


INSERT INTO account
(`balance`, `number`, `customer_id`)
VALUES
(131234560,"123", (select id from `db_banck_qualiti`.`customer` where name = "Rafael Batista Duarte")),
(1230,"321", (select id from `db_banck_qualiti`.`customer` where name = "Ricardo de Melo")),
(5201,"132", (select id from `db_banck_qualiti`.`customer` where name = "Jonas da Baleia")),
(10,"456", (select id from `db_banck_qualiti`.`customer` where name = "Leandro Leonardo"));