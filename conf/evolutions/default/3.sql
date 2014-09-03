#Add hash to users

# --- !Ups
ALTER table "user"
  ADD COLUMN hash character varying(255) NOT NULL;

# --- !Downs
ALTER table "user"
  DROP COLUMN hash;