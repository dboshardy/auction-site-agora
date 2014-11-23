class AddColsToAuction < ActiveRecord::Migration
  def change
  	add_column :auctions, :user_id, :integer
  	add_column :auctions, :auction_start_time, :datetime
  	add_column :auctions, :auction_length, :integer
  	add_column :auctions, :item_name, :string
  	add_column :auctions, :item_desc, :text
  	add_column :auctions, :quantity, :integer
  	add_column :auctions, :buy_it_now, :boolean
  	add_column :auctions, :start_bid, :decimal
  	add_column :auctions, :shipping_cost, :decimal
  end
end
