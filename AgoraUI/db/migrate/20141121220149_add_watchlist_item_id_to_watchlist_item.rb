class AddWatchlistItemIdToWatchlistItem < ActiveRecord::Migration
  def change
    add_column :watchlist_items, :watchlist_item_id, :integer
  end
end
