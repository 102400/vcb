CREATE TABLE orderitems
(
	id INT NOT NULL AUTO_INCREMENT,
	orderId INT NOT NULL,  -- orders.orderId
	itemId INT NOT NULL,  -- stacks.itemId
	createTime DATETIME NOT NULL DEFAULT NOW(),
	PRIMARY KEY(id)
);