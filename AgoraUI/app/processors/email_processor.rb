class EmailProcessor < ApplicationProcessor

  subscribes_to :email_confirm

  def on_message(message)
    logger.debug "EmailProcessor received: " + message
    create_new_message(message)
  end
end