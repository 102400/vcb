CREATE TABLE messages
(
	id INT NOT NULL AUTO_INCREMENT,
	senderId INT NOT NULL,  -- users.userId
	receiverId INT NOT NULL,  -- users.userId
	message VARCHAR(21000) NOT NULL,
	createTime DATETIME NOT NULL DEFAULT NOW(),
	PRIMARY KEY(id)
);