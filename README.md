# Checkout Kata

## Brief 

To code a super market checkout that can calculate the total price of a number of items, including multibuy deals. As these prices change regularly, the pricing rules will need to be input each time.

Please use `sbt test` to run.

## Wins

I made a case class for the multibuy deals, with the relevant information (quantity to purchase and sale price) and used this as an optional parameter on the stock case class. This allowed me to keep all the details for an item under one case class, which meant the code I used could be cleaner (as all the details are found under 1 case class), and there would be less coordination between different data types.

## Challenges

The hardest part was incorporating the sale price for items. As the sale price needs to be added to the total price no matter the order items were scanned, I decided to use `.count()` to get the totals of each item. From there, the number for the totals is used for the offer logic using division and modulus against the quantity needed for the offer to arrive at the total price. As all the offer details are optional, if there is no offer on the item then simple multiplication is used to calculate the total. 
This method is used while mapping through the stock lists, so every item is accounted for. The resulting list is summed, and this returns the total.

## Bugs

If an item is missing on the stock list - but is present on the items list then this code will not work. I think the ideal way to fix this would be to return the total for the rest of the items and a string that would say that the missing item was unavailable for purchase, though this would create a type conflict. Another solution could be to throw an error whenever this happens.
