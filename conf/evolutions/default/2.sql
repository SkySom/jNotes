# Notes schema

# --- !Ups

CREATE TABLE "Note"
(
	"noteId" integer NOT NULL,
	name character varying(255) NOT NULL,
	message text NOT NULL,
	"userId" integer NOT NULL,
	CONSTRAINT "Note_pkey" PRIMARY KEY ("noteId"),
	CONSTRAINT "Note_userId_fkey" FOREIGN KEY ("userId")
	REFERENCES "User" ("userId") MATCH SIMPLE
	ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
	OIDS=FALSE
);

# --- !Downs

DROP TABLE Note;