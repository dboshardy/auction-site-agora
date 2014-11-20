require 'json'

class ApplicationProcessor < ActiveMessaging::Processor
  
  # Default on_error implementation - logs standard errors but keeps processing. Other exceptions are raised.
  # Have on_error throw ActiveMessaging::AbortMessageException when you want a message to be aborted/rolled back,
  # meaning that it can and should be retried (idempotency matters here).
  # Retry logic varies by broker - see individual adapter code and docs for how it will be treated
  def on_error(err)
    if (err.kind_of?(StandardError))
      logger.error "ApplicationProcessor::on_error: #{err.class.name} rescued:\n" + \
      err.message + "\n" + \
      "\t" + err.backtrace.join("\n\t")
    else
      logger.error "ApplicationProcessor::on_error: #{err.class.name} raised: " + err.message
      raise err
    end
  end

  def parse_message_id(message)
    json = JSON.parse(message)
    return json["id"]
  end

  def create_new_message(message)
    message_new = Message.new
    message_new.message_id = parse_message_id(message)
    message_new.body = message
    message_new.received_date = DateTime.new
    message_new.save
  end    

end