class UsersController < ApplicationController
  before_action :set_user, only: [:show, :edit, :update, :destroy]
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
    if session[:user_id] != params[:id]
      redirect_to "/users", notice: "You cannot edit this user's information"
    end
  end

  # POST /users
  # POST /users.json
  def create
    user = User.new(user_params)

    id = SecureRandom.uuid.to_s

    user_info = { :id => id, :type => "create",
      :username => user.user_name,
      :first_name => user.first_name,
      :last_name => user.last_name,
      :user_description => user.user_description,
      :password_hash => user.password
    }

    if user.errors.any?
      redirect_to "/users", user.errors.full_messages
    end

    publish :user, JSON.generate(user_info)

    status, @error = get_success(id)

    if status == "true"
      @status = "New user created!"
    else
      @status = "User could not be created"
    end

    render 'confirm'

  end

  # PATCH/PUT /users/1
  # PATCH/PUT /users/1.json
  def update
    if session[:user_id] != params[:id]
        redirect_to "/users", notice: "You cannot edit this user's information"
    end

    user = User.new(user_params)

    id = SecureRandom.uuid.to_s
    user_id = params[:id]

    user_info = { :id => id, :type => "create",
      :user_id => user_id,
      :first_name => user.first_name,
      :last_name => user.last_name,
      :user_description => user.user_description,
      :password_hash => user.password
    }

    if user.errors.any?
      redirect_to "/users", user.errors.full_messages
    end

    publish :user, JSON.generate(user_info)

    status, @error = get_success(id)

    if status == "true"
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
    if (session[:user_id] != params[:id]) && (session[:is_admin] != "true")
        redirect_to "/users", notice: "You cannot delete this user"
    end    

    id = SecureRandom.uuid.to_s
    user_id = params[:id]

    user_info = {:id => id, :type => "delete", 
      :user_id => user_id
    }

    publish :auction, JSON.generate(auction_info)

    status, @error = get_success(id)

    if status == "true"
      @status = "User deleted!"
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
    if session[:is_admin] == "true"
      id = SecureRandom.uuid.to_s
      user_id = params[:id]

      user_info = {:id => id, :type => "suspend", 
        :user_id => user_id
      }

      publish :auction, JSON.generate(auction_info)

      status, @error = get_success(id)

      if status == "true"
        @status = "User suspended!"
      else
        @status = "User could not be suspended"
      end

      render 'confirm'          

    else
      redirect_to "/users", notice: "You cannot suspend this user"
    end
  end

  def block
    if session[:is_admin] == "true"
      id = SecureRandom.uuid.to_s
      user_id = params[:id]

      user_info = {:id => id, :type => "suspend", 
        :user_id => user_id
      }

      publish :auction, JSON.generate(auction_info)

      status, @error = get_success(id)

      if status == "true"
        @status = "User suspended!"
      else
        @status = "User could not be suspended"
      end

      render 'confirm'          

    else
      redirect_to "/users", notice: "You cannot block this user"
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
