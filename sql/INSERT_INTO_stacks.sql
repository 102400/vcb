INSERT INTO stacks
(
	itemId,
	bookId,
	ownerId,
	holderId,
	isLoan,
	canBorrow,
	ownerLocationId
)
VALUES
(
	1,
	1,
	1,
	1,
	FALSE,
	TRUE,
	1
);
INSERT INTO stacks
(
	bookId,
	ownerId,
	holderId,
	isLoan,
	canBorrow,
	ownerLocationId
)
VALUES
(
	2,
	1,
	1,
	FALSE,
	FALSE,
	3
);
INSERT INTO stacks
(
	bookId,
	ownerId,
	holderId,
	isLoan,
	canBorrow,
	ownerLocationId
)
VALUES
(
	3,
	1,
	2,
	TRUE,
	TRUE,
	1
);
INSERT INTO stacks
(
	bookId,
	ownerId,
	holderId,
	isLoan,
	canBorrow,
	ownerLocationId
)
VALUES
(
	4,
	1,
	2,
	TRUE,
	TRUE,
	1
);