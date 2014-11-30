class TransactionsController < ApplicationController
  before_action :confirm_user, only: [:index, :show, :new, :edit, :update, :destroy]

  # GET /transactions
  # GET /transactions.json
  # def index
  #   @transactions = Transaction.all
  # end

  # GET /transactions/1
  # GET /transactions/1.json
  def show
    transaction_id = params[:id]

    id = SecureRandom.uuid.to_s

    transaction_info = {:id => id, :type => "show", 
      :transaction_id => transaction_id
    }

    publish :transaction, JSON.generate(transaction_info)   

    @transaction, @auction = get_transaction(id)  
       
  end

  def index
    id = SecureRandom.uuid.to_s

    transaction_info = {:id => id, :type => "index", 
      :buyer_id => session[:user_id]
    }

    publish :transaction, JSON.generate(transaction_info)   

    @transactions = get_transactions(id) 

  end

  # GET /transactions/new
  def new
    @auction_id = params[:item]
  end

  # GET /transactions/1/edit
  # def edit
  # end

  # POST /transactions
  # POST /transactions.json
  def create
    transaction = Transaction.new(transaction_params)

    id = SecureRandom.uuid.to_s

    transaction_info = {:id => id, :type => "create", 
      :buyer_id => session[:user_id],     
      :auction_id => transaction.auction_id, 
      :address => transaction.address,
      :city => transaction.city, 
      :state => transaction.state,
      :zip_code => transaction.zip_code,
      :payment_type => transaction.payment_type,
      :payment_details => transaction.payment_details
    }

    publish :transaction, JSON.generate(transaction_info)

    status, @error = get_success(id)

    if status
      @status = "Transaction successful!"
    else
      @status = "Transaction not successful"
    end

    render 'confirm'
    # respond_to do |format|
    #   if @transaction.save
    #     format.html { redirect_to @transaction, notice: 'Transaction was successfully created.' }
    #     format.json { render :show, status: :created, location: @transaction }
    #   else
    #     format.html { render :new }
    #     format.json { render json: @transaction.errors, status: :unprocessable_entity }
    #   end
    # end
  end

  # PATCH/PUT /transactions/1
  # PATCH/PUT /transactions/1.json
  # def update
  #   respond_to do |format|
  #     if @transaction.update(transaction_params)
  #       format.html { redirect_to @transaction, notice: 'Transaction was successfully updated.' }
  #       format.json { render :show, status: :ok, location: @transaction }
  #     else
  #       format.html { render :edit }
  #       format.json { render json: @transaction.errors, status: :unprocessable_entity }
  #     end
  #   end
  # end

  # DELETE /transactions/1
  # DELETE /transactions/1.json
  # def destroy
  #   @transaction.destroy
  #   respond_to do |format|
  #     format.html { redirect_to transactions_url, notice: 'Transaction was successfully destroyed.' }
  #     format.json { head :no_content }
  #   end
  # end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_transaction
      @transaction = Transaction.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def transaction_params
      params.require(:transaction).permit(:trans_date, :bidder, :seller)
    end
end
