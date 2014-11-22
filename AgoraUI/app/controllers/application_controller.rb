require 'activemessaging/processor'
require 'json'

class ApplicationController < ActionController::Base
  # Prevent CSRF attacks by raising an exception.
  # For APIs, you may want to use :null_session instead.
  include ActiveMessaging::MessageSender  
  protect_from_forgery with: :exception

	def get_status(id)

		message = query_message(id)
        status = nil
        error = nil

    	if message.nil?
            status = "false"
            error = "database could not be reached"
    	else
    		json_data = JSON.parse(message)
    		status = json_data["success"]
            error = nil

            if status != "true"
                error = json_data["error"]
            end
    	end

        return status, error
	end

	def get_auctions(id)

		message = query_message(id)

    	if message.nil?
    		return nil
    	else
    		auction_data = message["auctions"]

            auctions = []

            auction_data.each do |auc|

                auction = Auction.new
                auction.auction_id = auc["auction_id"]
                auction.item_name = auc["item_name"]
                auction.item_desc = auc["item_desc"]

                bid = Bid.new
                bid.amount = auc["highest_bid"]

                array = [auction, bid]

                auctions.push(array)
            end

            return auctions

    	end
	end

    def get_watchlist(id)
        json = query_message(id)

        watchlist = Watchlist.new
        watchlist_name = json["watchlist_name"]

        watchlist_items = []

        items = json["watchlist_items"]

        if !items[0].nil? 
            items.each do |item|
                w_item = WatchlistItem.new
                w_item.item_id = item["item_id"]
                w_item.item_name = item["item_name"]
                w_item.item_description = item["item_desc"]
                w_item.item_price = item["item_price"]

                watchlist_items.push(w_item)

            end
        end

        return watchlist, watchlist_items
    end

    def get_cart(id)
        message = query_message(id)

        cart = ShoppingCart.new
        cart.user_id = 
    end

    def get_auction(id)

        json = query_message(id)

        auction = Auction.new
        auction.auction_id = json["auction_id"]
        auction.item_name = json["item_name"]
        auction.item_desc = json["item_desc"]
        auction.buy_it_now = json["buy_it_now"]
        auction.buy_now_price = json["buy_now_price"]

        bid = Bid.new
        bid.amount = json["highest_bid"]

        bidder = User.new
        bidder.user_id = json["bidder_id"]
        bidder.username = json["bidder_username"]

        seller = User.new
        seller.user_id = json["seller_id"]
        seller.username = json["seller_username"]

        return auction, bid, bidder, seller

    end

	def get_categories(id)

		message = query_message(id)

        json_flags = message["flags"]
        flags = []

        json_flags.each do |f|
            flag = Flag.new
            auction = Auction.new

            flag.flag_id = f["flag_id"]
            flag.flag_type = f["flag_type"]
            flag.flag_description = f["flag_desc"]
            
            auction.auction_id = f["auction_id"]
            auction.item_name = f["item_name"]
            auction.item_desc = f["item_desc"]
            auction.user_id = f["seller_id"]

            array = [flag, auction]

            flags.push(array)

        end

        return flags
    end

    def get_flags(id)

        message = query_message(id)


    end

    def get_success(id)

        message = query_message(id)

        if message.nil?
            return "Error reaching database"
        else
            json_data = JSON.parse(message)
            status = json_data["success"]
            error = json_data["error"]

            return status, error
        end
    end

    def get_user(id)
        message = query_message(id)

        user = nil
        error = nil

        if message.nil?
            error = "Error reaching database"
        else
            json_data = JSON.parse(message)
            user = User.new
            user.username = json_data[:username]
            user.first_name = json_data[:first_name]
            user.last_name = json_data[:last_name]
            user.user_description = json_data[:user_description]
        end

        return user, error
    end

    def get_bids(id)
        message = query_message(id)

        if message.nil?
            return nil
        else
            json_data = JSON.parse(message)
            bid_data = json_data["auctions"]

            bids = []

            bid_data.each do |bid|

                b = Bid.new
                b.bid_amount = bid["bid_amount"]
                b.bidder_id = bid["bidder"]
                b.auction_id = bid["auction_id"]

                bids.push(b)
            end

            return bids

        end                
    end

	def query_message(id)
	    attempts = 0

	    message_id = nil

	    message = Message.find_by(:message_id => id)

	    while attempts < 100 # change after testing

	      if message.nil?
	        sleep(1.0/10.0)
	        attempts += 1
	        message = Message.find_by(:message_id => id)
	      else
	        break
	      end
	  	end

        json_data = JSON.parse(message.body)
        json = json_data["response"]

        return json

    end		

    private

    def confirm_user
        if session[:user_id].nil?
            redirect_to "/users/new", notice: "You must log in or sign up to create a new auction"
        end
    end  

    def confirm_admin
        if (session[:is_admin].nil? || (session[:is_admin] == false))
            redirect_to root_url, notice: "You do not have sufficient privileges"
        end
    end
end
