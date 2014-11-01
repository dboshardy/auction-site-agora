class CreateFlags < ActiveRecord::Migration
  def change
    create_table :flags do |t|
      t.string :flag_type
      t.integer :flagging_user
      t.integer :auction_id

      t.timestamps
    end
  end
end
