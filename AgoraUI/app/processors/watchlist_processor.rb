class WatchlistProcessor < ApplicationProcessor

  subscribes_to :watchlist

  def on_message(message)
    logger.debug "WatchlistProcessor received: " + message
  end
end