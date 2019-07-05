CREATE TABLE IF NOT EXISTS user_entity
(
    id         BIGSERIAL       NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name  varchar(255) NOT NULL,
    email      varchar(255) NOT NULL,
    encrypted_password varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS book
(
    id            BIGSERIAL       NOT NULL,
    title         VARCHAR(255) NOT NULL,
    author        VARCHAR(255) NOT NULL,
    book_category VARCHAR(255) NOT NULL,
    user_id       BIGSERIAL      NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_entity (id),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_role
(
    id          BIGSERIAL       NOT NULL,
    role        VARCHAR(255) NOT NULL,
    description varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_entity_roles
(
    user_id  BIGSERIAL NOT NULL,
    roles_id BIGSERIAL NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_entity (id),
    FOREIGN KEY (roles_id) REFERENCES user_role (id)
);