require 'activemessaging/processor'
require 'json'

class AuctionsController < ApplicationController
  before_action :set_auction, only: [:show, :edit, :update, :destroy]
  
  include ActiveMessaging::MessageSender
  publishes_to :auction

  # GET /auctions
  # GET /auctions.json
  def index

    id = SecureRandom.uuid.to_s

    auction_info = {:id => id, :type => "index", 
      :user_id => session[:user_id]    
    }

    publish :auction, JSON.generate(auction_info)

    @auctions = Message.new.get_auctions(id)

    render 'index'      
  end

  # GET /auctions/1
  # GET /auctions/1.json
  def show

    id = SecureRandom.uuid.to_s

    auction_info = {:id => id, :type => "show", 
      :auction_id => params[:id]  }

    publish :auction, JSON.generate(auction_info)

    @auction, @bidder, @seller = Message.new.get_auction(id)

  end

  # GET /auctions/new
  def new
    if session[:user_id].blank?
      redirect_to "/users/new", notice: "You must log in or sign up to create a new auction"
    end
  end

  # GET /auctions/1/edit
  def edit
    if session[:user_id].blank?
      redirect_to "/users/new", notice: "You must log in or sign up to update an auction"
    end    
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

    status, @error = Message.new.get_success(id)

    if status == "true"
      @status = "New auction created!"
    else
      @status = "Auction could not be created"
    end

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

    status, @error = Message.new.get_success(id)

    if status == "true"
      @status = "Auction updated!"
    else
      @status = "Auction could not be updated"
    end

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

    status, @error = Message.new.get_success(id)

    if status == "true"
      @status = "Auction deleted!"
    else
      @status = "Auction could not be deleted"
    end

    render 'confirm'    
  end

  def search

    @categories = Category.new.get_categories

  end

  def keyword_search

    keyword = params[:keyword]

    id = SecureRandom.uuid.to_s

    auction_info = {:id => id, :type => "search", 
      :search_type => "keyword"     
    }

    publish :auction, JSON.generate(auction_info)

    @auctions = Message.new.get_auctions(id)

    render 'results'    

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
