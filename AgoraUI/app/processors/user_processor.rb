class UserProcessor < ApplicationProcessor

  subscribes_to :user_confirm

  def on_message(message)
    logger.debug "UserProcessor received: " + message
    create_new_message(message)
  end
end