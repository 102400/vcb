SELECT
	c.celebrityId,c.nameZh,c.nameEn,c.sex,c.birthday
FROM
	bookauthors as ba,celebrites AS c
WHERE 
	ba.bookId = 3
	AND ba.bookAuthorId = c.celebrityId;