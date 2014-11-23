json.array!(@auctions) do |auction|
  json.extract! auction, :id, :seller_id
  json.url auction_url(auction, format: :json)
end
