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

ActiveRecord::Schema.define(version: 20141031184147) do

  # These are extensions that must be enabled in order to support this database
  enable_extension "plpgsql"

  create_table "auctions", force: true do |t|
    t.integer  "seller_id"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "bids", force: true do |t|
    t.integer  "bidder_id"
    t.integer  "currency_id"
    t.integer  "amount"
    t.integer  "auction_id"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "categories", force: true do |t|
    t.string   "category"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "flags", force: true do |t|
    t.string   "flag_type"
    t.integer  "flagging_user"
    t.integer  "auction_id"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "items", force: true do |t|
    t.string   "item_name"
    t.text     "item_desc"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "shopping_carts", force: true do |t|
    t.integer  "user_id"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "transactions", force: true do |t|
    t.date     "trans_date"
    t.integer  "bidder"
    t.integer  "seller"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "users", force: true do |t|
    t.string   "username"
    t.string   "email"
    t.string   "location"
    t.string   "password"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "watchlists", force: true do |t|
    t.string   "watchlist_name"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

end