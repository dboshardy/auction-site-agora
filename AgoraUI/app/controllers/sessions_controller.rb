class SessionsController < ApplicationController
  before_action :set_session, only: [:show, :edit, :update, :destroy]
  publishes_to :user

  def index
    render "new"
  end

  def destroy
    reset_session
    redirect_to root_url, notice: "Goodbye."
  end

  def create
    username = params[:username]
    password = params[:password]

    id = SecureRandom.uuid.to_s

    user_info = { :id => id, :type => "login",
      :username => username,
      :password => password
    }

    if user.errors.any?
      redirect_to "/users", user.errors.full_messages
    end

    publish :user, JSON.generate(user_info)

    status, @error = get_success(id)

    if status == "true"
      @status = "Login Successful!"
    else
      @status = "Login error:"
    end

    render 'confirm'
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_session
      @session = Session.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def session_params
      params[:session]
    end
end
