CREATE TABLE app_users(
    id              BIGINT AUTO_INCREMENT  PRIMARY KEY,
    email           VARCHAR(200) NOT NULL UNIQUE,
    password_hash   VARCHAR(200) NOT NULL,
    enabled         BOOLEAN NOT NULL DEFAULT TRUE,
    roles           VARCHAR(100) NOT NULL DEFAULT 'USER'
);