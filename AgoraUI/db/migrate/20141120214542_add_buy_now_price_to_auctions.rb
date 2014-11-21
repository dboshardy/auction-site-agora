class AddBuyNowPriceToAuctions < ActiveRecord::Migration
  def change
    add_column :auctions, :buy_now_price, :integer
  end
end
