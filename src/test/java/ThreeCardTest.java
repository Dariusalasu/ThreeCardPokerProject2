import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

class ThreeCardTest {
	ArrayList<Card> cards;
	ArrayList<Card> winD;
	ArrayList<Card> loseD;
	char characters[] = {'C', 'D', 'S', 'H'};
	static int bet1 = 20;
	static int bet2 = 5;

	// setup:
	// Initialize the arraylist cards
	@BeforeEach
	void setup() {
		cards = new ArrayList<Card>();
		winD = new ArrayList<Card>();
		loseD = new ArrayList<Card>();
	} // End of setup

	// evalHighest:
	// Creates a hand of cards that evaluates to a highest hand
	// Asserts that the value return by evalHand is 0 which represents the value for highest
	@Test
	void evalHighest() {
		for(int i = 3; i >= 1; i--) {
			cards.add(new Card(characters[i], i*2));
		}
		assertEquals(0, ThreeCardLogic.evalHand(cards), "Error: Hand does not evaluate to highest order");
	} // End of evalHighest

	// evalPair:
	// Creates a hand of cards that evaluates to a pair hand
	// Asserts that the value return by evalHand is 5 which represents the value for pair
	@Test
	void evalPair() {
		int i;
		for(i = 1; i <= 2; i++) {
			cards.add(new Card(characters[i], 5));
		}
		cards.add(new Card(characters[i], (i+1)));
		assertEquals(5, ThreeCardLogic.evalHand(cards), "Error: Hand does not evaluate to pair");
	} // End of evalPair

	// evalFlush:
	// Creates a hand of cards that evaluates to a flush hand
	// Asserts that the value returned by evalHand is 4 which represents the value for flush
	@Test
	void evalFlush() {
		for(int i = 3; i >= 1; i--) {
			cards.add(new Card('C', i*2));
		}
		assertEquals(4, ThreeCardLogic.evalHand(cards), "Error: Hand does not evaluate to flush");
	} // End of evalFlush

	// evalStraight:
	// Creates a hand of cards that evaluates to a straight hand
	// Asserts that the value returned by evalHand is 3 which represents the value for straight
	@Test
	void evalStraight() {
		for(int i = 4; i >= 2; i--) {
			cards.add(new Card(characters[i-1], i));
		}
		assertEquals(3, ThreeCardLogic.evalHand(cards), "Error: Hand does not evaluate to straight");
	} // End of evalStraight

	// evalThreeKind:
	// Creates a hand of cards that evaluates to a three of a kind hand
	// Asserts that the value returned by evalHand is 2 which represents the value for three of a kind
	@Test
	void evalThreeKind() {
		for(int i = 0; i < 3; i++) {
			cards.add(new Card(characters[i], 12));
		}
		assertEquals(2, ThreeCardLogic.evalHand(cards), "Error: Hand does not evaluate to Three of a kind");
	} // End of evalThreeKind

	// evalStraightFlush:
	// Creates a hand of cards that evaluates to a straight flush hand
	// Asserts that the value returned by evalHand is 1 which represents the value for straight flush
	@Test
	void evalStraightFlush() {
		for(int i = 10; i > 7; i--) {
			cards.add(new Card('C', i));
		}
		assertEquals(1, ThreeCardLogic.evalHand(cards), "Error: Hand does not evaluate to Straight Flush");
	} // End of evalStraightFlush

	// evalPPWinningsHighest:
	// Creates a hand that evaluates to a highest hand
	// Asserts that the PPWinnings of this hand is 0*20, where 20 is the bet
	// Asserts that the PPWinnings of this hand is 0*5, where 5 is the bet
	@Test
	void evalPPWinningsHighest() {
		for(int i = 3; i >= 1; i--) {
			cards.add(new Card(characters[i], i*2));
		}
		assertEquals(0, ThreeCardLogic.evalPPWinnings(cards, bet1), "Error: Hand PPWinnings not correct for highest hand");
		assertEquals(0, ThreeCardLogic.evalPPWinnings(cards, bet2), "Error: Hand PPWinnings not correct for highest hand");
	} // End of evalPPWinningsHighest

	// evalPPWinningsPair():
	// Creates a hand that evaluates to a pair hand
	// Asserts that the PPWinnings of this hand is 1*20, where 20 is the bet
	// Asserts that the PPWinnings of this hand is 1*5, where 5 is the bet
	@Test
	void evalPPWinningsPair() {
		int i;
		for(i = 1; i <= 2; i++) {
			cards.add(new Card(characters[i], 5));
		}
		cards.add(new Card(characters[i], (i+1)));
		assertEquals(20, ThreeCardLogic.evalPPWinnings(cards, bet1), "Error: Hand PPWinnings not correct for pair hand");
		assertEquals(5, ThreeCardLogic.evalPPWinnings(cards, bet2), "Error: Hand PPWinnings not correct for pair hand");
	} // End of evalPPWinningsPair

	// evalPPWinningsFlush:
	// Creates a hand that evaluates to a flush hand
	// Asserts that the PPWinnings of this hand is 3*20, where 20 is the bet
	// Asserts that the PPWinnings of this hand is 3*5, where 5 is the bet
	@Test
	void evalPPWinningsFlush() {
		for(int i = 3; i >= 1; i--) {
			cards.add(new Card('C', i*2));
		}
		assertEquals(60, ThreeCardLogic.evalPPWinnings(cards, bet1), "Error: Hand PPWinnings not correct for flush hand");
		assertEquals(15, ThreeCardLogic.evalPPWinnings(cards, bet2), "Error: Hand PPWinnings not correct for flush hand");
	} // End of evalPPWinningsFlush

	// evalPPWinningsStraight:
	// Creates a hand that evaluates to a straight hand
	// Asserts that the PPWinnings of this hand is 6*20, where 20 is the bet
	// Asserts that the PPWinnings of this hand is 6*5, where 5 is the bet
	@Test
	void evalPPWinningsStraight() {
		for(int i = 4; i >= 2; i--) {
			cards.add(new Card(characters[i-1], i));
		}
		assertEquals(120, ThreeCardLogic.evalPPWinnings(cards, bet1), "Error: Hand PPWinnings not correct for straight hand");
		assertEquals(30, ThreeCardLogic.evalPPWinnings(cards, bet2), "Error: Hand PPWinnings not correct for straight hand");
	} // End of evalPPWinningsStraight

	// evalPPWinningsThreeKind:
	// Creates a hand that evaluates to a three of a kind hand
	// Asserts that the PPWinnings of this hand is 30*20, where 20 is the bet
	// Asserts that the PPWinnings of this hand is 30*5, where 5 is the bet
	@Test
	void evalPPWinningsThreeKind() {
		for(int i = 0; i < 3; i++) {
			cards.add(new Card(characters[i], 12));
		}
		assertEquals(600, ThreeCardLogic.evalPPWinnings(cards, bet1), "Error: Hand PPWinnings not correct for a three of a kind hand");
		assertEquals(150, ThreeCardLogic.evalPPWinnings(cards, bet2), "Error: Hand PPWinnings not correct for a three of a kind hand");
	} // End of evalPPWinningsThreeKind

	// evalPPWinningsStraightFlush:
	// Creates a hand that evaluates to a straight flush hand
	// Asserts that the PPWinnings of this hand is 40*20, where 20 is the bet
	// Asserts that the PPWinnings of this hand is 40*5, where 5 is the bet
	@Test
	void evalPPWinningsStraightFlush() {
		for(int i = 10; i > 7; i--) {
			cards.add(new Card('C', i));
		}
		assertEquals(800, ThreeCardLogic.evalPPWinnings(cards, bet1), "Error: Hand PPWinnings not correct for straight flush hand");
		assertEquals(200, ThreeCardLogic.evalPPWinnings(cards, bet2), "Error: Hand PPWinnings not correct for straight flush hand");
	} // End of evalPPWinningsStraightFlush

	// compareHandsBothHighest:
	// Creates two hands for a win deck and a lose deck
	//     - Fills win deck with a winning highest hand
	//     - Fills lose deck with a losing highest hand
	//     - Fills cards with deck equal to winning deck
	// Asserts that calling with player=win and dealer=lose decks returns correct value
	// Asserts that calling with player=lose and dealer=win decks returns correct value
	// Asserts that calling with player=win and dealer=cards returns draw value
	@Test
	void compareHandsBothHighest() {
		for(int i = 3; i >= 1; i--) {
			winD.add(new Card(characters[i], (i*2)+5));
			loseD.add(new Card(characters[i], i*2));
			cards.add(new Card(characters[4-i], (i*2)+5));
		}
		assertEquals(2, ThreeCardLogic.compareHands(loseD, winD), "Error: Player did not win");
		assertEquals(1, ThreeCardLogic.compareHands(winD, loseD), "Error: Dealer did not win");
		assertEquals(0, ThreeCardLogic.compareHands(winD, cards), "Error: Supposed to draw");
	} // End of compareHandsBothHighest

	// compareHandsBothPairs:
	// Creates two hands for a win deck and a lose deck
	//     - Fills win deck with a winning pair hand
	//     - Fills lose deck with a losing pair hand
	//     - Fills cards with deck equal to winning deck
	// Asserts that calling with player=win and dealer=lose decks returns correct value
	// Asserts that calling with player=lose and dealer=win decks returns correct value
	// Asserts that calling with player=win and dealer=cards returns draw value
	@Test
	void compareHandsBothPairsOne() {
		int i;
		for(i = 0; i < 2; i++) {
			winD.add(new Card(characters[i], 10));
			cards.add(new Card(characters[i+2], 10));
			loseD.add(new Card(characters[i], 5));
		}
		winD.add(new Card(characters[0], 8));
		cards.add(new Card(characters[2], 8));
		loseD.add(new Card(characters[0], (2)));
		assertEquals(2, ThreeCardLogic.compareHands(loseD, winD), "Error: Player did not win");
		assertEquals(1, ThreeCardLogic.compareHands(winD, loseD), "Error: Dealer did not win");
		assertEquals(0, ThreeCardLogic.compareHands(winD, cards), "Error: Supposed to draw");
	} // End of compareHandsBothPairs

	// compareHandsBothFlush:
	// Creates two hands for a win deck and a lose deck
	//     - Fills win deck with a winning flush hand
	//     - Fills lose deck with a losing flush hand
	//     - Fills cards with deck equal to winning deck
	// Asserts that calling with player=win and dealer=lose decks returns correct value
	// Asserts that calling with player=lose and dealer=win decks returns correct value
	// Asserts that calling with player=win and dealer=cards returns draw value
	@Test
	void compareHandsBothFlush() {
		for(int i = 3; i >= 1; i--) {
			winD.add(new Card('C', (i*2)+5));
			loseD.add(new Card('C', i*2));
			cards.add(new Card('S', (i*2)+5));
		}
		assertEquals(2, ThreeCardLogic.compareHands(loseD, winD), "Error: Player did not win");
		assertEquals(1, ThreeCardLogic.compareHands(winD, loseD), "Error: Dealer did not win");
		assertEquals(0, ThreeCardLogic.compareHands(winD, cards), "Error: Supposed to draw");
	} // End of compareHandsBothFlush

	// compareHandsBothStraight:
	// Creates two hands for a win deck and a lose deck
	//     - Fills win deck with a winning straight hand
	//     - Fills lose deck with a losing straight hand
	//     - Fills cards with deck equal to winning deck
	// Asserts that calling with player=win and dealer=lose decks returns correct value
	// Asserts that calling with player=lose and dealer=win decks returns correct value
	// Asserts that calling with player=win and dealer=cards returns draw value
	@Test
	void compareHandsBothStraight() {
		for(int i = 3; i >= 1; i--) {
			winD.add(new Card(characters[i], i+10));
			loseD.add(new Card(characters[i], i+3));
			cards.add(new Card(characters[i-1], i+10));
		}
		assertEquals(2, ThreeCardLogic.compareHands(loseD, winD), "Error: Player did not win");
		assertEquals(1, ThreeCardLogic.compareHands(winD, loseD), "Error: Dealer did not win");
		assertEquals(0, ThreeCardLogic.compareHands(winD, cards), "Error: Supposed to draw");
	} // End of compareHandsBothStraight

	// compareHandsBothThreeKind:
	// Creates two hands for a win deck and a lose deck
	//     - Fills win deck with a winning three of a kind hand
	//     - Fills lose deck with a losing three of a kind hand
	// Asserts that calling with player=win and dealer=lose decks returns correct value
	// Asserts that calling with player=lose and dealer=win decks returns correct value
	@Test
	void compareHandsBothThreeKind() {
		for(int i = 0; i < 3; i++) {
			winD.add(new Card(characters[i], 12));
			loseD.add(new Card(characters[i], 10));
		}
		assertEquals(2, ThreeCardLogic.compareHands(loseD, winD), "Error: Player did not win");
		assertEquals(1, ThreeCardLogic.compareHands(winD, loseD), "Error: Dealer did not win");
	} // End of compareHandsBothThreeKind

	// compareHandsBothStraightFlush:
	// Creates two hands for a win deck and a lose deck
	//     - Fills win deck with a winning straight flush hand
	//     - Fills lose deck with a losing straight flush hand
	//     - Fills cards with deck equal to winning deck
	// Asserts that calling with player=win and dealer=lose decks returns correct value
	// Asserts that calling with player=lose and dealer=win decks returns correct value
	// Asserts that calling with player=win and dealer=cards returns draw value
	@Test
	void compareHandsBothStraightFlush() {
		for(int i = 10; i > 7; i--) {
			winD.add(new Card('C', i));
			loseD.add(new Card('D', i-5));
			cards.add(new Card('D', i));
		}
		assertEquals(2, ThreeCardLogic.compareHands(loseD, winD), "Error: Player did not win");
		assertEquals(1, ThreeCardLogic.compareHands(winD, loseD), "Error: Dealer did not win");
		assertEquals(0, ThreeCardLogic.compareHands(winD, cards), "Error: Supposed to draw");
	} // End of compareHandsBothStraightFlush

	// compareHandsHighestAndStraight
	// Creates two hands for a win deck and a lose deck
	//     - Fills win deck with a winning straight hand
	//     - Fills lose deck with a losing highest hand
	// Asserts that calling with player=win and dealer=lose decks returns correct value
	// Asserts that calling with player=lose and dealer=win decks returns correct value
	@Test
	void compareHandsHighestAndStraight() {
		for(int i = 3; i >= 1; i--) {
			winD.add(new Card(characters[i], i+10));
			loseD.add(new Card(characters[i], i*2));
		}
		assertEquals(2, ThreeCardLogic.compareHands(loseD, winD), "Error: Player did not win");
		assertEquals(1, ThreeCardLogic.compareHands(winD, loseD), "Error: Dealer did not win");
	} // End of compareHandsHighestAndStraight

	// compareHandsStraightAndThreeKind:
	// Creates two hands for a win deck and a lose deck
	//     - Fills win deck with a winning three of a kind hand
	//     - Fills lose deck with a losing straight hand
	// Asserts that calling with player=win and dealer=lose decks returns correct value
	// Asserts that calling with player=lose and dealer=win decks returns correct value
	@Test
	void compareHandsStraightAndThreeKind() {
		for(int i = 3; i >= 1; i--) {
			winD.add(new Card(characters[i], 12));
			loseD.add(new Card(characters[i], i+10));
		}
		assertEquals(2, ThreeCardLogic.compareHands(loseD, winD), "Error: Player did not win");
		assertEquals(1, ThreeCardLogic.compareHands(winD, loseD), "Error: Dealer did not win");
	} // End of compareHandsStraightAndThreeKind
}
