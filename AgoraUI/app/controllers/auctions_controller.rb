class AuctionsController < ApplicationController
  before_action :confirm_user, only: [:index, :new, :edit, :create, :update, :destroy]
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
 
  end

  def welcome

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

    id = SecureRandom.uuid.to_s

    auction_info = {:id => id, :type => "categories" }

    publish :auction, JSON.generate(auction_info)

    @categories = get_categories(id)

  end

  # GET /auctions/1/edit
  def edit
    @auction = Auction.new

    id = SecureRandom.uuid.to_s

    auction_info = {:id => id, :type => "categories" }

    publish :auction, JSON.generate(auction_info)

    @categories = get_categories(id)

  end

  # POST /auctions
  # POST /auctions.json
  def create
    auction = Auction.new(auction_params)

    id = SecureRandom.uuid.to_s

    if params[:buy_it_now] == "false"
      buy_it_now_price = "0.00"
    else
      buy_it_now_price = params[:auction][:buy_now_price]
    end

    auction_start_time = {
      :year => params[:auction]["auction_start_time(1i)"],
      :month => params[:auction]["auction_start_time(2i)"], 
      :day => params[:auction]["auction_start_time(3i)"],
      :hour => params[:auction]["auction_start_time(4i)"],
      :minutes => params[:auction]["auction_start_time(5i)"]
    }

    auction_info = {:id => id, :type => "create", 
      :user_id => session[:user_id],     
      :item_name => params[:auction][:item_name], 
      :item_desc => params[:auction][:item_desc],
      :quantity => params[:auction][:quantity], 
      :buy_it_now => params[:buy_it_now],
      :buy_now_price => buy_it_now_price,
      :start_bid => params[:auction][:start_bid], 
      :shipping_cost => params[:auction][:shipping_cost],
      :auction_length => params[:auction][:auction_length],
      :auction_start_time => auction_start_time, 
      :category_id => params[:category]    
    }


    publish :auction, JSON.generate(auction_info)

    @auction, @status, @error = get_auction_success(id)

    if !@auction.nil?
      @status = 'auction created!'
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
    auction_id = params[:id]

    auction_start_time = {
      :year => params["auction_start_time"]["auction_start_time(1i)"],
      :month => params["auction_start_time"]["auction_start_time(2i)"], 
      :day => params["auction_start_time"]["auction_start_time(3i)"],
      :hour => params["auction_start_time"]["auction_start_time(4i)"],
      :minutes => params["auction_start_time"]["auction_start_time(5i)"]
    }

    auction_info = {:id => id, :type => "update", 
      :auction_id => auction_id,     
      :item_name => params[:item_name], 
      :item_desc => params[:item_desc],
      :quantity => params[:quantity], 
      :buy_it_now => params[:buy_it_now],
      :buy_now_price => params[:buy_now_price],
      :start_bid => params[:start_bid], 
      :shipping_cost => params[:shipping_cost],
      :auction_length => params[:auction_length],
      :auction_start_time => auction_start_time,
      :category => params[:category]      
    }

    publish :auction, JSON.generate(auction_info)

    status, @error = get_success(id)

    if status 
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

    if status 
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
      params.permit(:seller_id)
    end
end
