class TransactionProcessor < ApplicationProcessor

  subscribes_to :transaction_confirm

  def on_message(message)
    logger.debug "TransactionProcessor received: " + message
    create_new_message(message)
  end
end