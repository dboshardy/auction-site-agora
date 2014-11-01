class AuctionProcessor < ApplicationProcessor

  subscribes_to :auction

  def on_message(message)
    logger.debug "AuctionProcessor received: " + message
  end
end