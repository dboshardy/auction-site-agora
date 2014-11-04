--------------------------------------------------
--Definition for Table: UserAccounts 
--------------------------------------------------
CREATE TABLE UserAccounts 
(
    user_id integer NOT NULL,
    first_name character varying(45),
    last_name character varying(45),
    user_description character varying(45),
    watch_list character varying(45),
    password_hash character varying(45),
    CONSTRAINT UserAccounts_pk PRIMARY KEY (user_id)
)
WITH (
    OIDS=FALSE
);

--------------------------------------------------
--Definition for Table: Auctions 
--------------------------------------------------
CREATE TABLE Auctions 
(
    auction_id integer NOT NULL,
    list_time integer,
    current_highest_bid_id integer NOT NULL,
    auction_description character varying(45),
    end_time character varying(45),
    buy_it_now_price money NOT NULL,
    seller_user_id integer NOT NULL,
    is_ended boolean,
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
    CONSTRAINT User_has_Watchlist_Auctions_pk PRIMARY KEY (UserAccounts_user_id,Auctions_auction_id)
)
WITH (
    OIDS=FALSE
);

--------------------------------------------------
--Definition for Table: Bids 
--------------------------------------------------
CREATE TABLE Bids 
(
    bid_id integer NOT NULL,
    auction_id integer NOT NULL,
    timestamp integer,
    bidder_user_id integer NOT NULL,
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
