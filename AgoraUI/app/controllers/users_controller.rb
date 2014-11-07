require 'activemessaging/processor'
require 'json'

class UsersController < ApplicationController
  before_action :set_user, only: [:show, :edit, :update, :destroy]

  include ActiveMessaging::MessageSender
  publishes_to :user
  # GET /users
  # GET /users.json
  def index
    if session[:user_id].blank?
     render "new"
    else 
      redirect_to "/users/#{session[:user_id]}"
    end
  end

  # GET /users/1
  # GET /users/1.json
  def show
    @user = User.find(params[:id])
    if session[:user_id] != @user.id
      redirect_to root_url, notice: "No way!"
    end
  end

  # GET /users/new
  def new
    @user = User.new
  end

  # GET /users/1/edit
  def edit
  end

  # POST /users
  # POST /users.json
  def create
    user_info = {:first_name => params[:first_name].to_s,
      :last_name => params[:last_name].to_s,
      :email => params[:email].to_s,
      :username => params[:username].to_s,
      :password => params[:password].to_s
    }

    publish :user, JSON.generate(user_info)

    redirect_to users_path

    # @user = User.new(user_params)

    # respond_to do |format|
    #   if @user.save
    #     format.html { redirect_to @user, notice: 'User was successfully created.' }
    #     format.json { render :show, status: :created, location: @user }
    #   else
    #     format.html { render :new }
    #     format.json { render json: @user.errors, status: :unprocessable_entity }
    #   end
    # end
  end

  # PATCH/PUT /users/1
  # PATCH/PUT /users/1.json
  def update
    respond_to do |format|
      if @user.update(user_params)
        format.html { redirect_to @user, notice: 'User was successfully updated.' }
        format.json { render :show, status: :ok, location: @user }
      else
        format.html { render :edit }
        format.json { render json: @user.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /users/1
  # DELETE /users/1.json
  def destroy
    @user.destroy
    respond_to do |format|
      format.html { redirect_to users_url, notice: 'User was successfully destroyed.' }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_user
      @user = User.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def user_params
      params.require(:user).permit(:username, :email, :location, :password)
    end
end
