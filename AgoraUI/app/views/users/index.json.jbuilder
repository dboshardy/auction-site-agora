json.array!(@users) do |user|
  json.extract! user, :id, :username, :email, :location, :password
  json.url user_url(user, format: :json)
end
