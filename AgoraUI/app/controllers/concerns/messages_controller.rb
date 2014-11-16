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
    		return json_data["auctions"]

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