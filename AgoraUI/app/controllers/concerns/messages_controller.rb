require 'json'

class MessagesController < ApplicationController

	def get_status(id)

		message = query_message(id)

    	if message.nil?
    		return nil
    	else
    		json_data = JSON.parse(message)
    		status = json_data["success"]

    		if status == "true"
    			return "Success"
    		else 
    			return json_data["error"]
    		end

    	end
	end

	def get_auctions(id)

		message = query_message(id)

    	if message.nil?
    		return nil
    	else
    		json_data = JSON.parse(message)
    		auction_data = json_data["auctions"]

            auctions = []

            auction_data.each do |auc|

                auction = Auction.new
                auction.auction_id = auc["auction_id"]
                auction.item_name = auc["item_name"]
                auction.item_desc = auc["item_desc"]
                auction.highest_bid = auc["highest_bid"]

                auctions.push(auction)
            end

            return auctions

    	end
	end

    def get_auction(id)

        message = query_message(id)

        if message.nil?
            return nil
        else
            json = JSON.parse(message)

            auction = Auction.new
            auction.auction_id = json["auction_id"]
            auction.item_name = json["item_name"]
            auction.item_desc = json["item_desc"]
            auction.highest_bid = json["highest_bid"]

            bidder = User.new
            bidder.username = json["bidder_username"]

            seller = User.new
            seller.id = json["seller_id"]
            seller.username = json["seller_username"]

            return auction, bidder, seller

        end
    end

	def get_categories(id)

		message = query_message(id)

    	if message.nil?
    		return nil
    	else
    		json_data = JSON.parse(message)
    		return json_data["categories"]

    	end
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

	def query_message(id)
	    attempts = 0

	    message_id = nil

	    message = Message.find_by(:message_id => id)

	    while attempts < 10 # change after testing

	      if message.nil?
	        sleep(1)
	        attempts += 1
	        message = Message.find_by(:message_id => id)
	      else
	        break
	      end
	  	end

	    return message

    end		

end