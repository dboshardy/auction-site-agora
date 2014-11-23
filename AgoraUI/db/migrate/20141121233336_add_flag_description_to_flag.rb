class AddFlagDescriptionToFlag < ActiveRecord::Migration
  def change
    add_column :flags, :flag_description, :text
  end
end
