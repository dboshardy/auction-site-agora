json.array!(@bids) do |bid|
  json.extract! bid, :id, :bidder_id, :currency_id, :amount, :auction_id
  json.url bid_url(bid, format: :json)
end
