# --- !Ups
ALTER table users
  ADD CONSTRAINT adduniques UNIQUE (username, email);

# --- !Downs
ALTER table users
  DROP CONSTRAINT adduniques;