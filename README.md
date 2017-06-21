[![Build Status](https://travis-ci.org/peidevs/WarO_Groovy.svg?branch=master)](https://travis-ci.org/peidevs/WarO_Groovy)

WarO_Groovy
===========

The War-O [code kata](https://en.wikipedia.org/wiki/Kata_(programming)) in Groovy.

To run:

* install Gradle from http://gradle.org
* configure numCards, numGames and players in config.groovy
* type (or script): 
```
gradle myBuild
cd staging
java -jar warO.jar ../config.groovy
```

TODO: make a Gradle task to run the program

Rules:
---------

Use a deck of N cards with no suits, just natural numbers from 1 to N.
(as in Rack-O http://en.wikipedia.org/wiki/Rack-O)

Shuffle and deal N cards evenly to X players and a kitty.

For each round:
- Reveal a card from the kitty. This is the 'prize card'.
- Each player selects a 'bid' from his/her hand.
- Bids are revealed: highest bid wins points according to value of the prize card.

After all rounds, player with most points wins.

Example:
---------

Deck is [1,2,3,4,5,6,7,8,9]

John's hand is [2,4,9]
Alice's hand is [1,3,8]
kitty is [5,6,7]

Round 1, prize card is 5
John bids 4, Alice bids 8 -> Alice wins 5 pts

Round 2, prize card is 6
John bids 9, Alice bids 1 -> John wins 6 pts

Round 3, prize card is 7
John bids 2, Alice bids 3 -> Alice wins 7 pts

Alice wins (12 pts) over John (6 pts)

