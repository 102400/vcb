CREATE TABLE booktranslators
(
	id INT NOT NULL AUTO_INCREMENT,
	bookId INT NOT NULL,  -- books.bookId
	bookTranslatorId INT NOT NULL,  -- celebrites.celebrityId
	createTime DATETIME NOT NULL DEFAULT NOW(),
	PRIMARY KEY(id)
);