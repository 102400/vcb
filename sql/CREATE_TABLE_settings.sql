CREATE TABLE settings
(
	userId INT NOT NULL UNIQUE,  -- users.userId
	defaultLocationId INT NOT NULL,  -- locations.locationId
	createTime DATETIME NOT NULL DEFAULT NOW(),
	PRIMARY KEY(userId)
);