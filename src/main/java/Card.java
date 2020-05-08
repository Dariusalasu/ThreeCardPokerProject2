public class Card {
    private char suit;
    private int value;

    Card(char s, int v) {
        suit = s;
        value = v;
    } // End of constructor

    public char getSuit() {
        return suit;
    } // End of getSuit

    public int getValue() {
        return value;
    } // End of getValue

    public String getName() { return (Integer.toString(value) + Character.toString(suit)); } // End of getName
}
