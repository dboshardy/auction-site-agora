class DropUserDescriptionFromUser < ActiveRecord::Migration
  def change
  	remove_column :users, :user_description
  	add_column :users, :user_description, :string
  end
end
