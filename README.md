# Springboot-learn
## Spring Data JPA

day1:orm-hibernate

mysql 5.X:

```mysql
CREATE DATABASE IF NOT EXISTS jpa DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
CREATE TABLE cst_customer (
  `cust_id` bigint(32) NOT NULL AUTO_INCREMENT,
  `cust_name` varchar(32)  NOT NULL,
  `cust_source` varchar(32) DEFAULT NULL,
  `cust_industry` varchar(32) DEFAULT NULL,
  `cust_level` varchar(32) DEFAULT NULL,
  `cust_address` varchar(128) DEFAULT NULL,
  `cust _phone` varchar(64) DEFAULT  NULL,
  PRIMARY KEY (`cust_id`)
) ENGINE = InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET = utf8;

#######################################################
CREATE TABLE `cst_linkman`  (
  `lkm_id` bigint(32) NOT NULL AUTO_INCREMENT,
   `lkm_name` varchar(16) DEFAULT NULL,
  `lkm_gender` char(1) DEFAULT NULL,
  `lkm _phone` varchar(16) DEFAULT  NULL,
  `lkm_mobile` varchar(16) DEFAULT NULL,
  `lkm_email` varchar(64) DEFAULT NULL,
  `lkm_position` varchar(16) DEFAULT NULL,
  `lkm_memo` varchar(512) DEFAULT NULL,
  `lkm_cust_id`  bigint(32)  NOT NULL,
  PRIMARY KEY (`lkm_id`),
  FOREIGN KEY (`lkm_cust_id`) REFERENCES `cst_customer` (`cust_id`) 
) ENGINE = InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET = utf8;



```
