import java.util.ArrayList;

public class ThreeCardLogic {
    // Variables to hold result of comparing hands
    private static int draw = 0;
    private static int dealerWon = 1;
    private static int playerWon = 2;
    private static int payouts[] = {0, 40, 30, 6, 3, 1}; // Payouts

    // evalHand:
    // This method takes an arraylist of cards as it's parameter.
    // By comparing each card value/suit in the hand, it will set the
    // corresponding boolean variable to the opposite. This allows for simple
    // if-elseif-else statements to determine the value of the hand.
    public static int evalHand(ArrayList<Card> hand) {
        // Variables representing hand values
        boolean straight = true;
        boolean flush = true;
        boolean threeKind = true;
        boolean pair = false;

        // Iterate through hand and compare each card to each other
        for(int i = 0; i < hand.size()-1; i++) {
            Card cardI = hand.get(i);
            for(int j = i+1; j < hand.size(); j++) {
                Card cardJ = hand.get(j);
                // Check if left card value is not card value + 1
                // This means it is not a straight
                if(straight && (cardI.getValue() != (hand.get(i+1).getValue()+1))) {
                    straight = false;
                }
                // Check if card suits are not equal
                // This means it is not a flush
                if(flush && (cardI.getSuit() != cardJ.getSuit())) {
                    flush = false;
                }
                // Check if card values are not equal
                // This means it is not a three of a kind
                if(threeKind && (cardI.getValue() != cardJ.getValue())) {
                    threeKind = false;
                }
                // Check if two card values are equal
                // This means it is a pair
                if(!pair && (cardI.getValue() == cardJ.getValue())) {
                    pair = true;
                }
            }
        }

        // Check and determine value of hand
        if(straight && flush) {
            return 1;
        } else if(threeKind) {
            return 2;
        } else if(straight) {
            return 3;
        } else if(flush) {
            return 4;
        } else if(pair) {
            return 5;
        } else {
            return 0;
        }
    } // End of evalHand

    // getPayout:
    // Helper method to determine payout
    public static int getPayout(ArrayList<Card> hand, int bet) {
        int eval = evalHand(hand);
        if(eval == 0) {
            return(bet);
        }
        return(payouts[eval]*bet);
    } // End of getPayout

    // evalPPWinnings:
    // This method takes an arraylist of cards and a int as it's parameters
    // This method calculates the value of of the hand by calling evalHand.
    // It then chooses the corresponding wager and returns the (payout * the bet)-bet.
    // This is the value that will be added to totalWinnings.
    public static int evalPPWinnings(ArrayList<Card> hand, int bet) {
        int eval = evalHand(hand);
        return (payouts[eval] * bet);
    } // End of evalPPWinnings

    // comparePairs:
    // This method takes two arraylists of cards as it's parameter
    // This method is a helper method to calculate the winner if both hands contain a pair
    private static int comparePairs(ArrayList<Card> dealer, ArrayList<Card> player) {
        // Get value of pair and third card for dealer
        int pairD = dealer.get(0).getValue();
        int notPairD;
        if(pairD == dealer.get(1).getValue()) {
            notPairD = dealer.get(2).getValue();
        } else if(pairD == dealer.get(2).getValue()) {
            notPairD = dealer.get(1).getValue();
        } else {
            notPairD = pairD;
            pairD = dealer.get(1).getValue();
        }

        // Get value of pair and third card for player
        int pairP = player.get(0).getValue();
        int notPairP;
        if(pairP == player.get(1).getValue()) {
            notPairP = player.get(2).getValue();
        } else if(pairP == player.get(2).getValue()) {
            notPairP = player.get(1).getValue();
        } else {
            notPairP = pairP;
            pairP = player.get(1).getValue();
        }

        // Compare values
        if(pairD > pairP) {
            return dealerWon;
        } else if(pairP > pairD) {
            return playerWon;
        } else if(notPairD > notPairP) {
            return dealerWon;
        } else if(notPairP > notPairD) {
            return playerWon;
        }
        return draw;
    } // End of comparePairs

    // compareHands:
    // This method takes two arraylist of cards as it's parameter.
    // It finds the value of each hand (dealer and player) and returns the
    // winner based on the following conditions:
    //     0 if neither hand won
    //     1 if the dealer hand won
    //     2 if the player hand won
    // The dealer hand or player hand can still win if hand values are the same.
    // If they are then the values of each card will be compared, with the
    // winner having the highest value.
    public static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player) {
        // Get hand values
        int evalD = evalHand(dealer);
        //System.out.println("EvalD: " + evalD);
        int evalP = evalHand(player);
        //System.out.println("EvalP: " + evalP);

        // Ensures both hands are not highest hands
        if(evalD > 0 || evalP > 0) {
            // If one hand is equal to highest hand value, return the other
            if(evalD == 0) {
                return playerWon;
            } else if(evalP == 0) {
                return dealerWon;
            }

            // Both hands are not highest hand. Therefore determine winner based on winning hand order
            // If eval values are not equal, return winner with highest eval value
            if(evalD < evalP) {
                return dealerWon;
            } else if(evalP < evalD) {
                return playerWon;
            }

            // Logic to determine winner if both hands are pair hands
            if(evalD == 5) {
                // Get value of pair and third card for dealer
                return comparePairs(dealer, player);
            }
        }

        // Reached if highest card in sorted order must be tested (excluding draws by pair hands)
        for(int i = 0; i < dealer.size(); i++) {
            // Iterate through each card and compare
            //System.out.println("Dealer: " + dealer.get(i).getValue());
            //System.out.println("Player: " + player.get(i).getValue());
            if(dealer.get(i).getValue() > player.get(i).getValue()) {
                return dealerWon;
            } else if(player.get(i).getValue() > dealer.get(i).getValue()) {
                return playerWon;
            }
        }

        // All card values are the same
        return draw;
    } // End of compareHands
}
