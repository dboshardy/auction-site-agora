class AddAmountToBod < ActiveRecord::Migration
  def change
  	remove_column :bids, :amount
  	add_column :bids, :amount, :decimal
  end
end
