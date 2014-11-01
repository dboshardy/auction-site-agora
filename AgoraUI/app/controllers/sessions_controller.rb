class SessionsController < ApplicationController

  def index
  	render "new"
  end

  def destroy
    reset_session
    redirect_to root_url, notice: "Goodbye."
  end

  def create
    username = params[:username]

    user = User.find_by(username: username)
    if user
      if user.authenticate(params[:password])
        session[:user_id] = user.id
        find_follows
        redirect_to root_url, notice: "Welcome Back, #{user.username}"
      else
        redirect_to "/sessions", notice: "Bad Password"
      end
    else
       redirect_to "/sessions", notice: "Unknown Username"
    end
  end

end
