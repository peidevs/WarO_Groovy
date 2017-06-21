
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
        def winningBid = findRoundWinner(prizeCard, players)
        def winner = winningBid.player

        if (verbose) { println "\nthis round: ${winner.name} WINS $prizeCard with ${winningBid.offer}" }

        winner.playerStats.numRoundsWon++
        winner.playerStats.total += prizeCard
        
        winner        
    }

    // returns Expando with 'Player winner' and 'int winningBid'
    protected def findRoundWinner(def prizeCard, def players) {
        def bids = players.collect { p -> p.getBid(prizeCard) }
        def winningBid = bids.max { b -> b.offer }

        return winningBid      
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
