CREATE TABLE do
(
	id INT NOT NULL AUTO_INCREMENT,
	bookId INT NOT NULL,  -- books.bookId
	userId INT NOT NULL,  -- users.userId
	createTime DATETIME NOT NULL DEFAULT NOW(),
	PRIMARY KEY(id)
);