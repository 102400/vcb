CREATE TABLE cart
(
	id INT NOT NULL AUTO_INCREMENT,
	itemId INT NOT NULL,  -- stacks.itemId
	purchaserId INT NOT NULL,  -- users.userId
	createTime DATETIME NOT NULL DEFAULT NOW(),
	PRIMARY KEY(id)
);