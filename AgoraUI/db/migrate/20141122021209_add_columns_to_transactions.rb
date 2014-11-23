class AddColumnsToTransactions < ActiveRecord::Migration
  def change
    add_column :transactions, :auction_id, :integer
    add_column :transactions, :address, :string
    add_column :transactions, :city, :string
    add_column :transactions, :state, :string
    add_column :transactions, :zip_code, :integer
    add_column :transactions, :payment_type, :string
    add_column :transactions, :payment_details, :string
  end
end
