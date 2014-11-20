class AddIdsToTables < ActiveRecord::Migration
  def change
  	add_column :watchlists, :watchlist_id, :integer
  	add_column :users, :user_id, :integer
  	add_column :transactions, :transaction_id, :integer
  	add_column :shopping_carts, :shopping_cart_id, :integer
  	add_column :items, :item_id, :integer
  	add_column :flags, :flag_id, :integer
  	add_column :categories, :category_id, :integer
  	add_column :bids, :bid_id, :integer
  end
end
