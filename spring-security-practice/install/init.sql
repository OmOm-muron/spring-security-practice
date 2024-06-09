CREATE TABLE security_user (
    username VARCHAR(64) PRIMARY KEY,
    password VARCHAR(64) NOT NULL,
    enabled boolean NOT NULL DEFAULT true
);

CREATE TABLE role (
    username VARCHAR(64) NOT NULL REFERENCES security_user(username),
    role VARCHAR(32) NOT NULL
);
