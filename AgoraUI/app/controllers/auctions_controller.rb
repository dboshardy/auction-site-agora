class AuctionsController < ApplicationController
  before_action :confirm_user, only: [:new, :edit, :create, :update, :destroy]
  publishes_to :auction

  # GET /auctions
  # GET /auctions.json
  def index

    id = SecureRandom.uuid.to_s

    auction_info = {:id => id, :type => "index", 
      :user_id => session[:user_id]    
    }

    publish :auction, JSON.generate(auction_info)

    @auctions = get_auctions(id)

    if @auctions.nil?
      render 'welcome'
    else
      render 'index'  
    end    
  end

  # GET /auctions/1
  # GET /auctions/1.json
  def show

    id = SecureRandom.uuid.to_s
    auction_id = params[:id]

    auction_info = {:id => id, :type => "show", 
      :auction_id => auction_id  }

    publish :auction, JSON.generate(auction_info)

    @auction, @bid, @bidder, @seller = get_auction(id)

    # if @auction.item_name.nil?
    #   @error = "Auction not found"
    #   render 'error'
    # end

  end

  # GET /auctions/new
  def new
    @auction = Auction.new

  end

  # GET /auctions/1/edit
  def edit
    @auction = Auction.new

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

    @auction, @status, @error = get_auction_success(id)

    if !@auction.nil?
      redirect_to "/categories/new_categories_for_auction/#{@auction}"
    else
      @status = "Auction could not be created"
      render 'confirm'
    end

  end

  # PATCH/PUT /auctions/1
  # PATCH/PUT /auctions/1.json
  def update
    auction = Auction.new(auction_params)

    id = SecureRandom.uuid.to_s
    auction_id = params[:id]

    auction_info = {:id => id, :type => "update", 
      :auction_id => auction_id,     
      :auction_start_time => auction.auction_start_time, 
      :auction_length => auction.auction_length,
      :item_name => auction.item_name, :item_desc => auction.item_desc,
      :quantity => auction.quantity, :buy_it_now => auction.buy_it_now,
      :start_id => auction.start_bid, :shipping_cost => auction.shipping_cost
    }

    publish :auction, JSON.generate(auction_info)

    status, @error = get_success(id)

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
    auction_id = params[:id]

    auction_info = {:id => id, :type => "delete", 
      :auction_id => auction_id     
    }

    publish :auction, JSON.generate(auction_info)

    status, @error = get_success(id)

    if status == "true"
      @status = "Auction deleted!"
    else
      @status = "Auction could not be deleted"
    end

    render 'confirm'    
  end

  def search

    @categories = get_categories

  end

  def keyword_search

    keyword = params[:keyword]

    id = SecureRandom.uuid.to_s

    auction_info = {:id => id, :type => "search", 
      :search_type => "keyword"     
    }

    publish :auction, JSON.generate(auction_info)

    @auctions = get_auctions(id)

    render 'results'    

  end

  def category_search

    category = params[:category]

    id = SecureRandom.uuid.to_s

    auction_info = {:id => id, :type => "search", 
      :search_type => "category"     
    }

    publish :auction, JSON.generate(auction_info)

    @auctions = get_auctions(id)

    render 'results'   
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    # def set_auction
    #   redirect_to "/users/new", notice: "You must log in or sign up to create a new auction"
    # end

    # Never trust parameters from the scary internet, only allow the white list through.
    def auction_params
      params.require(:auction).permit(:seller_id)
    end
end
