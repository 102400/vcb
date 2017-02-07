SELECT
oi.id AS orderitemId,oi.orderId,oi.itemId,
b.bookId,b.name AS bookName,
ou.userId AS ownerId,ou.userNickname AS ownerNickname,
hu.userId AS holderId,hu.userNickname AS holderNickname,
s.ownerLocationId,
l.name AS locationName,l.n,l.e
FROM
orderitems AS oi,stacks AS s,books AS b,users AS ou,users AS hu,locations AS l
WHERE 
oi.orderId = 1
AND oi.itemId = s.itemId
AND s.bookId = b.bookId
AND s.ownerId = ou.userId
AND s.holderId = hu.userId
AND s.ownerLocationId = l.locationId;