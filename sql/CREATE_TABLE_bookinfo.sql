CREATE TABLE bookinfo
(
	bookInfoId INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	createTime DATETIME NOT NULL DEFAULT NOW(),
	PRIMARY KEY(bookInfoId)
);