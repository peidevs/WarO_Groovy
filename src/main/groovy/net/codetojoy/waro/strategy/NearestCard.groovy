
package net.codetojoy.waro.strategy 

class NearestCard implements Strategy {
    int selectCard(int prizeCard, List<Integer> hand, int maxCard) {
        def result
        
        hand.inject( 1000 * 1000, { diff, card ->
            def thisDiff = Math.abs(prizeCard - card)

            if (thisDiff < diff) {
                diff = thisDiff
                result = card
            }      
            
            diff       
        } )
        
        result
    }
}
