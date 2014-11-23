class CreateTransactions < ActiveRecord::Migration
  def change
    create_table :transactions do |t|
      t.date :trans_date
      t.integer :bidder
      t.integer :seller

      t.timestamps
    end
  end
end
