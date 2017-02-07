INSERT INTO users
(
	userId,
	userName,
	userEmail,
	userPasswordMd5,
	userNickname
)
VALUES
(
	1,
	'root',
	'root@domain.com',
	'5F4DCC3B5AA765D61D8327DEB882CF99',  -- -> 'password'
	'root'
);
INSERT INTO users
(
	userName,
	userEmail,
	userPasswordMd5,
	userNickname
)
VALUES
(
	'abc',
	'abc@domain.com',
	'5F4DCC3B5AA765D61D8327DEB882CF99',  -- -> 'password'
	'abc'
);