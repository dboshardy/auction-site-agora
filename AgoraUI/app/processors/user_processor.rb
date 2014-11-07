class UserProcessor < ApplicationProcessor

  subscribes_to :user

  def on_message(message)
    logger.debug "UserProcessor received: " + message
  end
end