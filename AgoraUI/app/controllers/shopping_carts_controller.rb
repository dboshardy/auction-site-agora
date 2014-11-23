class ShoppingCartsController < ApplicationController
  before_action :confirm_user, only: [:show, :edit, :update, :destroy]
  publishes_to :shopping_cart
  # GET /shopping_carts
  # GET /shopping_carts.json
  # def index
  #   @shopping_carts = ShoppingCart.all
  # end

  # GET /shopping_carts/1
  # GET /shopping_carts/1.json
  def show

    id = SecureRandom.uuid.to_s
    auction_id = params[:id]

    cart_info = {:id => id, :type => "show", 
      :user_id => session[:user_id]  }

    publish :shopping_cart, JSON.generate(cart_info)

    @cart, @items = get_cart(id)

  end

  # # GET /shopping_carts/new
  # def new
  #   @shopping_cart = ShoppingCart.new
  # end

  # # GET /shopping_carts/1/edit
  # def edit
  # end

  # # POST /shopping_carts
  # # POST /shopping_carts.json
  # def create
  #   @shopping_cart = ShoppingCart.new(shopping_cart_params)

  #   respond_to do |format|
  #     if @shopping_cart.save
  #       format.html { redirect_to @shopping_cart, notice: 'Shopping cart was successfully created.' }
  #       format.json { render :show, status: :created, location: @shopping_cart }
  #     else
  #       format.html { render :new }
  #       format.json { render json: @shopping_cart.errors, status: :unprocessable_entity }
  #     end
  #   end
  # end

  # # PATCH/PUT /shopping_carts/1
  # # PATCH/PUT /shopping_carts/1.json
  # def update
  #   respond_to do |format|
  #     if @shopping_cart.update(shopping_cart_params)
  #       format.html { redirect_to @shopping_cart, notice: 'Shopping cart was successfully updated.' }
  #       format.json { render :show, status: :ok, location: @shopping_cart }
  #     else
  #       format.html { render :edit }
  #       format.json { render json: @shopping_cart.errors, status: :unprocessable_entity }
  #     end
  #   end
  # end

  # # DELETE /shopping_carts/1
  # # DELETE /shopping_carts/1.json
  def destroy
    id = SecureRandom.uuid.to_s
    item_id = params[:id]

    item_info = {:id => id, :type => "delete", 
      :item_id => item_id     
    }

    publish :shopping_cart, JSON.generate(item_info)

    status, @error = get_success(id)

    if status == "true"
      @status = "Cart item deleted!"
    else
      @status = "Cart item could not be deleted"
    end

    render 'confirm' 
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    # def set_shopping_cart
    #   @shopping_cart = ShoppingCart.find(params[:id])
    # end

    # Never trust parameters from the scary internet, only allow the white list through.
    def shopping_cart_params
      params.require(:shopping_cart).permit(:user_id)
    end
end
