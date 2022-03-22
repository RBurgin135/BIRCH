import static java.lang.Math.abs;

public class Hand {
    private Card[] cards;
    private int handSize;
    private static final int maxCards = 8;

    /**
     * Creates a new hand object with no cards
     */
    public Hand(){
        cards = new Card[maxCards];
        handSize = 0;
    }

    /**
     * adds a card to the hand
     * @param card
     */
    public void addCard(Card card){
        if (handSize != maxCards){
            cards[handSize] = card;
            handSize++;
        }
    }

    /**
     * Discards a Card from the hand and notifies the user of it, then sorts the list after.
     * @param card
     */
    public void removeCard(Card card){
        for (int i = 0; i<handSize; i++){
            if (cards[i]==card){
                cards[i] = null;
                handSize--;
                sortList();
                break;
            }
        }
    }

    /**
     * Sorts the hand so that the empty element in the hand is
     * always at the end.
     */
    public void sortList(){
        if (cards[7] != null){
            Card c = cards[7];
            cards[7] = null;
            for (int i = 0; i<8; i++){
                if (cards[i]==null){
                    cards[i]=c;
                    break;
                }
            }
        }
    }

    /**
     * find the least valuable card in the hand
     * @return
     */
    public Card leastPotency(){
        Card leastPotentCard = new Card(1, Suit.S);
        int leastPotent = 999;
        for (int i = 0; i<maxCards; i++){
            if (cards[i].getPotency() < leastPotent) {
                leastPotentCard = cards[i];
                leastPotent = cards[i].getPotency();
            }
        }
        return leastPotentCard;
    }

    /**
     * Over-arching method for deducing how valuable each card is in the hand
     */
    public void deducePotency(){
        resetPotency();
        detectRuns();
        detectSets();
    }

    /**
     * resets the potency values for all the cards in the hand
     */
    public void resetPotency(){
        for (int i =0; i<handSize; i++){
            cards[i].setPotency(0);
        }
    }

    /**
     * used to detect runs in the hand and adjust the cards potency accordingly
     * being closer in value to cards of the same suit means a higher potency for that card
     */
    public void detectRuns(){
        for (int i=0; i< handSize; i++){
            Card c = cards[i]; //card finding its potency
            for (int z=0;z<handSize; z++){
                if(i!=z){ //check they aren't the same card
                    Card x = cards[z]; //card c to be examined against
                    if (c.getSuit() == x.getSuit()){ //if the suits are the same they can make a run
                        int valueDiff = abs(c.getValue()-x.getValue());
                        switch (valueDiff){
                            case 1:
                                c.setPotency(c.getPotency()+3);
                                break;
                            case 2:
                                c.setPotency(c.getPotency()+2);
                                break;
                            case 3:
                                c.setPotency(c.getPotency()+1);
                                break;
                        }
                    }
                }
            }
        }
    }

    /**
     * used to detect runs in the hand and adjust the cards potency accordingly
     * more cards in a set means more potency for the cards in the set
     */
    public void detectSets(){
        int total;
        for (int i=0; i< handSize; i++) {
            Card c = cards[i]; //card finding its potency
            total = 0;
            for (int z = 0; z < handSize; z++) {
                if (i != z) { //check they aren't the same card
                    Card x = cards[z]; //card c to be examined against
                    if (c.getValue() == x.getValue()) {
                        total++;
                    }
                }
            }
            c.setPotency(c.getPotency()+(total*2));
        }
    }
}
