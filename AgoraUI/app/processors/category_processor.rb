class CategoryProcessor < ApplicationProcessor

  subscribes_to :category_confirm

  def on_message(message)
    logger.debug "CategoryProcessor received: " + message
    create_new_message(message)
  end
end