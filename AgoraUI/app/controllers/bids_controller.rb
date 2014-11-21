class BidsController < ApplicationController
  before_action :set_bid, only: [:new, :create, :create_buy_now, :show, :edit, :update, :destroy]
  publishes_to :bid


  # GET /bids
  # GET /bids.json
  def index
    @bids = Bid.all
  end

  # GET /bids/1
  # GET /bids/1.json
  def show_auction_history
    id = SecureRandom.uuid.to_s

    bid_info = {:id => id, :type => "show",   
      :auction_id => params[:id]
    }

    publish :bid, JSON.generate(bid_info)

    @bids = get_bids(id)

    render 'auction_bids'    

  end

  def show_user_history
    id = SecureRandom.uuid.to_s

    bid_info = {:id => id, :type => "show",   
      :user_id => params[:id]
    }

    publish :bid, JSON.generate(bid_info)

    @bids = get_bids(id)

    render 'user_bids'    
  end

  # GET /bids/new
  def new

  end

  # # GET /bids/1/edit
  # def edit
  # end

  # POST /bids
  # POST /bids.json
  def create

    @bid = Bid.new(bid_params)

    id = SecureRandom.uuid.to_s

    bid_info = {:id => id, :type => "create", 
      :user_id => session[:user_id],     
      :auction_id => params[:id],
      :bid_amount => params[:bid_amount],
      :buy_now => "false"
    }

    publish :bid, JSON.generate(bid_info)

    status, @error = get_success(id)

    if status == "true"
      @status = "Bid Placed!"
    else
      @status = "Bid could not be placed"
    end

    render 'confirm'    

    # respond_to do |format|
    #   if @bid.save
    #     format.html { redirect_to @bid, notice: 'Bid was successfully created.' }
    #     format.json { render :show, status: :created, location: @bid }
    #   else
    #     format.html { render :new }
    #     format.json { render json: @bid.errors, status: :unprocessable_entity }
    #   end
    # end
  end

  def create_buy_now

    id = SecureRandom.uuid.to_s

    bid_info = {:id => id, :type => "create", 
      :user_id => session[:user_id],     
      :auction_id => params[:id],
      :bid_amount => params[:buy_now_price],
      :buy_now => "true"
    }

    publish :bid, JSON.generate(bid_info)

    status, @error = get_success(id)

    if status == "true"
      @status = "Bid Placed!"
    else
      @status = "Bid could not be placed"
    end

    render 'confirm'       

  end

  # PATCH/PUT /bids/1
  # PATCH/PUT /bids/1.json
  # def update
  #   respond_to do |format|
  #     if @bid.update(bid_params)
  #       format.html { redirect_to @bid, notice: 'Bid was successfully updated.' }
  #       format.json { render :show, status: :ok, location: @bid }
  #     else
  #       format.html { render :edit }
  #       format.json { render json: @bid.errors, status: :unprocessable_entity }
  #     end
  #   end
  # end

  # # DELETE /bids/1
  # # DELETE /bids/1.json
  # def destroy
  #   @bid.destroy
  #   respond_to do |format|
  #     format.html { redirect_to bids_url, notice: 'Bid was successfully destroyed.' }
  #     format.json { head :no_content }
  #   end
  # end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_bid
      redirect_to "/users/new", notice: "You must log in or sign up to create a new auction"
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def bid_params
      params.require(:bid).permit(:bidder_id, :currency_id, :amount, :auction_id)
    end
end
