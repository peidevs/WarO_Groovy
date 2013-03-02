
package net.codetojoy.waro.casino

import net.codetojoy.waro.domain.* 

class Game {
    
    def verbose = true
    
    def determineWinner(def table) {
        def kitty = table.kitty
        def players = table.players
        
        table.assertTotals()
        
        def max = 0
        def winner 
        
        // TODO: use inject ?
        players.each { p ->
            if (p.playerStats.total > max) {
                max = p.playerStats.total
                winner = p
            }
        }

        if (verbose) {
            players.each { p ->
                def stats = p.playerStats
                println "$p.name won $stats.rounds rounds with $stats.total"
            }                            
        }
        
        winner.playerStats.wins++
        println "\nGame summary:"
        println "overall WINNER is: $winner.name "                
    }
    
    def playGame(def numCards, def players) {
        def dealer = new Dealer()
        
        def table = dealer.deal(numCards, players)
        
        if (verbose) {
            // println "kitty: ${table.kitty.toString()}"
            table.players.each { p -> println "${p.name}: ${p.hand.toString()}" }
        }
    
        dealer.play(table)
        
        determineWinner(table)
    }
}
