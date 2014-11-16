require 'activemessaging/processor'
require 'json'

class AuctionsController < ApplicationController
  before_action :set_auction, only: [:show, :edit, :update, :destroy]
  
  include ActiveMessaging::MessageSender
  publishes_to :auction, :category

  # GET /auctions
  # GET /auctions.json
  def index
    @auctions = Auction.all
  end

  # GET /auctions/1
  # GET /auctions/1.json
  def show

    
  end

  # GET /auctions/new
  def new
    @auction = Auction.new
  end

  # GET /auctions/1/edit
  def edit
  end

  # POST /auctions
  # POST /auctions.json
  def create
    auction = Auction.new(auction_params)

    id = SecureRandom.uuid.to_s

    auction_info = {:id => id, :type => "create", 
      :user_id => session[:user_id],     
      :auction_start_time => auction.auction_start_time, 
      :auction_length => auction.auction_length,
      :item_name => auction.item_name, :item_desc => auction.item_desc,
      :quantity => auction.quantity, :buy_it_now => auction.buy_it_now,
      :start_id => auction.start_bid, :shipping_cost => auction.shipping_cost
    }

    publish :auction, JSON.generate(auction_info)

    @message = Message.new.query_message(id)

    render 'confirm'
  end

  # PATCH/PUT /auctions/1
  # PATCH/PUT /auctions/1.json
  def update
    auction = Auction.new(auction_params)

    id = SecureRandom.uuid.to_s

    auction_info = {:id => id, :type => "update", 
      :auction_id => params[:id],     
      :auction_start_time => auction.auction_start_time, 
      :auction_length => auction.auction_length,
      :item_name => auction.item_name, :item_desc => auction.item_desc,
      :quantity => auction.quantity, :buy_it_now => auction.buy_it_now,
      :start_id => auction.start_bid, :shipping_cost => auction.shipping_cost
    }

    publish :auction, JSON.generate(auction_info)

    @message_id = Message.new.get_status(id)

    render 'confirm'    

  end

  # DELETE /auctions/1
  # DELETE /auctions/1.json
  def destroy
    id = SecureRandom.uuid.to_s

    auction_info = {:id => id, :type => "delete", 
      :auction_id => params[:id],     
    }

    publish :auction, JSON.generate(auction_info)

    @message_id = Message.new.query_message(id)

    render 'confirm'
  end

  def search

    id = SecureRandom.uuid.to_s

    auction_info = {:id => id, :type => "index" }

    publish :category, JSON.generate(auction_info)

    @categories = Message.new.get_categories(id) 

  end

  def keyword_search

    keyword = params[:keyword]

    id = SecureRandom.uuid.to_s

    auction_info = {:id => id, :type => "search", 
      :search_type => "keyword"     
    }

    publish :auction, JSON.generate(auction_info)

    @auctions = Message.new.get_auctions(id)

    render 'confirm'    

  end

  def category_search

    category = params[:category]

    id = SecureRandom.uuid.to_s

    auction_info = {:id => id, :type => "search", 
      :search_type => "category"     
    }

    publish :auction, JSON.generate(auction_info)

    @auctions = Message.new.get_auctions(id)

    render 'results'   
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_auction
      @auction = Auction.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def auction_params
      params.require(:auction).permit(:seller_id)
    end
end
