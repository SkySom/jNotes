#Add hash to users

# --- !Ups
ALTER table users
  ADD COLUMN hash character varying(255) NOT NULL;

# --- !Downs
ALTER table users
  DROP COLUMN hash;