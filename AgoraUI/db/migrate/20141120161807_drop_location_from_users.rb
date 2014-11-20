class DropLocationFromUsers < ActiveRecord::Migration
  def change
  	remove_column :users, :location
  	remove_column :users, :email
  	add_column :users, :first_name, :string
  	add_column :users, :last_name, :string
  	add_column :users, :user_description, :text	
  end
end
