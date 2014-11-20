require File.dirname(__FILE__) + '/../test_helper'
require 'activemessaging/test_helper'
require File.dirname(__FILE__) + '/../../app/processors/application_processor'

class TransactionProcessorTest < Test::Unit::TestCase
  include ActiveMessaging::TestHelper
  
  def setup
    load File.dirname(__FILE__) + "/../../app/processors/transaction_processor.rb"
    @processor = TransactionProcessor.new
  end
  
  def teardown
    @processor = nil
  end  

  def test_transaction_processor
    @processor.on_message('Your test message here!')
  end
end