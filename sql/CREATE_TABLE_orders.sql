CREATE TABLE orders
(
	orderId INT NOT NULL AUTO_INCREMENT,
	state INT NOT NULL DEFAULT 0,  -- orderstate.state
	ownerId INT NOT NULL,  -- users.userId
	purchaserId INT NOT NULL,  -- users.userId
	ownerLocationId INT NOT NULL,  -- locations.locationId
	-- purchaserLocationId INT NOT NULL,  -- locations.locationId
	createTime DATETIME NOT NULL DEFAULT NOW(),
	PRIMARY KEY(orderId)
);