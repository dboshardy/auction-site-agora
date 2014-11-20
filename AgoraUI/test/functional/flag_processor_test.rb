require File.dirname(__FILE__) + '/../test_helper'
require 'activemessaging/test_helper'
require File.dirname(__FILE__) + '/../../app/processors/application_processor'

class FlagProcessorTest < Test::Unit::TestCase
  include ActiveMessaging::TestHelper
  
  def setup
    load File.dirname(__FILE__) + "/../../app/processors/flag_processor.rb"
    @processor = FlagProcessor.new
  end
  
  def teardown
    @processor = nil
  end  

  def test_flag_processor
    @processor.on_message('Your test message here!')
  end
end