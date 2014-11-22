class WatchlistItemController < ApplicationController
  before_action :confirm_user, only: [:new, :create, :show, :edit, :update, :destroy]
  publishes_to :watchlist_item
  # GET /watchlists
  # GET /watchlists.json
  # def index
  # end

  # # GET /watchlists/1
  # # GET /watchlists/1.json
  # def show

  # end

  # GET /watchlist_items/id/new
  def new
  	@watchlist_id = params[:id]
  end

  # GET /watchlists/1/edit
  def edit
  	@watchlist_item_id = params[:id] 	
  end

  # POST /watchlists
  # POST /watchlists.json
  def create

  	watchlist_item = WatchlistItem.new(watchlist_item_params)

    id = SecureRandom.uuid.to_s

    watchlist_item_info = {:id => id, type => "create",
      :watchlist_id => watchlist_item.watchlist_id,
      :item_name => watchlist_item.item_name,
      :item_description => watchlist_item.item_description,
      :item_price => watchlist_item.item_price

    }

    publish :watchlist_item, JSON.generate(watchlist_item_info)

    status, @error = get_success(id)

    if status == "true"
      @status = "New watchlist item created!"
    else
      @status = "Watchlist item could not be created"
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
  	watchlist_item = WatchlistItem.new(watchlist_item_params)

    id = SecureRandom.uuid.to_s

    watchlist_item_info = {:id => id, type => "update",
      :watchlist_item_id => watchlist_item.watchlist_item_id,
      :item_name => watchlist_item.item_name,
      :item_description => watchlist_item.item_description,
      :item_price => watchlist_item.item_price

    }

    publish :watchlist_item, JSON.generate(watchlist_item_info)

    status, @error = get_success(id)

    if status == "true"
      @status = "New watchlist item updated!"
    else
      @status = "Watchlist item could not be updated"
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

    watchlist_item_info = {:id => id, :type => "delete", 
      :watchlist_item_id => params[:id]    
    }

    publish :watchlist_item, JSON.generate(watchlist_item_info)

    status, @error = get_success(id)

    if status == "true"
      @status = "Watchlist item deleted!"
    else
      @status = "Watchlist item could not be deleted"
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
    def set_watchlist_item
      if session[:user_id].blank?
        redirect_to "/users/new", notice: "You must log in or sign up to create a new auction"
      end
    end    

    # Never trust parameters from the scary internet, only allow the white list through.
    def watchlist_item_params
      params.require(:watchlist).permit(:watchlist_name)
    end
end
