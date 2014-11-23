require File.dirname(__FILE__) + '/../test_helper'
require 'activemessaging/test_helper'
require File.dirname(__FILE__) + '/../../app/processors/application_processor'

class UserProcessorTest < Test::Unit::TestCase
  include ActiveMessaging::TestHelper
  
  def setup
    load File.dirname(__FILE__) + "/../../app/processors/user_processor.rb"
    @processor = UserProcessor.new
  end
  
  def teardown
    @processor = nil
  end  

  def test_user_processor
    @processor.on_message('Your test message here!')
  end
end