CREATE TABLE `order_service`.`t_orders`
(
     `id`  bigint(20) NOT NULL AUTO_INCREMENT,
      `order_number`  VARCHAR(255) DEFAULT NULL,
      `sku_code` VARCHAR(255),
      `price`  DECIMAL(19,2),
      `quantity` int(11),
      PRIMARY KEY(`id`)
);
