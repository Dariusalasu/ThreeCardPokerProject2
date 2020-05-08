import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class DeckAndDealerTest {
    Deck myDeck;
    Dealer myDealer;
    Player myPlayer;
    ArrayList<Card> hand;
    char characters[] = {'C', 'D', 'S', 'H'};

    // setup:
    // Initializes a dealer to test Dealer class
    // Initializes a Deck to hold a deck and test Deck class
    @BeforeEach
    void setup() {
        myDeck = new Deck();
        myDealer = new Dealer();
        myPlayer = new Player();
        hand = new ArrayList<Card>();
    } // End of setup

    // initDeckSize:
    // Checks that deck was properly initialized with a size of 52
    @Test
    void initDeckSize() {
        assertEquals(52, myDeck.size(), "Error: Initial Deck size is not correct");
    } // End of initDeckSize

    // initDealer:
    // Checks that dealer was properly initialized with a size of 52
    @Test
    void initDealer() {
        assertEquals(52, myDealer.getDeck().size(), "Error: Initial Dealer deck size is not correct");
    } // End of initDealer

    // initPlayer:
    // Checks that player was properly initialized with a size of 52

    // getCardsReturnCards:
    // Inputs an array of cards of size 4 of the same suit and ensures that the following methods work:
    //     - getCards: Ensures that this method stores given arraylist into the dealer's hand
    //     - returnCards: Ensures that this method returns an arraylist of the dealer's hand
    @Test
    void getCardsReturnCards() {
        for(int i = 5; i < 9; i++) {
            hand.add(new Card('C', i));
        }
        myDealer.getCards(hand);
        assertEquals(4, myDealer.returnCards().size(), "Error: Dealer did not store hand correctly");
    }

    // getCardsReturnCardsDif:
    // Inputs an array of cards of size 4 of different suits and ensures that the following methods work:
    //     - getCards: Ensures that this method stores given arraylist into the dealer's hand
    //     - returnCards: Ensures that this method returns an arraylist of the dealer's hand
    @Test
    void getCardsReturnCardsDif() {
        for(int i = 5; i < 9; i++) {
            hand.add(new Card(characters[i-5], i));
        }
        myDealer.getCards(hand);
        assertEquals(4, myDealer.returnCards().size(), "Error: Dealer did not store hand correctly");
    } // End of getCardsReturnCardsDif

    // assertSameSuit:
    // Inputs an array of cards of size 3 with same suits and ensures that the dealer stores the correct cards
    @Test
    void assertSameSuit() {
        for(int i = 5; i < 8; i++) {
            hand.add(new Card('H', i));
        }
        myDealer.getCards(hand);
        for(Card c : myDealer.returnCards()) {
            assertEquals('H', c.getSuit(), "Error: Dealer deck does not have correct card");
        }
    } // End of assertSameSuit

    // assertSameValue:
    // Inputs an array of cards of size 3 with same values and ensures that the dealer stores the correct cards
    @Test
    void assertSameValue() {
        for(int i = 0; i < 4; i++) {
            hand.add(new Card(characters[i], 12));
        }
        myDealer.getCards(hand);
        for(Card c : myDealer.returnCards()) {
            assertEquals(12, c.getValue(), "Error: Dealer deck does not have correct card");
        }
    } // End of assertSameValue

    // checkDealHand:
    // Initializes temporary dealer to check the following:
    //     - Checks if dealHand returns 3 cards and reduces dealer's deck by 3
    //     - Uses returnCards to check if getCards stored the hand dealt into the dealer
    // Checks if dealHand returns 3 cards and if dealer's deck is reduced by 3
    @Test
    void checkDealHand() {
        Dealer d = new Dealer();
        d.getCards(d.dealHand());
        assertEquals(3, d.returnCards().size(), "Error: Hand dealt not correct");
        assertEquals(49, d.getDeck().size(), "Error: Deck size not correct after dealing hand");
    }

    // dealFirstHand:
    // Deals first hand and checks the following:
    //     - Check if hand dealt is size of 3
    //     - Check if dealer's deck was reduced by 3
    @Test
    void dealFirstHand() {
        hand = myDealer.dealHand();
        assertEquals(3, hand.size(), "Error: Hand dealt is not correct");
        assertEquals(49, myDealer.getDeck().size(), "Error: Dealer's deck's size was not decreased");
    } // End of dealFirstHand

    // dealSecondHand:
    // Deals second hand and checks the following:
    //     - Check if second hand dealt is a size of 3
    //     - Check if dealer's deck was again reduced by 3
    @Test
    void dealSecondHand() {
        hand = myDealer.dealHand();
        hand = myDealer.dealHand();
        assertEquals(3, hand.size(), "Error: Hand dealt is not correct");
        assertEquals(46, myDealer.getDeck().size(), "Error: Dealer's deck's size was not decreased");
    } // End of dealSecondHand

    // resetDeck:
    // Deals hands 6 times to have 34 cards left in the deck.
    // Asserts that deck is reset and shuffled before 7th hand is dealt.
    @Test
    void resetDeck() {
        for(int i = 1; i < 7; i++) {
            hand = myDealer.dealHand();
        }
        assertEquals(34, myDealer.getDeck().size(), "Error: Dealer's deck's size was not decreased");
        hand = myDealer.dealHand();
        assertEquals(49, myDealer.getDeck().size(), "Error: Dealer's deck was not reset and shuffled");
    } // End of resetDeck
}
