public class Card {
    private int value;
    private Suit suit;
    private int potency;

    @Override
    public String toString() {
        String name;
        switch(value){
            case 1:
                name = "Ace";
                break;
            case 11:
                name = "Jack";
                break;
            case 12:
                name = "Queen";
                break;
            case 13:
                name = "King";
                break;
            default:
                name = String.valueOf(value);
                break;
        }
        return name+" of "+suit.toString();
    }

    /**
     * Creates a card with a specified value and suit
     * @param value
     * @param suit
     * @return
     */
     public Card(int value, Suit suit){
         this.value = value;
         this.suit = suit;
         potency = 0;
     }

    /**
     * returns the value of the card
     * @return
     */
    public int getValue() {
        return value;
    }

    /**
     * returns the suit of the card
     * @return
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * returns the potency of the card
     * @return
     */
    public int getPotency() {
        return potency;
    }

    /**
     * sets the potency of the card
     * @param potency
     */
    public void setPotency(int potency) {
        this.potency = potency;
    }
}
