class ChangeMessageIdTypeInMessages < ActiveRecord::Migration
  def self.up
  	change_column :messages, :message_id, :string
  end
  def self.down
  	change_column :messages, :message_id, :text
  end
end
