
package net.codetojoy.waro

import net.codetojoy.waro.domain.* 
import net.codetojoy.waro.strategy.* 
import net.codetojoy.waro.casino.* 

// TODO: add logging

def config = new Config(args[0])

def tourney = new Tourney(config)
tourney.playGames()









