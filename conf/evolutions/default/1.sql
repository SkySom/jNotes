# Users schema

# --- !Ups
CREATE SEQUENCE useridseq;
CREATE TABLE "user"
(
	userid integer NOT NULL DEFAULT nextval('useridseq'),
	username character varying(255) NOT NULL,
	email character varying(255) NOT NULL,
	password character varying(255) NOT NULL,
	CONSTRAINT user_pkey PRIMARY KEY (userid)
)
WITH (
	OIDS=FALSE
);
ALTER SEQUENCE useridseq OWNED BY user.userid;
# --- !Downs

DROP TABLE "user";