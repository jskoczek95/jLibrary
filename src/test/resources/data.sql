CREATE TABLE IF NOT EXISTS user_entity
(
    id         BIGSERIAL       NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name  varchar(255) NOT NULL,
    email      varchar(255) NOT NULL,
    encrypted_password varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO user_entity VALUES ('1', 'Kuba', 'Skoczek', 'test@test.com', '{bcrypt}$2a$10$iJFT7UTRvgOhrbygFVz80emdsh2CyF.0f7qLOLvZhEYWPMVmh4u/S');