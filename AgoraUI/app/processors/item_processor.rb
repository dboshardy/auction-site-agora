class ItemProcessor < ApplicationProcessor

  subscribes_to :item_confirm

  def on_message(message)
    logger.debug "ItemProcessor received: " + message
    create_new_message(message)
  end
end