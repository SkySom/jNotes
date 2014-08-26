# Notes schema

# --- !Ups

CREATE TABLE Note (
  noteId bigint(20) NOT NULL,
  name varchar(255) NOT NULL,
  message text NOT NULL,
  userId bigint(20) NOT NULL,
  PRIMARY KEY (noteId ASC),
  FOREIGN KEY(userId) REFERENCES User(userId)
);

# --- !Downs

DROP TABLE Note;