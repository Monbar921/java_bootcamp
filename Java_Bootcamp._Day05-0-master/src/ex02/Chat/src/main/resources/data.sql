INSERT INTO chat.users (login, password)
VALUES ('Bob', '000000');
INSERT INTO chat.users (login, password)
VALUES ('Billy', '1200000');
INSERT INTO chat.users (login, password)
VALUES ('Logan', '000000123');
INSERT INTO chat.users (login, password)
VALUES ('Gachi', '000000');
INSERT INTO chat.users (login, password)
VALUES ('Andrew', '000000');

INSERT INTO chat.rooms (name, owner)
VALUES ('Random', 1);
INSERT INTO chat.rooms (name, owner)
VALUES ('General', 2);
INSERT INTO chat.rooms (name, owner)
VALUES ('dungeon', 3);
INSERT INTO chat.rooms (name, owner)
VALUES ('Lols', 4);
INSERT INTO chat.rooms (name, owner)
VALUES ('NMA', 5);

INSERT INTO chat.messages (author, room, text, datetime)
VALUES (1, 1, 'kek', '2020-01-01 00:00:01');
INSERT INTO chat.messages (author, room, text, datetime)
VALUES (2, 3, 'lmao', '2020-01-01 00:00:01');
INSERT INTO chat.messages (author, room, text, datetime)
VALUES (3, 3, '300 bucks', '2020-01-01 00:00:02');
INSERT INTO chat.messages (author, room, text, datetime)
VALUES (4, 4, 'RIPPERONI', '2020-01-01 00:00:04');
INSERT INTO chat.messages (author, room, text, datetime)
VALUES (5, 5, 'NMA', '2020-01-01 00:00:05');