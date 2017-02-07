SELECT
c.id AS cartId,c.itemId,c.purchaserId,s.bookId,b.name AS bookName,s.ownerId,u.userNickname AS ownerNickname,l.locationId,l.name AS locationName,l.n,l.e
FROM
cart AS c,stacks AS s,books AS b,locations AS l,users AS u
WHERE
c.purchaserId = 2
AND c.itemId = s.itemId
AND s.bookId = b.bookId
AND s.ownerLocationId = l.locationId
AND s.ownerId = u.userId;