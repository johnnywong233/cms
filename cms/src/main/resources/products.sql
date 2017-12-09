/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


CREATE DATABASE IF NOT EXISTS `cms`
  DEFAULT CHARACTER SET utf8mb4;

USE `cms`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id`       INT(11)      NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_name`     VARCHAR(45)  NOT NULL COMMENT '用户名',
  `user_password` VARCHAR(45)  NOT NULL COMMENT '密码',
  PRIMARY KEY (`user_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT = '用户信息';

/*Data for the table `user` */
INSERT INTO user (user_id, user_name, user_password) VALUES (4, 'root', 'root');


/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `cate_id`   INT(11)      NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `cate_name` VARCHAR(45) NOT NULL COMMENT '类别名',
  PRIMARY KEY (`cate_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT = '商品类别';

/*Data for the table `category` */

INSERT INTO category (cate_id, cate_name) VALUES (1, 'fruit');


/*Table structure for table `products` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `pro_id`          INT(11) UNSIGNED     NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `pro_name`        VARCHAR(45)          NOT NULL COMMENT '商品名',
  `pro_no`          VARCHAR(45)          DEFAULT NULL COMMENT '商品编码',
  `pro_state`       CHAR(1)              DEFAULT NULL COMMENT '商品状态', # M:在卖品，F：非卖品
#   `com_create_date` DATETIME    DEFAULT    current_timestamp COMMENT '创建时间',
#   `com_update_date` DATETIME    DEFAULT    current_timestamp COMMENT '更新时间',
  `pro_price`       VARCHAR(45)          DEFAULT NULL COMMENT '价格',
  `pro_number`      VARCHAR(45)          DEFAULT NULL COMMENT '存量',
  `d_id`            INT(11)              DEFAULT NULL COMMENT '',
  PRIMARY KEY (`pro_id`),
  KEY `fk_pro_cate` (`d_id`),
  CONSTRAINT `fk_pro_cate` FOREIGN KEY (`d_id`) REFERENCES `category` (`cate_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT = '商品信息';

/*Data for the table `commodity` */

INSERT INTO product (pro_id, pro_name, pro_no, pro_state, pro_price, pro_number, d_id)
VALUES (2, 'apple', '111221', 'M', '5.8', '565', 1);
INSERT INTO product (pro_id, pro_name, pro_no, pro_state, pro_price, pro_number, d_id)
VALUES (3, 'banana', '13552', 'M', '5.8', '41', 1);
INSERT INTO product (pro_id, pro_name, pro_no, pro_state, pro_price, pro_number, d_id)
VALUES (4, 'pipe', '198752', 'M', '5.8', '23', 1);
INSERT INTO product (pro_id, pro_name, pro_no, pro_state, pro_price, pro_number, d_id)
VALUES (5, 'lemon', '331341', 'F', '7.8', '44', 1);
INSERT INTO product (pro_id, pro_name, pro_no, pro_state, pro_price, pro_number, d_id)
VALUES (6, 'pear', '112341', 'M', '5.8', '565', 1);
INSERT INTO product (pro_id, pro_name, pro_no, pro_state, pro_price, pro_number, d_id)
VALUES (7, 'grape', '111221', 'M', '5.8', '565', 1);
INSERT INTO product (pro_id, pro_name, pro_no, pro_state, pro_price, pro_number, d_id)
VALUES (8, 'orange', '111422', 'M', '5.8', '565', 1);
INSERT INTO product (pro_id, pro_name, pro_no, pro_state, pro_price, pro_number, d_id)
VALUES (9, 'peach', '133242', 'F', '5.8', '565', 1);


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;