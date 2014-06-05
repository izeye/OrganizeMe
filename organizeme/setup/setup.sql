CREATE DATABASE db_organizeme CHARACTER SET utf8;

GRANT ALL PRIVILEGES ON db_organizeme.* TO 'organizeme'@'%' IDENTIFIED BY 'organizeme';

INSERT INTO `tb_user` VALUES (1,'1234',0,'admin'),(2,'1234',1,'izeye'),(3,'1234',1,'always19'),(4,'1234',1,'guest');

INSERT INTO `tb_category` VALUES (1,'Computer',NULL),(2,'Java',1),(3,'English',NULL);
