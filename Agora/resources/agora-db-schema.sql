--------------------------------------------------
--Definition for Table: UserAccounts 
--------------------------------------------------
CREATE TABLE UserAccounts 
(
    user_id serial NOT NULL,
    username character varying(45) NOT NULL,
    first_name character varying(45),
    last_name character varying(45),
    user_description character varying(1000),
    password_hash character varying(256) NOT NULL,
    user_joined_date timestamp NOT NULL,
    CONSTRAINT UserAccounts_pk PRIMARY KEY (user_id)
)
WITH (
    OIDS=FALSE
);

CREATE TABLE FlaggedAuctions
(
		flag_id serial NOT NULL
		auction_id int not null,
		CONSTRAINT FlaggedUsers PRIMARY KEY (flag_id,auction_id)
)
WITH (
    OIDS=FALSE
);

CREATE TABLE FlaggedUsers
(
		flag_id serial NOT NULL
		user_id int not null,
		CONSTRAINT FlaggedUsers PRIMARY KEY (flag_id,user_id)
)
WITH (
    OIDS=FALSE
);
--------------------------------------------------
--Definition for Table: Auctions 
--------------------------------------------------
CREATE TABLE Auctions 
(
    auction_id serial NOT NULL,
    list_time timestamp NOT NULL,
    current_highest_bid_id integer NOT NULL,
    auction_description character varying(1000),
    end_time timestamp,
    buy_it_now_price money NOT NULL,
    seller_user_id integer NOT NULL,
    is_ended boolean default false,
    CONSTRAINT Auctions_pk PRIMARY KEY (auction_id)
)
WITH (
    OIDS=FALSE
);

--------------------------------------------------
--Definition for Table: User_has_Watchlist_Auctions 
--------------------------------------------------
CREATE TABLE User_has_Watchlist_Auctions 
(
    UserAccounts_user_id integer NOT NULL,
    Auctions_auction_id integer NOT NULL,
    watchlist_name character varying(45) default 'default' NOT NULL,
    CONSTRAINT User_has_Watchlist_Auctions_pk PRIMARY KEY (UserAccounts_user_id,Auctions_auction_id,watchlist_name)
)
WITH (
    OIDS=FALSE
);

--------------------------------------------------
--Definition for Table: Bids 
--------------------------------------------------
CREATE TABLE Bids 
(
    bid_id serial NOT NULL,
    auction_id integer NOT NULL, timestamp timestamp, bidder_user_id integer NOT NULL,
    bid_amount money NOT NULL,
    currency character varying(45),
    CONSTRAINT Bids_pk PRIMARY KEY (bid_id,auction_id)
)
WITH (
    OIDS=FALSE
);

--------------------------------------------------
--Definition for Table: User_has_ShoppingCart_Auctions 
--------------------------------------------------
CREATE TABLE User_has_ShoppingCart_Auctions 
(
    UserAccounts_user_id integer NOT NULL,
    Auctions_auction_id integer NOT NULL,
    CONSTRAINT User_has_ShoppingCart_Auctions_pk PRIMARY KEY (UserAccounts_user_id,Auctions_auction_id)
)
WITH (
    OIDS=FALSE
);

CREATE TABLE Categories 
(
		category_id serial NOT NULL,
		category_name character varying(45),
	  parent_id int,
		CONSTRAINT Categories_pk PRIMARY KEY (category_id)
)
WITH (
		OIDS=FALSE
);

CREATE TABLE CategoryChildren 
(
		parent_id NOT NULL,
		child_id NOT NULL,
		constraint Category_Chilren_pk PRIMARY KEY (parent_id,child_id)
)
WITH (
		OIDS=FALSE
);

------------------

ALTER TABLE Auctions ADD CONSTRAINT fk_Auctions_UserAccounts1 FOREIGN KEY (seller_user_id)
        REFERENCES UserAccounts (user_id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE User_has_Watchlist_Auctions ADD CONSTRAINT fk_Auctions_has_UserAccounts_Auctions FOREIGN KEY (Auctions_auction_id)
        REFERENCES Auctions (auction_id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE User_has_Watchlist_Auctions ADD CONSTRAINT fk_Auctions_has_UserAccounts_UserAccounts1 FOREIGN KEY (UserAccounts_user_id)
        REFERENCES UserAccounts (user_id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE Bids ADD CONSTRAINT fk_Bids_Auctions1 FOREIGN KEY (auction_id)
        REFERENCES Auctions (auction_id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE Bids ADD CONSTRAINT fk_Bids_UserAccounts1 FOREIGN KEY (bidder_user_id)
        REFERENCES UserAccounts (user_id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE User_has_ShoppingCart_Auctions ADD CONSTRAINT fk_UserAccounts_has_Auctions_UserAccounts1 FOREIGN KEY (UserAccounts_user_id)
        REFERENCES UserAccounts (user_id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE User_has_ShoppingCart_Auctions ADD CONSTRAINT fk_UserAccounts_has_Auctions_Auctions1 FOREIGN KEY (Auctions_auction_id)
        REFERENCES Auctions (auction_id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE FlaggedUsers ADD CONSTRAINT fk_FlaggedUsers1 FOREIGN KEY (UserAccounts_user_id)
				REFERENCES UserAccounts (user_id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE FlaggedAuctions ADD CONSTRAINT fk_FlaggedUsers1 FOREIGN KEY (Auctions_Auction_id)
				REFERENCES Auctions (auction_id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE CategoryChildren ADD CONSTRAINT fk_Categories1 FOREIGN KEY (child_id)
				REFERENCES Categories (category_id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
