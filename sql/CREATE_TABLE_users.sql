CREATE TABLE users
(
	userId INT NOT NULL AUTO_INCREMENT,
	userName VARCHAR(16) NOT NULL UNIQUE,
	userEmail VARCHAR(128) UNIQUE,
	userPasswordMd5 CHAR(32) NOT NULL,
	userNickname VARCHAR(64) NOT NULL DEFAULT 'default',
	following INT NOT NULL DEFAULT 0,
	followers INT NOT NULL DEFAULT 0,
	createTime DATETIME NOT NULL DEFAULT NOW(),
	PRIMARY KEY(userId)
);