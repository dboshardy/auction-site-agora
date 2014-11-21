class AddBuyNowToBid < ActiveRecord::Migration
  def change
  	add_column :bids, :buy_now, :boolean
  end
end
