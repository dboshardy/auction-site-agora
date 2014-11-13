require 'json'

class AuctionProcessor < ApplicationProcessor

  subscribes_to :auction_confirm

  def on_message(message)
    logger.debug "AuctionProcessor received: " + message
    message_new = Message.new
    message_new.message_id = parse_message_id(message)
    message_new.body = message
    message_new.received_date = DateTime.new
    message_new.save

  end

  def parse_message_id(message)
  	json = JSON.parse(message)
  	return json["id"]
  end

end