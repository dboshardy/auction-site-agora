class AddEndTimeToAuction < ActiveRecord::Migration
  def change
    add_column :auctions, :end_time, :timestamp
  end
end
