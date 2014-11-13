require 'activemessaging/processor'
require 'json'

class AuctionsController < ApplicationController
  before_action :set_auction, only: [:show, :edit, :update, :destroy]
  
  include ActiveMessaging::MessageSender
  publishes_to :auction

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

    id = SecureRandom.uuid.to_s

    auction_info = {:id => id, :type => "create", 
      :auction_start_time => params[:auction_start_time].to_s, 
      :auction_length => params[:auction_length].to_s,
      :item_name => params[:item_name].to_s,
      :item_desc => params[:item_desc].to_s
    }

    publish :auction, JSON.generate(auction_info)

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
  end

  def check_for_message(message_id)
    message = Message.find_by(:message_id => message_id.to_s)

    if message.nil?
      return nil
    else
      return message
    end
  end

  # PATCH/PUT /auctions/1
  # PATCH/PUT /auctions/1.json
  def update
    respond_to do |format|
      if @auction.update(auction_params)
        format.html { redirect_to @auction, notice: 'Auction was successfully updated.' }
        format.json { render :show, status: :ok, location: @auction }
      else
        format.html { render :edit }
        format.json { render json: @auction.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /auctions/1
  # DELETE /auctions/1.json
  def destroy
    @auction.destroy
    respond_to do |format|
      format.html { redirect_to auctions_url, notice: 'Auction was successfully destroyed.' }
      format.json { head :no_content }
    end
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
