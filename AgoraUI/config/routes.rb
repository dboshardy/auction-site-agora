Rails.application.routes.draw do
  resources :shopping_cart_items

  root "auctions#index"

  get "/users/:id/suspend" => "users#suspend"
  get "/auctions/search" => "auctions#search"
  get "/auctions/:id/stop" => "auctions#stop"
  get "/auctions/auctions_bid_on" => "auctions#auctions_bid_on"
  post "/auctions/keyword_search" => "auctions#keyword_search"
  post "/auctions/category_search" => "auctions#category_search"
  post "/auctions/:id/update" => "auctions#update"
  post "/bids/:id/create" => "bids#create"
  post "/bids/:id/buy_now" => "bids#create_buy_now"
  get "/bids/:id/auction_bid_history" => "bids#show_auction_history"
  get "/bids/:id/user_bid_history" => "bids#show_user_history"
  get "/watchlist/:id" => "watchlists#show"
  get "/watchlists/:id/new" => "watchlists#new"
  post "/watchlists/:id" => "watchlists#create"
  get "/flags/:id/new" => "flags#new"
  post "flags/:id/create" => "flags#create"
  get "/transactions/:id/new" => "transactions#new"
  post "/users/:id/edit" => "users#update"
  get "/users/logout" => "users#destroy_session"
  get "/users/login_page" => "users#login_page"
  post "/users/login" => "users#login"
  get "/categories/new_categories_for_auction/:id" => "categories#new_categories_for_auction"
  get "/categories/manage" => "categories#manage"
  post "/categories/:id/update" => "categories#update"

  resources :watchlists

  resources :watchlist_items

  resources :users

  resources :transactions

  resources :shopping_carts

  resources :items

  resources :flags

  resources :categories

  resources :bids

  resources :auctions

  resources :sessions

  # The priority is based upon order of creation: first created -> highest priority.
  # See how all your routes lay out with "rake routes".

  # You can have the root of your site routed with "root"
  # root 'welcome#index'

  # Example of regular route:
  #   get 'products/:id' => 'catalog#view'

  # Example of named route that can be invoked with purchase_url(id: product.id)
  #   get 'products/:id/purchase' => 'catalog#purchase', as: :purchase

  # Example resource route (maps HTTP verbs to controller actions automatically):
  #   resources :products

  # Example resource route with options:
  #   resources :products do
  #     member do
  #       get 'short'
  #       post 'toggle'
  #     end
  #
  #     collection do
  #       get 'sold'
  #     end
  #   end

  # Example resource route with sub-resources:
  #   resources :products do
  #     resources :comments, :sales
  #     resource :seller
  #   end

  # Example resource route with more complex sub-resources:
  #   resources :products do
  #     resources :comments
  #     resources :sales do
  #       get 'recent', on: :collection
  #     end
  #   end

  # Example resource route with concerns:
  #   concern :toggleable do
  #     post 'toggle'
  #   end
  #   resources :posts, concerns: :toggleable
  #   resources :photos, concerns: :toggleable

  # Example resource route within a namespace:
  #   namespace :admin do
  #     # Directs /admin/products/* to Admin::ProductsController
  #     # (app/controllers/admin/products_controller.rb)
  #     resources :products
  #   end
end
