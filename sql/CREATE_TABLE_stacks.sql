CREATE TABLE stacks
(
	itemId INT NOT NULL AUTO_INCREMENT,
	bookId INT NOT NULL,  -- books.bookId
	ownerId INT NOT NULL,  -- users.userId
	holderId INT NOT NULL,  -- users.userId
	isLoan BOOLEAN NOT NULL DEFAULT FALSE,
	canBorrow BOOLEAN NOT NULL DEFAULT FALSE,
	ownerLocationId INT NOT NULL,  -- locations.locationId
	createTime DATETIME NOT NULL DEFAULT NOW(),
	PRIMARY KEY(itemId)
);