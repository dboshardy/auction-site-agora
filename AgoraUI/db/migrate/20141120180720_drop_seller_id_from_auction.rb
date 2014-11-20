class DropSellerIdFromAuction < ActiveRecord::Migration
  def change
  	remove_column :auctions, :seller_id
  	add_column :auctions, :auction_id, :integer
  end
end
