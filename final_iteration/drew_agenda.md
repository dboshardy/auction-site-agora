#Final Presentation Agenda#
##Part 1. Demo##
##Part 2. Description of Development methodology##
###2a. High-Level Architecture###
1. Diagram
2. MVC
3. Systems used
###2b. Class UML###
Patterns used:
1. Strategy (Payment)
2. Abstract Class (Janitor)
###2c. Communication Diagrams###
1. Flag Auction
2. Search for Auctions
3. 
###2d. Use Case Diagrams###
###2e. Story Map###
###2f. Did it work?###
##Part 3. Individual Presentations##
###3a. Drew -- Hibernate###
Hibernate ended up working out really well.  It was both the best and the most difficult part of the project for me.  It was great in that it took care of a lot of things automagically.  It wasn\'t great simply because I used it wrong.  I didn\'t let Hibernate create its own schema. We had a schema that worked and wanted to use that. So I mapped that schema to the classes we had created.  This ended up working just fine.  It only meant a little bit of extra work to get things like join tables (Shopping Cart, Watchlist, Categories) working with pure SQL instead of the fancy methods Hibernate provides. ANd the SQL to do so was not very difficult.  

However, this did lead to a pain point.  I had never worked with Hibernate before, nor had I ever had to design or build a database controller.  That said, once I started, it only got easier.  

My Eureka Moment was when I first got Hibernate running and realized how simple it could be.

What was easy but turned out hard. 
###3b. Thom -- Frontend###
###3c. Miao -- Messaging###
###3d. Bowen -- Email/Transactions?###
##Part 4. General Pain Points##
##Part 5. Easy but turned out hard##
##Part 6. Hard but turned out easy##
##Part 7. Eureka Moment##
##Part 8. Coolest Thing##
##Part 9. Design Thinking vs. Code and Refactor

