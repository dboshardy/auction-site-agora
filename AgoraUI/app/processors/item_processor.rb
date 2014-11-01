class ItemProcessor < ApplicationProcessor

  subscribes_to :item

  def on_message(message)
    logger.debug "ItemProcessor received: " + message
  end
end