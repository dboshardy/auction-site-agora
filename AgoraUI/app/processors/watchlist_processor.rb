class WatchlistProcessor < ApplicationProcessor

  subscribes_to :watchlist_confirm

  def on_message(message)
    logger.debug "WatchlistProcessor received: " + message
    create_new_message(message)
  end
end