json.array!(@flags) do |flag|
  json.extract! flag, :id, :flag_type, :flagging_user, :auction_id
  json.url flag_url(flag, format: :json)
end
