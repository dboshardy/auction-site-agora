class BidProcessor < ApplicationProcessor

  subscribes_to :bid_confirm

  def on_message(message)
    logger.debug "BidProcessor received: " + message
    create_new_message(message)
  end
end