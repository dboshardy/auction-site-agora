class AddAuctionStartTimeToAuctions < ActiveRecord::Migration
  def change
    add_column :auctions, :auction_start_time, :timestamp
  end
end
