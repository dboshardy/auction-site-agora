class CreateShoppingCartItems < ActiveRecord::Migration
  def change
    create_table :shopping_cart_items do |t|
      t.integer :shopping_cart_id
      t.integer :item_id
      t.string :item_name
      t.text :item_desc
      t.float :item_price

      t.timestamps
    end
  end
end
