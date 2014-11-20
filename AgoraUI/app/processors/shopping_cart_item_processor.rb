class ShoppingCartItemProcessor < ApplicationProcessor

  subscribes_to :shopping_cart_item_confirm

  def on_message(message)
    logger.debug "ShoppingCartItemProcessor received: " + message
    create_new_message(message)
  end
end