class CreateAuctions < ActiveRecord::Migration
  def change
    create_table :auctions do |t|
      t.integer :seller_id

      t.timestamps
    end
  end
end
