# Users schema

# --- !Ups

CREATE TABLE User (
    userId bigint(20) NOT NULL,
    email varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    PRIMARY KEY (userId ASC)
);

# --- !Downs

DROP TABLE User;