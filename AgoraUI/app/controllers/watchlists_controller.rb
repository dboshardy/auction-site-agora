class WatchlistsController < ApplicationController
  before_action :set_watchlist, only: [:show, :edit, :update, :destroy]
  publishes_to :watchlist
  # GET /watchlists
  # GET /watchlists.json
  def index
    @watchlists = Watchlist.all
  end

  # GET /watchlists/1
  # GET /watchlists/1.json
  def show
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

    watchlist_info = {:id => id, :watchlist_name => params[:watchlist_name].to_s}

    publish :watchlist, JSON.generate(watchlist_info)

        attempts = 0

    @message_id = nil

    message = Message.find_by(:message_id => id)

    while attempts < 10 # change after testing

      if message.nil?
        sleep(1)
        attempts += 1
        message = Message.find_by(:message_id => id)
      else
        @message_id = message.message_id
        break
      end

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
    respond_to do |format|
      if @watchlist.update(watchlist_params)
        format.html { redirect_to @watchlist, notice: 'Watchlist was successfully updated.' }
        format.json { render :show, status: :ok, location: @watchlist }
      else
        format.html { render :edit }
        format.json { render json: @watchlist.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /watchlists/1
  # DELETE /watchlists/1.json
  def destroy
    @watchlist.destroy
    respond_to do |format|
      format.html { redirect_to watchlists_url, notice: 'Watchlist was successfully destroyed.' }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_watchlist
      @watchlist = Watchlist.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def watchlist_params
      params.require(:watchlist).permit(:watchlist_name)
    end
end
