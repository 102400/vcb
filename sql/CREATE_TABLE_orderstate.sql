CREATE TABLE orderstate
(
	id INT NOT NULL AUTO_INCREMENT,
	orderId INT NOT NULL,  -- orders.orderId
	state INT NOT NULL,  -- stacks.itemId
	createTime DATETIME NOT NULL DEFAULT NOW(),  -- 完成state时间
	PRIMARY KEY(id)
);