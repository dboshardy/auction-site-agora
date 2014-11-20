class WatchlistItemProcessor < ApplicationProcessor

  subscribes_to :watchlist_item_confirm

  def on_message(message)
    logger.debug "WatchlistItemProcessor received: " + message
    create_new_message(message)
  end
end