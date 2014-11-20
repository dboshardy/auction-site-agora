class AuctionProcessor < ApplicationProcessor

  subscribes_to :auction_confirm

  def on_message(message)
    logger.debug "AuctionProcessor received: " + message

    create_new_message(message)

  end

end