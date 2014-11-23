CREATE TABLE Emails
(
  email_id serial NOT NULL,
  sender_id serial NOT NULL,
  receiver_id serial NOT NULL,
  email_content character varying(10000),
  CONSTRAINT Emails_pk PRIMARY KEY (email_id)
)
WITH (
		OIDS=FALSE
);