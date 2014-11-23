class DropUserIdFromWatchlistItems < ActiveRecord::Migration
  def change
  	remove_column :watchlist_items, :user_id
  end
end
