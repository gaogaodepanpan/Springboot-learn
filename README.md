# Springboot-learn
## Spring Data JPA

day1:orm-hibernate

mysql 5.X:

```mysql
CREATE DATABASE IF NOT EXISTS jpa DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
CREATE TABLE `cst_customer`  (
  `cust_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cust_address` varchar(255) DEFAULT NULL,
  `cust_industry` varchar(255) DEFAULT NULL,
  `cust_level` varchar(255) DEFAULT NULL,
  `cust_name` varchar(255) DEFAULT NULL,
  `cust _phone` varchar(255) DEFAULT  NULL,
  `cust_source` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cust_id`)
) ENGINE = InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET = utf8;
```
