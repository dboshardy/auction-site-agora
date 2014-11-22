# encoding: UTF-8
# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20141122005041) do

  # These are extensions that must be enabled in order to support this database
  enable_extension "plpgsql"

  create_table "auction_messages", force: true do |t|
    t.text     "body"
    t.datetime "received_date"
    t.datetime "created_at"
    t.datetime "updated_at"
    t.boolean  "read"
  end

  create_table "auctions", force: true do |t|
    t.datetime "created_at"
    t.datetime "updated_at"
    t.integer  "user_id"
    t.datetime "auction_start_time"
    t.integer  "auction_length"
    t.string   "item_name"
    t.text     "item_desc"
    t.integer  "quantity"
    t.boolean  "buy_it_now"
    t.decimal  "start_bid"
    t.decimal  "shipping_cost"
    t.integer  "auction_id"
    t.integer  "buy_now_price"
  end

  create_table "bids", force: true do |t|
    t.integer  "bidder_id"
    t.integer  "currency_id"
    t.integer  "amount"
    t.integer  "auction_id"
    t.datetime "created_at"
    t.datetime "updated_at"
    t.integer  "bid_id"
    t.boolean  "buy_now"
  end

  create_table "categories", force: true do |t|
    t.string   "category"
    t.datetime "created_at"
    t.datetime "updated_at"
    t.integer  "category_id"
  end

  create_table "flags", force: true do |t|
    t.string   "flag_type"
    t.integer  "flagging_user"
    t.integer  "auction_id"
    t.datetime "created_at"
    t.datetime "updated_at"
    t.integer  "flag_id"
    t.text     "flag_description"
  end

  create_table "items", force: true do |t|
    t.string   "item_name"
    t.text     "item_desc"
    t.datetime "created_at"
    t.datetime "updated_at"
    t.integer  "item_id"
  end

  create_table "messages", force: true do |t|
    t.string   "message_id"
    t.text     "body"
    t.datetime "received_date"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "sessions", force: true do |t|
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "shopping_cart_items", force: true do |t|
    t.integer  "shopping_cart_id"
    t.integer  "item_id"
    t.string   "item_name"
    t.text     "item_desc"
    t.float    "item_price"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "shopping_carts", force: true do |t|
    t.integer  "user_id"
    t.datetime "created_at"
    t.datetime "updated_at"
    t.integer  "shopping_cart_id"
  end

  create_table "transactions", force: true do |t|
    t.date     "trans_date"
    t.integer  "bidder"
    t.integer  "seller"
    t.datetime "created_at"
    t.datetime "updated_at"
    t.integer  "transaction_id"
  end

  create_table "users", force: true do |t|
    t.string   "username"
    t.string   "password"
    t.datetime "created_at"
    t.datetime "updated_at"
    t.string   "first_name"
    t.string   "last_name"
    t.text     "user_description"
    t.integer  "user_id"
  end

  create_table "watchlist_items", force: true do |t|
    t.integer  "watchlist_id"
    t.string   "item_name"
    t.text     "item_description"
    t.float    "item_price"
    t.datetime "created_at"
    t.datetime "updated_at"
    t.integer  "watchlist_item_id"
  end

  create_table "watchlists", force: true do |t|
    t.string   "watchlist_name"
    t.datetime "created_at"
    t.datetime "updated_at"
    t.integer  "watchlist_id"
  end

end
