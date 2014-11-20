class ShoppingCartProcessor < ApplicationProcessor

  subscribes_to :shopping_cart_confirm

  def on_message(message)
    logger.debug "ShoppingCartProcessor received: " + message
    create_new_message(message)
  end
end