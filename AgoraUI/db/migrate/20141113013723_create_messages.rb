class CreateMessages < ActiveRecord::Migration
  def change
    create_table :messages do |t|
      t.text :message_id
      t.text :body
      t.datetime :received_date

      t.timestamps
    end
  end
end
