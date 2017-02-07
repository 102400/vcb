SELECT
	b.bookId,b.bookInfoId,bi.name AS bookInfoName,b.name AS bookName,b.publisherId,p.name AS publisherName,b.languageId,l.languageName,b.pages,b.isbn,b.price,b.publicationYear,b.publicationMonth
FROM
	books AS b,bookinfo AS bi,publishers AS p,languages AS l
WHERE
	b.bookId = 1
	AND b.bookInfoId = bi.bookInfoId
	AND b.publisherId = p.publisherId
	AND b.languageId = l.languageId;