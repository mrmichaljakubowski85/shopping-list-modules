CREATE TABLE products (
    id uuid NOT NULL,
    createDateTime timestamp,
    updateDateTime timestamp,
    checked boolean NOT NULL,
    description varchar(1000),
    quantity varchar(255),
    title varchar(255) NOT NULL,
    unit varchar(255),
    user_id bigint NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE roles (
    id bigint NOT NULL,
    name varchar(255) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE users (
    id bigint not null,
    createDateTime timestamp,
    updateDateTime timestamp,
    email varchar(255) NOT NULL,
    firstName varchar(255),
    lastName varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    userName varchar(255) NOT NULL,
    primary key (id)
);

CREATE TABLE users_roles (
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);

CREATE SEQUENCE role_seq START 1 INCREMENT 50;
CREATE SEQUENCE user_seq START 1 INCREMENT 50;

ALTER TABLE IF EXISTS products
    ADD CONSTRAINT products_user_id_fkey
    FOREIGN KEY (user_id)
    REFERENCES users;

ALTER TABLE IF EXISTS users_roles
    ADD CONSTRAINT users_roles_role_id_fkey
    FOREIGN KEY (role_id)
    REFERENCES roles;

ALTER TABLE IF EXISTS users_roles
    ADD CONSTRAINT users_roles_user_id_fkey
    FOREIGN KEY (user_id)
    REFERENCES users;