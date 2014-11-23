class FlagProcessor < ApplicationProcessor

  subscribes_to :flag_confirm

  def on_message(message)
    logger.debug "FlagProcessor received: " + message
    create_new_message(message)
  end
end