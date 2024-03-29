require File.dirname(__FILE__) + '/../test_helper'
require 'activemessaging/test_helper'
require File.dirname(__FILE__) + '/../../app/processors/application_processor'

class WatchlistProcessorTest < Test::Unit::TestCase
  include ActiveMessaging::TestHelper
  
  def setup
    load File.dirname(__FILE__) + "/../../app/processors/watchlist_processor.rb"
    @processor = WatchlistProcessor.new
  end
  
  def teardown
    @processor = nil
  end  

  def test_watchlist_processor
    @processor.on_message('Your test message here!')
  end
end