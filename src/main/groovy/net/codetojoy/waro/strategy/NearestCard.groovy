
package net.codetojoy.waro.strategy 

class NearestCard implements Strategy {
    int selectCard(int prizeCard, List<Integer> hand, int maxCard) {
        def result
        def diff = 1000 * 1000
        
        hand.each { c ->
            def thisDiff = Math.abs(prizeCard - c)
            if (thisDiff < diff) {
                diff = thisDiff
                result = c
            }
        }
        
        result
    }
}
