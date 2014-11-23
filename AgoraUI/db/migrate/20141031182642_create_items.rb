class CreateItems < ActiveRecord::Migration
  def change
    create_table :items do |t|
      t.string :item_name
      t.text :item_desc

      t.timestamps
    end
  end
end
