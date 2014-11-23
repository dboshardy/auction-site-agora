require File.dirname(__FILE__) + '/../test_helper'
require 'activemessaging/test_helper'
require File.dirname(__FILE__) + '/../../app/processors/application_processor'

class ShoppingCartProcessorTest < Test::Unit::TestCase
  include ActiveMessaging::TestHelper
  
  def setup
    load File.dirname(__FILE__) + "/../../app/processors/shopping_cart_processor.rb"
    @processor = ShoppingCartProcessor.new
  end
  
  def teardown
    @processor = nil
  end  

  def test_shopping_cart_processor
    @processor.on_message('Your test message here!')
  end
end