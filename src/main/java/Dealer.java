import java.util.ArrayList;

public class Dealer {
    // Private variables
    private Deck theDeck;
    private ArrayList<Card> dealersHand;

    // Dealer:
    // Constructor for Dealer class
    Dealer() {
        theDeck = new Deck(); // Initialize the deck
    } // End of constructor

    public Deck getDeck() {
        return theDeck;
    } // End of getDeck

    // getCards:
    // Takes an arraylist of cards as it's parameter
    // Store that arraylist into dealer's hand
    public void getCards(ArrayList<Card> l) {
        dealersHand = l;
    } // End of getCards

    // returnCards:
    // Returns the arraylist containing the dealer's hand
    public ArrayList<Card> returnCards() {
        return dealersHand;
    } // returnCards

    // dealHand:
    // This method will draw three cards from the deck and return them in an
    // arraylist in sorted order, from largest value to smallest value.
    public ArrayList<Card> dealHand() {
        // Check if deck size is less than 34
        // If so, reshuffle deck
        if(theDeck.size() <= 34) {
            theDeck.newDeck();
        }

        // Create temp arraylist
        ArrayList<Card> tmpArray = new ArrayList<Card>();

        // Add three cards from deck into temp arraylist
        for(int i = 1; i <= 3; i++) {
            Card tmpCard = theDeck.get(0); // Get card for calculations
            theDeck.remove(0); // Remove card from deck
            // Insert card into array by sorting from largest to smallest value
            // Note: card suit does not matter
            int idx = 0;
            for(Card c : tmpArray) {
                if(tmpCard.getValue() >= c.getValue()) {
                    break;
                }
                idx++;
            }
            tmpArray.add(idx, tmpCard); // Add at appropriate index
        }
        return tmpArray; // Return temp arraylist
    } // End of dealHand
}
