json.array!(@transactions) do |transaction|
  json.extract! transaction, :id, :trans_date, :bidder, :seller
  json.url transaction_url(transaction, format: :json)
end
