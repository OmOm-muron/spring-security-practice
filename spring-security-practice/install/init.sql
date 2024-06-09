CREATE TABLE users (
    username VARCHAR(64) PRIMARY KEY,
    password VARCHAR(64) NOT NULL
);

CREATE TABLE authorities (
    username VARCHAR(64) NOT NULL REFERENCES users(username),
    authority VARCHAR(32) NOT NULL
);

INSERT INTO users (
    username, password
) VALUES (
    'admin', '$2a$08$SOIknkK2e/D5lGyApRKSoe/lKYc6axgOsR3abEHtjjBJQWDUuIGAy'
);

INSERT INTO authorities (
    username, authority
) VALUES (
    'admin', 'ROLE_USER'
);
