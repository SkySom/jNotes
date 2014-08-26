# Users schema

# --- !Ups

CREATE TABLE "User"
(
	"userId" integer NOT NULL,
	username character varying(255) NOT NULL,
	email character varying(255) NOT NULL,
	password character varying(255) NOT NULL,
	CONSTRAINT "User_pkey" PRIMARY KEY ("userId")
)
WITH (
	OIDS=FALSE
);

# --- !Downs

DROP TABLE User;