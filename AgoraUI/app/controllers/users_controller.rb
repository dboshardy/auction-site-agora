require 'digest'

class UsersController < ApplicationController
  before_action :confirm_user, only: [:show, :edit, :update, :destroy]
  before_action :confirm_admin, only: [:destroy, :block]
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
    id = SecureRandom.uuid.to_s
    user_id = params[:id]

    user_info = { :id => id, :type => "show",
      :user_id => user_id
    }

    publish :user, JSON.generate(user_info)   

    @user, error = get_user(id)   

    if @user.nil?
      redirect_to "/users", notice: "Error finding this user" + error
    end

    # @user = User.find(params[:id])
    # if session[:user_id] != @user.id
    #   redirect_to root_url, notice: "No way!"
    # end
  end

  # GET /users/new
  def new
    @user = User.new
  end

  # GET /users/1/edit
  def edit
    if session[:user_id] != params[:id].to_i
      redirect_to "/users", notice: "You cannot edit this user's information"
    end
  end

  def login_page

  end

  def login
    user = User.new(user_params)

    id = SecureRandom.uuid.to_s
    user_info = { :id => id, :type => "login",
      :user_name => user.username,
      :password_hash => Digest::SHA256.hexdigest(params[:password])
    }

    publish :user, JSON.generate(user_info)
    
    status, @error, user_id, is_admin, is_suspended = get_login_success(id)

    if status
      @status = "Login Successful!"
      session[:user_id] = user_id
      session[:is_admin] = is_admin
      session[:is_suspended] = is_suspended
    else
      @status = "Login error:"
    end

    render 'confirm'

  end

  # POST /users
  # POST /users.json
  def create
    user = User.new(user_params)

    id = SecureRandom.uuid.to_s

    if params[:password] != params[:password_confirmation]
      redirect_to "/users", notice: "Passwords do not match"
      return
    end

    user_info = { :id => id, :type => "create",
      :user_id => session[:user_id], 
      :username => user.username,
      :email => user.email,
      :first_name => user.first_name,
      :last_name => user.last_name,
      :user_description => params[:user_description],
      :password_hash => Digest::SHA256.hexdigest(params[:password])
    }

    publish :user, JSON.generate(user_info)

    status, @error, user_id = get_new_user_success(id)

    if status
      @status = "New user created!"
      session[:user_id] = user_id
    else
      @status = "User could not be created"
    end

    render 'confirm'

  end

  # PATCH/PUT /users/1
  # PATCH/PUT /users/1.json
  def update

    user = User.new(user_params)

    id = SecureRandom.uuid.to_s
    user_id = params[:id]

    user_info = { :id => id, :type => "update",
      :user_id => user_id,
      :username => user.username,
      :email => user.email,
      :first_name => user.first_name,
      :last_name => user.last_name,
      :user_description => params["user_description"],
      :password_hash => Digest::SHA256.hexdigest(params[:password])
    }

    publish :user, JSON.generate(user_info)

    status, @error = get_success(id)

    if status
      @status = "New user updated!"
    else
      @status = "User could not be updated"
    end

    render 'confirm'

    # respond_to do |format|
    #   if @user.update(user_params)
    #     format.html { redirect_to @user, notice: 'User was successfully updated.' }
    #     format.json { render :show, status: :ok, location: @user }
    #   else
    #     format.html { render :edit }
    #     format.json { render json: @user.errors, status: :unprocessable_entity }
    #   end
    # end
  end

  # DELETE /users/1
  # DELETE /users/1.json
  def destroy
 
    id = SecureRandom.uuid.to_s
    user_id = params[:id]

    user_info = {:id => id, :type => "delete", 
      :user_id => user_id
    }

    publish :user, JSON.generate(user_info)

    status, @error = get_success(id)

    if status
      @status = "User deleted!"
      if params[:id].to_i == session[:user_id]
        session[:user_id] = nil
      end
    else
      @status = "User could not be deleted"
    end

    render 'confirm'       
    # @user.destroy
    # respond_to do |format|
    #   format.html { redirect_to users_url, notice: 'User was successfully destroyed.' }
    #   format.json { head :no_content }
    # end
  end

  # GET "/users/1/suspend"
  def suspend
    id = SecureRandom.uuid.to_s
    user_id = params[:id]

    user_info = {:id => id, :type => "suspend", 
      :user_id => user_id
    }

    publish :user, JSON.generate(user_info)

    status, @error = get_success(id)

    if status
      @status = "User suspended!"
      if session[:user_id] == user_id.to_i
        session[:is_suspended] = true
      end
    else
      @status = "User could not be suspended"
    end

    render 'confirm'          

  end

  def block
    if session[:is_admin] == "true"
      id = SecureRandom.uuid.to_s
      user_id = params[:id]

      user_info = {:id => id, :type => "block", 
        :user_id => user_id
      }

      publish :user, JSON.generate(user_info)

      status, @error = get_success(id)

      if status
        @status = "User blocked!"
      else
        @status = "User could not be blocked"
      end

      render 'confirm'          

    else
      redirect_to "/users", notice: "You cannot block this user"
    end    

  end

  def destroy_session
    reset_session
    redirect_to root_url, notice: "Logged Out"
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_user
      @user = User.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def user_params
      params.permit(:user_id, :username, :email, :location, :password, :first_name, :last_name)
    end
end
