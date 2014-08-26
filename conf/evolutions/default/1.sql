# Users schema

# --- !Ups

CREATE TABLE User (
    id bigint(20) NOT NULL,
    email varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    PRIMARY KEY (id ASC)
);

# --- !Downs

DROP TABLE User;