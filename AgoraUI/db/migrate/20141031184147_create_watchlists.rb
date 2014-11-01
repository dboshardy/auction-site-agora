class CreateWatchlists < ActiveRecord::Migration
  def change
    create_table :watchlists do |t|
      t.string :watchlist_name

      t.timestamps
    end
  end
end
