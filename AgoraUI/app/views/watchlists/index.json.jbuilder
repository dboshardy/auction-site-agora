json.array!(@watchlists) do |watchlist|
  json.extract! watchlist, :id, :watchlist_name
  json.url watchlist_url(watchlist, format: :json)
end
