import java.util.Scanner;

public class AI {
    private Hand hand;
    private boolean playing;
    private Scanner input;

    /**
     * creates a new AI object with an empty hand
     */
    public AI(){
        hand = new Hand();
        playing = true;
        input = new Scanner(System.in);
    }

    /**
     *Used to deal cards into the AI objects hand attribute,
     * this is done by inputting two characters, the first corresponding to the
     * value of the playing card (j for jack, q for queen, k for king, x for 10),
     * the second being the first letter of the suit of the playing card. All
     * can be upper or lower case.
     */
    public void dealHand(){
        System.out.println("Enter the cards in the Ai's hand: ");
        for (int i=0; i<7; i++){
            Card card = deduceCard(input.nextLine());
            System.out.println("You inputted: "+card.toString());
            hand.addCard(card);
        }
    }

    /**
     * From a string of two characters(c), the first stating the value of the card
     * and the second stating the suit of the card, this method deduces and creates
     * a Card object reflecting the description given by c.
     * @param c
     * @return
     */
    public Card deduceCard(String c){
        char value = c.charAt(0);
        char suit = c.charAt(1);

        //find the value
        int v;
        if (value == 'a' || value == 'A'){
            v = 1;
        }else if (value == 'x' || value == 'X') {
            v = 10;
        }else if (value == 'j' || value == 'J'){
            v = 11;
        }else if (value == 'q' || value == 'Q'){
            v = 12;
        }else if (value == 'k' || value == 'K'){
            v = 13;
        }else{
            v = value - '0';
        }

        //find the suit
        Suit s;
        if (suit == 's' || suit == 'S'){
            s = Suit.S;
        }else if (suit == 'h' || suit == 'H'){
            s = Suit.H;
        }else if (suit == 'c' || suit == 'C'){
            s = Suit.C;
        }else{
            s = Suit.D;
        }

        //create Card object
        Card card = new Card(v,s);
        return card;
    }

    /**
     * responsible for the overarching loop of the ai's actions each turn
     */
    public void takeTurn(){
        if (!considerDiscard()){
            takeFromDeck();
            hand.deducePotency();
        }
        discardCard();
    }

    /**
     * simulate taking the top card of the discard pile and assess whether it
     * would be worth picking it up.
     * @return
     */
    public boolean considerDiscard(){
        System.out.println("What is the top card of the discard pile?: ");
        Card card = deduceCard(input.nextLine());
        hand.addCard(card);
        hand.deducePotency();
        Card discardCard = hand.leastPotency();
        if (discardCard == card){
            hand.removeCard(card);
            return false;
        } else{
            System.out.println("Take the top of the discard pile.");
            return true;
        }
    }

    /**
     * takes the top card of the deck and adds it into the hand
     */
    public void takeFromDeck(){
        System.out.println("Take the top card of the deck.");
        System.out.println("What was the card?: ");
        Card card = deduceCard(input.nextLine());
        hand.addCard(card);
    }

    /**
     * Finds the card that should be discarded
     */
    public void discardCard(){
        Card discardCard = hand.leastPotency();
        System.out.println("Discard "+discardCard.toString()+".");
        hand.removeCard(discardCard);
    }

    /**
     * returns the boolean value of playing
     * @return
     */
    public boolean isPlaying() {
        return playing;
    }
}
