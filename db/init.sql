CREATE DATABASE IF NOT EXISTS `wordoccurrences`;
USE `wordoccurrences`;
CREATE TABLE IF NOT EXISTS word (
    ID int NOT NULL AUTO_INCREMENT,
    Word varchar(255) NOT NULL,
    Count int NOT NULL,
    PRIMARY KEY (ID)
);