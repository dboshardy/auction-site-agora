json.array!(@shopping_cart_items) do |shopping_cart_item|
  json.extract! shopping_cart_item, :id, :shopping_cart_id, :item_id, :item_name, :item_desc, :item_price
  json.url shopping_cart_item_url(shopping_cart_item, format: :json)
end
