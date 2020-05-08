import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand;
    private int anteBet;
    private int playBet;
    private int pairPlusBet;
    private int totalWinnings;

    // Player:
    // Constructor for Player class
    Player() {
        anteBet = 0;
        playBet = 0;
        pairPlusBet = 0;
        totalWinnings = 0;
    } // End of constructor

    public int getTotalWinnings() {
        return totalWinnings;
    } // End of getTotalWinnings

    public void updateTotalWinnings(int v) {
        totalWinnings += v;
    } // End of updateTotalWinnings

    // getCards:
    // Takes an arraylist of cards as it's parameter
    // Store that arraylist into player's hand
    public void getCards(ArrayList<Card> l) {
        hand = l;
    } // End of getCards

    // returnCards:
    // Returns the arraylist containing the player's hand
    public ArrayList<Card> returnCards() {
        return hand;
    } // returnCards

    // setAnteBet:
    // Takes a bet (from $5 to $25) and stores it into the variable anteBet
    public void setAnteBet(int b) {
        anteBet = b;
    } // End of setAnteBet

    // getAnteBet:
    // Returns the value of anteBet
    public int getAnteBet() { return anteBet; } // End of getAnteBet

    // setPlayBet:
    // If the player decides to play, set the playBet variable equal to
    // the anteBet variable.
    public void setPlayBet(int b) {
        playBet = b;
    } // End of setPlayBet

    // getPlayBet:
    // Returns the value of playBet
    public int getPlayBet() { return playBet; } // End of getPlayBet

    // setPairPlusBet:
    // Takes a bet (from $5 to $25) and stores it into the variable pairPlusBet
    public void setPairPlusBet(int b) {
        pairPlusBet = b;
    } // End of setPairPlusBet

    // getPairPlusBet:
    // Returns the value of pairPlusBet
    public int getPairPlusBet() { return pairPlusBet; } // End of getPairPlusBet
}
