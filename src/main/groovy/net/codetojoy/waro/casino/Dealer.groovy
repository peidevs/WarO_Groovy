
package net.codetojoy.waro.casino

import net.codetojoy.waro.domain.* 
import com.google.common.collect.Lists

class Dealer {
    
    def verbose = true
    
    Table deal(int numCards, def players) {
        def numPlayers = players.size()
        
        def hands = dealHands(numCards, numPlayers)

        def kitty = hands[0]
        
        for (index in 1..numPlayers) {
            players[index - 1].hand = hands[index]
        }
        
        Table table = new Table(players, kitty)
        
        return table
    }
    
    void play(Table table) {
        table.kitty.each { prizeCard ->    
            playRound(prizeCard, table.players)
        }        
    }
    
    // -- internal 

    protected def dealHands(int numCards, int numPlayers) {
        def hands = []
        
        def deck = newDeck(numCards)        
        def numCardsInHand = getNumCardsInHand(numCards, numPlayers)

        Lists.partition(deck, numCardsInHand).each { unmodifiableHand ->
            def hand = []
            hand.addAll(unmodifiableHand)
            hands << hand
        }
        
        return hands
    }

    protected def playRound(def prizeCard, def players) {
        // TODO: no need to collect all bids before determining winner
        def bids = players.inject( [], { bids, player -> bids << player.getBid(prizeCard) } )

        def result = findRoundWinner(bids)
        int maxBid = result.max
        Player winner = result.winner

        if (verbose) { println "\nthis round: ${winner.name} WINS $prizeCard with ${maxBid}" }

        winner.playerStats.rounds++
        winner.playerStats.total += prizeCard
        
        winner        
    }

    protected def findRoundWinner(def bids) {
        def result = new Expando()

        bids.inject( 0, { max, bid ->
                         
             if (bid.offer > max) {
                 result.winner = bid.player
                 result.max = bid.offer
                 max = result.max
             }
             
             max
         } )  
         
         result      
    }

    protected def newDeck(def numCards) {
        def deck = []
        
        (1..numCards).each { deck << it }

        Collections.shuffle(deck)
        
        return deck
    }
    
    protected def getNumCardsInHand(def numCards, def numPlayers) {
        return (numCards / (numPlayers + 1)) as int
    }    
}
