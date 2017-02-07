CREATE TABLE bookauthors
(
	id INT NOT NULL AUTO_INCREMENT,
	bookId INT NOT NULL,  -- books.bookId
	bookAuthorId INT NOT NULL,  -- celebrites.celebrityId
	createTime DATETIME NOT NULL DEFAULT NOW(),
	PRIMARY KEY(id)
);