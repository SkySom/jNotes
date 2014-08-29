# Notes schema

# --- !Ups
CREATE SEQUENCE noteidseq;
CREATE TABLE "note"
(
	noteid integer NOT NULL DEFAULT nextval('noteidseq'),
	name character varying(255) NOT NULL,
	message text NOT NULL,
	userid integer NOT NULL,
	CONSTRAINT note_pkey PRIMARY KEY (noteid),
	CONSTRAINT note_userid_fkey FOREIGN KEY (userid)
	REFERENCES "user" (userid) MATCH SIMPLE
	ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
	OIDS=FALSE
);
ALTER SEQUENCE noteidseq OWNED BY note.noteid;
# --- !Downs

DROP TABLE "note";