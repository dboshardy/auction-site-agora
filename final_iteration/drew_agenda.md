#Final Presentation Agenda#
##Part 1. Demo##
##Part 2. Description of Development methodology##
###2a. Development Approach###
We definitely took a risk-based approach to this.  We tackled the big set pieces first (foundations).  I took the database, Thom took the frontend and Miao and Bowen took the messaging aspect.  This approach lent itself well for me as I required some time to get acquainted with Hibernate.  Then, once those two pieces were in place, we connected them with the messaging.  After that, adding new functionality was super easy. 
###2a. High-Level Architecture###
1. Diagram
2. MVC
3. Systems used
###2b. Class UML###
Patterns used:

1. Strategy (Payment)
2. Abstract Class (Janitor)
###2c. Communication Diagrams###
1. Search for Auctions
2. Flag Auction
3. Checkout/Transaction
###2d. Use Case Diagrams###
This didn't really change must past the 3rd iteration.  The requirements never changed and we analyzed them and created good use cases for them in the first place.  
###2e. Story Map###
This only changed later when we realized one epic was a little too broad and needed to be broken into two epics. (View Auction details and View Auctions)
###2f. Did it work?###
##Part 3. Individual Presentations##
###3a. Drew -- Hibernate###
Hibernate ended up working out really well.  It was both the best and the most difficult part of the project for me.  It was great in that it took care of a lot of things automagically.  It wasn't great simply because I used it wrong.  I didn't let Hibernate create its own schema. We had a schema that worked and wanted to use that. So I mapped that schema to the classes we had created.  This ended up working just fine.  It only meant a little bit of extra work to get things like join tables (Shopping Cart, Watchlist, Categories) working with pure SQL instead of the fancy methods Hibernate provides. ANd the SQL to do so was not very difficult.  

####Pain Points####
However, this did lead to a pain point.  I had never worked with Hibernate before, nor had I ever had to design or build a database controller.  That said, once I started, it only got easier.  But when an issue came up between Hibernate and the database, it took a lot of debugging to find the error. A lot of times it was something to do with parameter mapping between objects and Hibernate entities.  This is probably due to my fiddling more than Hibernate doing anything wrong. 
Not being collocated was another. Also, not knowing enough about the front end. 

####Eureka Moment####
My Eureka Moment was when I first got Hibernate running and realized how simple it could be.

####What I thought was going to be hard but turned out easy. ####
I thought getting the controllers set up and the database ready to go was going to be very difficult.  But, once I got Hibernate and postgres working, it was a cinch.  
####What I thought was going to be easy but turned out hard. ####
Not much really. I anticipated a lot being hard, and was pleasantly surprised. But that may be my outlook on life. 

####Coolest Moment####
Seeing it working for the first time.
###3b. Thom -- Frontend###
###3c. Miao -- Messaging###
###3d. Bowen -- Email/Transactions?###
##Part 4. General Pain Points##
##Part 5. Easy but turned out hard##
##Part 6. Hard but turned out easy##
##Part 7. Eureka Moment##
##Part 8. Coolest Thing##
Seeing it working for the first time.
##Part 9. Design Thinking vs. Code and Refactor

