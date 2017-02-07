CREATE TABLE books
(
	bookId INT NOT NULL AUTO_INCREMENT,
	bookInfoId INT NOT NULL DEFAULT 1,  -- bookinfo.bookInfoId
	name VARCHAR(255) NOT NULL,
	-- authors INT NOT NULL,
	-- translators INT NOT NULL,
	publisherId INT NOT NULL DEFAULT 1,  -- publishers.publisherId
	languageId INT NOT NULL DEFAULT 1,  -- languages.languageId
	pages INT NOT NULL DEFAULT -1,
	isbn VARCHAR(20) NOT NULL DEFAULT '',
	price DOUBLE NOT NULL DEFAULT -1,
	-- cover
	-- country
	publicationYear INT NOT NULL DEFAULT 0,
	publicationMonth INT NOT NULL DEFAULT 0,
	modifyTime DATETIME,
	createTime DATETIME NOT NULL DEFAULT NOW(),
	PRIMARY KEY(bookId)
);