public class Rummy {
    public static void main(String[] args) {
        AI ai = new AI(); //generates ai and hand object
        ai.dealHand(); //deals the ai a hand the user specifies

        while (ai.isPlaying()){ //iterate whilst the ai thinks its playing
            ai.takeTurn(); //ai takes its turn
        }
        System.out.println("AI HAS WON"); //ai has won
    }
}
