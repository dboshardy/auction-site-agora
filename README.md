# Agora

## Setup
###Database
In order to set up this code, you have to first create a postgres db named agora-db with the postgres user with the password 'postgres'.  If you want to change any of those names, you must change the hibernate.cfg.xml file to reflect the name/password changes, otherwise this won't work.  

Once you have created the database, either copy the SQL code from the agora-db-schema.sql file located in Agora/resources, or run that file as a script.  

To run the example code in AuctionEngine, specify that class as containing the main method (preferably in IntelliJ) and run it.  Currently, it is set up to write 2 users to the database, 1 auction created by a user, including an initial bid.  Then the other user adds that auction to their watchlist.  

###Frontend
Install Ruby, Rails (Installation guide can be found at https://gorails.com/setup/ ). 

From the AgoraUI directory, run 'rails server' on the command line (run bundle install and create database if necessary).

In another terminal, run 'script/poller run' from the AgoraUI directory

From another terminal, run ActiveMQ (bin/activemq console)

In the Agora java application, run main in the KickoffsQueues class.

Finally, go to localhost:3000 in a browser to access the AgoraUI client
