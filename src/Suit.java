public enum Suit {
    S, H, C, D;

    @Override
    public String toString() {
        if (this == S){
            return "Spades";
        }else if (this == H){
            return "Hearts";
        }else if (this == C){
            return "Clubs";
        }else {
            return "Diamonds";
        }
    }
}
