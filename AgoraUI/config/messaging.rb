#
# Add your destination definitions here
# can also be used to configure filters, and processor groups
#
ActiveMessaging::Gateway.define do |s|
  #s.destination :orders, '/queue/Orders'
  #s.filter :some_filter, :only=>:orders
  #s.processor_group :group1, :order_processor
  
  s.destination :auction, '/queue/Auction'
  s.destination :auction_confirm, '/queue/AuctionConfirm'

  s.destination :item, '/queue/Item'
  s.destination :item_confirm, 'queue/ItemConfirm'

  s.destination :watchlist, '/queue/Watchlist'
  s.destination :watchlist_confirm, '/queue/WatchlistConfirm'

  s.destination :watchlist_item, 'queue/WatchlistItem'
  s.destination :watchlist_item_confirm, '/queue/WatchlistItemConfirm'  

  s.destination :user, '/queue/User'
  s.destination :user_confirm, '/queue/UserConfirm'

  s.destination :bid, '/queue/Bid'
  s.destination :bid_confirm, '/queue/BidConfirm'

  s.destination :shopping_cart, '/queue/ShoppingCart'
  s.destination :shopping_cart_confirm, '/queue/ShoppingCartConfirm'

  s.destination :shopping_cart_item, '/queue/ShoppingCartItem'
  s.destination :shopping_cart_item_confirm, '/queue/ShoppingCartItemConfirm'

  s.destination :transaction, '/queue/Transction'
  s.destination :transaction_confirm, '/queue/TransactionConfirm'

  s.destination :category, '/queue/Category'
  s.destination :category_confirm, '/queue/CategoryConfirm'

  s.destination :email, '/queue/Email'
  s.destination :email_confirm, '/queue/EmailConfirm'

  s.destination :flag, '/queue/Flag'
  s.destination :flag_confirm, '/queue/FlagConfirm'
  
end