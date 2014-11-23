CREATE TABLE Emails
(
  email_id serial NOT NULL,
  sender_id serial NOT NULL,
  receiver_id serial NOT NULL,
  CONSTRAINT Emails_pk PRIMARY KEY (email_id)
)
WITH (
		OIDS=FALSE
);