class CreateWatchlistItems < ActiveRecord::Migration
  def change
    create_table :watchlist_items do |t|
      t.integer :watchlist_id
      t.integer :user_id
      t.string :item_name
      t.text :item_description
      t.float :item_price

      t.timestamps
    end
  end
end
