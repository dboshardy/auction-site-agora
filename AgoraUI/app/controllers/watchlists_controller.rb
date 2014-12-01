class WatchlistsController < ApplicationController
  before_action :confirm_user, only: [:new, :create, :show, :edit, :update, :destroy]
  publishes_to :watchlist
  # GET /watchlists
  # GET /watchlists.json
  def index
    id = SecureRandom.uuid.to_s

    watchlist_info = {:id => id, :type => 'index',
      :user_id => session[:user_id]
    }

    publish :watchlist, JSON.generate(watchlist_info)

    @auctions = get_watchlists(id)
  end

  # GET /watchlists/1
  # GET /watchlists/1.json
  def show
    id = SecureRandom.uuid.to_s

    watchlist_info = {:id => id, :type => 'show',
      :watchlist_id => params[:id]
    }

    publish :watchlist, JSON.generate(watchlist_info)

    @watchlist, @items = get_watchlist(id)

  end

  # GET /watchlists/new
  def new
    @watchlist = Watchlist.new

  end

  # GET /watchlists/1/edit
  def edit
 
  end

  # POST /watchlists
  # POST /watchlists.json
  def create

    id = SecureRandom.uuid.to_s

    watchlist_info = {:id => id, :type => "add",
      :auction_id => params[:id],
      :user_id => session[:user_id]
    }

    publish :watchlist, JSON.generate(watchlist_info)

    status, @error = get_success(id)

    if status 
      @status = "Auction Added to Watchlist"
    else
      @status = "Auction Could Not Be Added to Watchlist"
    end    

    render 'confirm'
    # @watchlist = Watchlist.new(watchlist_params)

    # respond_to do |format|
    #   if @watchlist.save
    #     format.html { redirect_to @watchlist, notice: 'Watchlist was successfully created.' }
    #     format.json { render :show, status: :created, location: @watchlist }
    #   else
    #     format.html { render :new }
    #     format.json { render json: @watchlist.errors, status: :unprocessable_entity }
    #   end
    # end

  end

  # PATCH/PUT /watchlists/1
  # PATCH/PUT /watchlists/1.json
  def update
    id = SecureRandom.uuid.to_s

    watchlist_info = {:id => id, type => "update",
      :watchlist_id => params[:id],
      :watchlist_name => params[:watchlist_name]
    }

    publish :watchlist, JSON.generate(watchlist_info)

    status, @error = get_success(id)

    if status 
      @status = "Watchlist updated!"
    else
      @status = "Watchlist could not be updated"
    end    

    render 'confirm'    
    # respond_to do |format|
    #   if @watchlist.update(watchlist_params)
    #     format.html { redirect_to @watchlist, notice: 'Watchlist was successfully updated.' }
    #     format.json { render :show, status: :ok, location: @watchlist }
    #   else
    #     format.html { render :edit }
    #     format.json { render json: @watchlist.errors, status: :unprocessable_entity }
    #   end
    # end
  end

  # DELETE /watchlists/1
  # DELETE /watchlists/1.json
  def destroy
    id = SecureRandom.uuid.to_s

    watchlist_info = {:id => id, :type => "delete", 
      :watchlist_id => params[:id]    
    }

    publish :watchlist, JSON.generate(watchlist_info)

    status, @error = get_success(id)

    if status 
      @status = "Watchlist deleted!"
    else
      @status = "Watchlist could not be deleted"
    end

    render 'confirm' 
        # @watchlist.destroy
    # respond_to do |format|
    #   format.html { redirect_to watchlists_url, notice: 'Watchlist was successfully destroyed.' }
    #   format.json { head :no_content }
    # end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_watchlist
      if session[:user_id].blank?
        redirect_to "/users/new", notice: "You must log in or sign up to create a new auction"
      end
    end    

    # Never trust parameters from the scary internet, only allow the white list through.
    def watchlist_params
      params.require(:watchlist).permit(:watchlist_name)
    end
end
