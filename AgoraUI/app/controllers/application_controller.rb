require 'activemessaging/processor'
require 'json'

class ApplicationController < ActionController::Base
  # Prevent CSRF attacks by raising an exception.
  # For APIs, you may want to use :null_session instead.
  include ActiveMessaging::MessageSender  
  protect_from_forgery with: :exception

	def get_status(id)
		json_data = query_message(id)
        status = nil
        error = nil

		status = json_data["success"]
        error = nil

        if status != "true"
            error = json_data["error"]
        end

        return status, error
	end

	def get_auctions(id)
		message = query_message(id)
		auction_data = message["auctions"]
        auctions = []

        if auction_data.nil?
            return nil
        end

        auction_data.each do |auc|
            auction = Auction.new
            auction.auction_id = auc["auction_id"]
            auction.item_name = auc["auction_name"]
            auction.end_time = auc["end_time"]
            auction.item_desc = auc["item_desc"]

            bid = Bid.new
            bid.amount = auc["highest_bid"]

            array = [auction, bid]

            auctions.push(array)
        end

        return auctions
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
        cart.user_id = message["cart_id"]

        items = []

        message["items"].each do |item|
            cart_auction = Auction.new
            cart_auction.auction_id = item["item_id"]
            cart_auction.item_name = item["item_name"]
            cart_auction.item_desc = item["item_desc"]
            cart_item.item_price = item["item_price"]

            items.push(cart_item)
        end

        return cart, items
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

        category = Category.new
        category.category = json["category"]

        return auction, bid, bidder, seller, category

    end

    def get_categories(id)
        message = query_message(id)

        categories = []

        message["categories"].each do |c|
            category = Category.new
            category.category = c["category"]
            category.category_id = c["category_id"]
            categories.push(category)
        end

        return categories
    end

	def get_flags(id)

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

    def get_transaction(id)
        message = query_message(id)

        transaction = Transaction.new
        auction = Auction.new

        transaction.trans_date = t["date"]
        transaction.address = t["address"]
        transaction.city = t["city"]
        transcation.state = t["state"]
        transaction.zip_code = t["zip_code"]
        transaction.payment_type = t["payment_type"]
        transaction.total_cost = t["total_cost"]

        auction.auction_id = t["auction_id"]
        auction.item_name = t["auction_name"]
        auction.item_desc = t["item_desc"]

        return transaction, auction
    end

    def get_transactions(id)
        message = query_message(id)

        transactions = []

        message["transactions"].each do |t|
            transaction = Transaction.new
            auction = Auction.new

            transaction.transaction_id = t["transaction_id"]
            transaction.trans_date = t["date"]
            transaction.total_cost = t["total_cost"]

            auction.auction_id = t["auction_id"]
            auction.item_name = t["auction_name"]
            auction.item_desc = t["item_desc"]

            array = [transaction, auction]

            transactions.push(array)
        end

        return transactions
    end

    def get_auction_success(id)
        message = query_message(id)

        auction_id = message["auction_id"]
        status = message["status"]
        error = message["error"]

        return auction_id, status, error

    end

    def get_login_success(id)
        message = query_message(id)

        status = message["succeed"]
        error = message["Error"]
        user_id = message["user_id"]
        is_admin = message["is_admin"]

        return status, error, user_id, is_admin      
    end

    def get_success(id)
        message = query_message(id)

        status = message["succeed"]
        error = message["Error"]

        return status, error
    end

    def get_new_user_success(id)
        message = query_message(id)

        status = message["succeed"]
        error = message["Error"]
        user_id = nil

        if status
            user_id = message["user_id"]
        end

        return status, error, user_id
    end    

    def get_user(id)
        json_data = query_message(id)

        user = nil
        error = nil

        user = User.new
        user.user_id = json_data["user_id"]
        user.username = json_data["username"]
        user.first_name = json_data["first_name"]
        user.last_name = json_data["last_name"]
        user.user_description = json_data["user_description"]

        return user, error
    end

    def get_bids(id)
        message = query_message(id)
        bid_data = message["bids"]
        bids = []

        bid_data.each do |bid|
            b = Bid.new
            b.amount = bid["bid_amount"]
            b.bidder_id = bid["bidder_id"]
            b.auction_id = bid["auction_id"]

            u = User.new
            u.username = bid["bidder_username"]

            array = [b, u]

            bids.push(array)
        end

        return bids
    end

    def get_categories(id)
        message = query_message(id)
        categories = message["categories"]

        cats = []

        categories.each do |category|
            c = Category.new
            c.category = category["category"]
            c.category_id = category["category_id"]

            cats.push(c)
        end

        return cats
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
        if session[:user_id].nil? && !session[:is_admin]
            redirect_to "/users/new", notice: "You must log in or sign up"
        end
    end  

    def confirm_correct_user(id)
        if session[:user_id] != id.to_i

        end
    end

    def confirm_admin
        if (session[:is_admin].nil? || (session[:is_admin] == false))
            redirect_to root_url, notice: "You do not have sufficient privileges"
        end
    end
end
