class AddAuctionLengthToAuctions < ActiveRecord::Migration
  def change
    add_column :auctions, :length, :integer
  end
end
