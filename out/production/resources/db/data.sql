INSERT INTO T_USERS VALUES (1, 'USER0');
INSERT INTO T_USERS VALUES (2, 'USER1');
INSERT INTO T_USERS VALUES (3, 'USER2');
INSERT INTO T_USERS VALUES (4, 'USER3');
INSERT INTO T_USERS VALUES (5, 'USER4');

INSERT INTO USERS (username, password, enabled) values ('admin', 'password', true);
INSERT INTO USERS (username, password, enabled) values ('user', 'password', true);

INSERT INTO AUTHORITIES (username, authority) values ('user', 'ROLE_USER');
INSERT INTO AUTHORITIES (username, authority) values ('admin', 'ROLE_ADMIN');