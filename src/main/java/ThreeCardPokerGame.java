import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

public class ThreeCardPokerGame extends Application {
	// Players and Dealer
	Dealer dealer = new Dealer();
	Player playerOne = new Player();
	Player playerTwo = new Player();
	// Images for backgrounds
	Image b1 = new Image("background1.jpg");
	Image b2 = new Image("background2.jpg");
	int background = 1; // Variable to identify which background image to use
	// ImageViews for 9 cards that will be visible to user
	ImageView p1c1 = new ImageView(new Image("gray_back.png"));
	ImageView p1c2 = new ImageView(new Image("gray_back.png"));
	ImageView p1c3 = new ImageView(new Image("gray_back.png"));
	ImageView p2c1 = new ImageView(new Image("gray_back.png"));
	ImageView p2c2 = new ImageView(new Image("gray_back.png"));
	ImageView p2c3 = new ImageView(new Image("gray_back.png"));
	ImageView dc1 = new ImageView(new Image("gray_back.png"));
	ImageView dc2 = new ImageView(new Image("gray_back.png"));
	ImageView dc3 = new ImageView(new Image("gray_back.png"));

	Button deal1, play1, fold1, deal2, play2, fold2, nextRound; // Buttons
	Label lA1, lA2, lPP1, lPP2, p1R, p1A, p1W, p2R, p2A, p2W; // Labels for texts
	HBox anteInput1, anteInput2, pairPlusInput1, pairPlusInput2; // Hboxes
	boolean played1 = true, played2 = true; // Boolean to identify whether players have played/folded

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Let's Play Three Card Poker!!!");

		// Create Border Pane Window
		BorderPane bp = new BorderPane();
		bp.setBackground(new Background(new BackgroundImage(b1, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		// End of creating border pane window

		// Create Menu bar with corresponding options
		Menu menu = new Menu("Options");
		menu.setStyle("-fx-text-fill: white");
		MenuItem m1 = new MenuItem("Exit");
		MenuItem m2 = new MenuItem("Fresh Start");
		MenuItem m3 = new MenuItem("NewLook");
		menu.getItems().add(m1);
		menu.getItems().add(m2);
		menu.getItems().add(m3);
		MenuBar mb = new MenuBar();
		mb.setStyle("-fx-background-color: #ac0000; -fx-font-weight: bold");
		mb.getMenus().add(menu);
		VBox vboxMenu = new VBox(mb);
		// End of creating menu

		// Event Handler for m1 button
		m1.setOnAction(e -> {primaryStage.close();});

		// Event Handler for m2 button
		m2.setOnAction(e -> {
			dealer = new Dealer();
			playerOne = new Player();
			playerTwo = new Player();
			changePic(p1c1, p1c2, p1c3, "gray_back", "gray_back", "gray_back");
			changePic(p2c1, p2c2, p2c3, "gray_back", "gray_back", "gray_back");
			changePic(dc1, dc2, dc3, "gray_back", "gray_back", "gray_back");
			p1A.setText(null);
			p2A.setText(null);
			p1R.setText("     Select Ante and Pair Plus Bets     ");
			p2R.setText(null);
			p1W.setText("Winnings: " + playerOne.getTotalWinnings());
			p2W.setText("Winnings: " + playerTwo.getTotalWinnings());
			nextRound.setVisible(false);
			disableElems(false, true, true, false, false,
					true, true, true, true, true);
		});

		// Event Handler for m3 button
		m3.setOnAction(e -> {
			// Swap backgrounds
			if(background == 1) {
				background = 2;
				mb.setStyle("-fx-background-color: #585858; -fx-font-weight: bold");
				bp.setBackground(new Background(new BackgroundImage(b2, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
			} else {
				background = 1;
				mb.setStyle("-fx-background-color: #ac0000; -fx-font-weight: bold");
				bp.setBackground(new Background(new BackgroundImage(b1, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
			}
		});

		// Insert menu into border pane
		bp.setTop(vboxMenu);
		vboxMenu.setAlignment(Pos.BASELINE_CENTER);

		// Set card preferences
		cardPref(p1c1, p1c2, p1c3);
		cardPref(p2c1, p2c2, p2c3);
		cardPref(dc1, dc2, dc3);

		// Buttons for player 1
		deal1 = new Button("Deal");
		deal1.setStyle("-fx-font-weight: bold");
		play1 = new Button("Play");
		play1.setStyle("-fx-font-weight: bold");
		fold1 = new Button("Fold");
		fold1.setStyle("-fx-font-weight: bold");

		// Get value for ante bet
		Spinner<Integer> spinA1 = new Spinner<Integer>();
		SpinnerValueFactory<Integer> valSpinA1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 25, 25);
		spinA1.setValueFactory(valSpinA1);
		spinA1.setMaxWidth(70);
		lA1 = new Label("Ante Bet:");
		lA1.setStyle("-fx-background-color: white");
		lA1.setMaxHeight(30);
		anteInput1 = new HBox(5, lA1, spinA1);
		anteInput1.setAlignment(Pos.CENTER);

		// Get value for pair plus bet
		Spinner<Integer> spinPP1 = new Spinner<Integer>();
		SpinnerValueFactory<Integer> valSpinPP1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 25, 25);
		spinPP1.setValueFactory(valSpinPP1);
		spinPP1.setMaxWidth(70);
		lPP1 = new Label("Pair Plus Bet:");
		lPP1.setStyle("-fx-background-color: white");
		lPP1.setMaxHeight(30);
		pairPlusInput1 = new HBox(5, lPP1, spinPP1);
		pairPlusInput1.setAlignment(Pos.CENTER);

		// Buttons for player 2
		deal2 = new Button("Deal");
		deal2.setStyle("-fx-font-weight: bold");
		play2 = new Button("Play");
		play2.setStyle("-fx-font-weight: bold");
		fold2 = new Button("Fold");
		fold2.setStyle("-fx-font-weight: bold");

		// Get value for ante bet
		Spinner<Integer> spinA2 = new Spinner<Integer>();
		SpinnerValueFactory<Integer> valSpinA2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 25, 25);
		spinA2.setValueFactory(valSpinA2);
		spinA2.setMaxWidth(70);
		lA2 = new Label("Ante Bet:");
		lA2.setStyle("-fx-background-color: white");
		lA2.setMaxHeight(30);
		anteInput2 = new HBox(5, lA2, spinA2);
		anteInput2.setAlignment(Pos.CENTER);

		// Get value for pair plus bet
		Spinner<Integer> spinPP2 = new Spinner<Integer>();
		SpinnerValueFactory<Integer> valSpinPP2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 25, 25);
		spinPP2.setValueFactory(valSpinPP2);
		spinPP2.setMaxWidth(70);
		lPP2 = new Label("Pair Plus Bet:");
		lPP2.setStyle("-fx-background-color: white");
		lPP2.setMaxHeight(30);
		pairPlusInput2 = new HBox(5, lPP2, spinPP2);
		pairPlusInput2.setAlignment(Pos.CENTER);

		// Set labels for player 1
		p1R = new Label("     Select Ante and Pair Plus Bets     ");
		p1R.setStyle("-fx-background-color: white; -fx-font-weight: bold");
		p1A = new Label("");
		p1A.setStyle("-fx-background-color: white; -fx-font-weight: bold");
		p1W = new Label("Winnings: " + Integer.toString(playerOne.getTotalWinnings()));
		p1W.setStyle("-fx-background-color: white; -fx-font-weight: bold");

		// Set labels for player 2
		p2R = new Label("");
		p2R.setStyle("-fx-background-color: white; -fx-font-weight: bold");
		p2A = new Label("");
		p2A.setStyle("-fx-background-color: white; -fx-font-weight: bold");
		p2W = new Label("Winnings: " + Integer.toString(playerTwo.getTotalWinnings()));
		p2W.setStyle("-fx-background-color: white; -fx-font-weight: bold");

		// Button to start new round
		nextRound = new Button("Next Round");
		nextRound.setStyle("-fx-font-weight: bold");
		nextRound.setVisible(false);

		// Initially allow player 1 to interact and decide to play
		// Check disableElems for more detail
		disableElems(false, true, true, false, false,
				true, true, true, true, true);

		// Event Handler for nextRound Button
		nextRound.setOnAction(e ->{
			// Change image of each card to back of card
			changePic(p1c1, p1c2, p1c3, "gray_back", "gray_back", "gray_back");
			changePic(p2c1, p2c2, p2c3, "gray_back", "gray_back", "gray_back");
			changePic(dc1, dc2, dc3, "gray_back", "gray_back", "gray_back");

			// Reset labels
			p1R.setText("     Select Ante and Pair Plus Bets     ");
			p2R.setText("");
			p1A.setText("");
			p2A.setText("");

			// Enable/Disable buttons/spinners
			disableElems(false, true, true, false, false,
					true, true, true, true, true);

			nextRound.setVisible(false);
		});

		// Event Handler for deal1 Button
		deal1.setOnAction(e -> {
			// Get cards for player one
			playerOne.getCards(dealer.dealHand());

			// Update label
			p1R.setText("");

			// Get cards and change player 1's cards to appropriate image
			ArrayList<Card> cards = playerOne.returnCards();
			changePic(p1c1, p1c2, p1c3, cards.get(0).getName(), cards.get(1).getName(), cards.get(2).getName());

			// Set player 1 bet values to appropriate inputs
			playerOne.setAnteBet(valSpinA1.getValue());
			playerOne.setPlayBet(playerOne.getAnteBet());
			playerOne.setPairPlusBet(valSpinPP1.getValue());

			// Enable/Disable Buttons/Spinners
			disableElems(true, false, false, true, true,
					true, true, true, true, true);
		});

		// Event Handler for play1 Button
		play1.setOnAction(e -> {
			// Update labels
			p1R.setText("     Player 1 has played     ");
			p2R.setText("     Select Ante and Pair Plus Bets     ");

			played1 = true; // Player 1 has played

			// Enable/Disable Buttons/Spinners
			disableElems(true, true, true, true, true,
					false, true, true, false, false);
		});

		// Event Handler for fold1 Button
		fold1.setOnAction(e -> {
			// Update labels
			p1R.setText("     Player 1 has folded: " + (-playerOne.getAnteBet()) + "     ");
			p2R.setText("     Select Ante and Pair Plus Bets     ");

			played1 = false; // Player 1 has folded

			// Enable/Disable Buttons/Spinners
			disableElems(true, true, true, true, true,
					false, true, true, false, false);
		});

		// Event Handler for deal2 Button
		deal2.setOnAction(e -> {
			// Get cards for player two
			playerTwo.getCards(dealer.dealHand());

			// Update label
			p2R.setText("");

			// Get cards and change player 2's cards to appropriate image
			ArrayList<Card> cards = playerTwo.returnCards();
			changePic(p2c1, p2c2, p2c3, cards.get(0).getName(), cards.get(1).getName(), cards.get(2).getName());

			// Set player 2 bet values to appropriate inputs
			playerTwo.setAnteBet(valSpinA1.getValue());
			playerTwo.setPlayBet(playerTwo.getAnteBet());
			playerTwo.setPairPlusBet(valSpinPP2.getValue());

			// Enable/Disable Buttons/Spinners
			disableElems(true, true, true, true, true,
					true, false, false, true, false);
		});

		// Event Handler for play2 Button
		play2.setOnAction(e -> {
			// Update label
			p2R.setText("     Player 2 has played     ");

			played2 = true; // Player 2 has played

			// Update elements to show you can/can't interact
			disableElems(true, true, true, true, true,
					true, true, true, true, true);

			// Both players have finished, evaluate the outcome
			evalGame();
		});

		// Event Handler for fold2 Button
		fold2.setOnAction(e -> {
			// Update label
			p2R.setText("     Player 2 has folded: " + (-playerTwo.getAnteBet()) + "     ");

			played2 = false; // Player 2 has folded

			// Enable/Disable Buttons/Spinners
			disableElems(true, true, true, true, true,
					true, true, true, true, true);

			// Both players have finished, evaluate the outcome
			evalGame();
		});

		// Organize border pane
		HBox p1Buttons = new HBox(20, deal1, play1, fold1);
		p1Buttons.setAlignment(Pos.CENTER);

		HBox p2Buttons = new HBox(20, deal2, play2, fold2);
		p2Buttons.setAlignment(Pos.CENTER);

		HBox p1Cards = new HBox(10, p1c1, p1c2, p1c3);
		VBox p1 = new VBox(10, p1A, p1R, p1Cards, p1Buttons, anteInput1, pairPlusInput1, p1W);
		p1.setAlignment(Pos.CENTER);

		HBox p2Cards = new HBox(10, p2c1, p2c2, p2c3);
		VBox p2 = new VBox(10, p2A, p2R, p2Cards, p2Buttons, anteInput2, pairPlusInput2, p2W);
		p2.setAlignment(Pos.CENTER);

		HBox players = new HBox(70, p1, nextRound, p2);
		players.setAlignment(Pos.CENTER);
		HBox dCards = new HBox(10, dc1, dc2, dc3);
		dCards.setAlignment(Pos.CENTER);
		VBox allComp = new VBox(30, dCards, players);
		allComp.setAlignment(Pos.TOP_CENTER);

		bp.setCenter(allComp);
		// End of organizing border pane

		// Set Scene and show stage
		Scene scene = new Scene(bp,1080,720);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	// evalGame;
	// Helper method called to evaluate the players and determine a winner
	public void evalGame() {
		boolean queenHigher = true; // Assume dealer has queen or higher
		dealer.getCards(dealer.dealHand()); // Draw hand for dealer

		// Get cards and change dealer's cards to appropriate image
		ArrayList<Card> cards = dealer.returnCards();
		changePic(dc1, dc2, dc3, cards.get(0).getName(), cards.get(1).getName(), cards.get(2).getName());

		// Evaluate player 1's hand to determine pair plus winnings
		int ppw1 = ThreeCardLogic.evalPPWinnings(playerOne.returnCards(), playerOne.getPairPlusBet());
		if(ppw1 == 0) {
			ppw1 -= playerOne.getPairPlusBet();
			p1A.setText("     Player One loses Pair Plus: " + ppw1 + "     ");
		} else {
			p1A.setText("     Player Two wins Pair Plus: " + ppw1 + "     ");
		}
		playerOne.updateTotalWinnings(ppw1); // Update total winnings for player 1
		// Evaluate player 2's hand to determine pair plus winnings
		int ppw2 = ThreeCardLogic.evalPPWinnings(playerTwo.returnCards(), playerTwo.getPairPlusBet());
		if(ppw2 == 0) {
			ppw2 -= playerTwo.getPairPlusBet();
			p2A.setText("     Player Two loses Pair Plus: " + ppw2 + "     ");
		} else {
			p2A.setText("     Player Two wins Pair Plus: " + ppw2 + "     ");
		}
		playerTwo.updateTotalWinnings(ppw2); // Update total winnings for player 2

		// Evaluate dealer's hand
		int dealerEval = ThreeCardLogic.evalHand(cards);
		if(dealerEval == 0) { // If hand is highest hand
			queenHigher = false; // Assume highest card is less than a queen
			for(Card c : cards) {
				// If highest card is at least a queen, update boolean
				if(c.getValue() >= 12) {
					queenHigher = true;
				}
			}
		}

		// If dealer's hand is valid for playing
		if(queenHigher) {
			String tmp = "";
			if(played1) { // Player 2 played
				// Get bets and compare dealer with player 1
				int eval1 = ThreeCardLogic.compareHands(cards, playerOne.returnCards());
				int bets = playerOne.getAnteBet()+playerOne.getPlayBet();
				if(eval1 == 1) { // Player 1 lost
					tmp = "     Player One losses to Dealer: " + (-bets) + "     ";
					playerOne.updateTotalWinnings(-(bets)); // Update total winnings
				} else if(eval1 == 2) { // Player 1 won
					int winnings = ThreeCardLogic.getPayout(playerOne.returnCards(), bets);
					tmp = "     Player One beats Dealer: " + winnings + "     ";
					playerOne.updateTotalWinnings(winnings); // Update total winnings
				} else { // Draw
					tmp = "     Player One and Dealer draw     ";
				}
				p1R.setText(tmp); // Update label
			} else { // Player 1 folded
				playerOne.updateTotalWinnings(-(playerOne.getAnteBet())); // Update total winnings
			}

			if(played2) { // Player 2 played
				// Get bets and compare dealer with player 2
				int eval2 = ThreeCardLogic.compareHands(cards, playerTwo.returnCards());
				int bets = playerTwo.getAnteBet()+playerTwo.getPlayBet();
				if(eval2 == 1) { // Player 2 lost
					tmp = "     Player Two losses to Dealer: " + (-bets) + "     ";
					playerTwo.updateTotalWinnings(-(bets)); // Update total winnings
				} else if(eval2 == 2) { // Player 2 won
					int winnings = ThreeCardLogic.getPayout(playerTwo.returnCards(), bets);
					tmp = "     Player Two beats Dealer: " + winnings + "     ";
					playerTwo.updateTotalWinnings(winnings); // Update total winnings
				} else { // Draw
					tmp = "     Player Two and Dealer draw     ";
				}
				p2R.setText(tmp); // Update label
			} else { // Player 2 folded
				playerTwo.updateTotalWinnings(-(playerTwo.getAnteBet())); // Update total winnings
			}
		} else { // If dealer's hand is not valid for playing
			// Update labels
			p1R.setText("     Dealer does not have at least Queen High     ");
			p2R.setText("     Dealer does not have at least Queen High     ");
		}

		// Update labels with winnings
		p1W.setText("Winnings: " + Integer.toString(playerOne.getTotalWinnings()));
		p2W.setText("Winnings: " + Integer.toString(playerTwo.getTotalWinnings()));
		nextRound.setVisible(true); // Button is visible to start next round
	} // End of evalGame

	// changePic:
	// Helper method to change ImageView to corresponding picture
	public void changePic(ImageView f, ImageView s, ImageView t, String pic1, String pic2, String pic3) {
		f.setImage(new Image(pic1 + ".png"));
		s.setImage(new Image(pic2 + ".png"));
		t.setImage(new Image(pic3 + ".png"));
	} // End of changePic

	// disableElems:
	// Helper method to enable/disable elements from being interacted with
	public void disableElems(boolean p1e, boolean p2e, boolean p3e, boolean p4e, boolean p5e, boolean p6e, boolean p7e, boolean p8e, boolean p9e, boolean p10e) {
		deal1.setDisable(p1e);
		play1.setDisable(p2e);
		fold1.setDisable(p3e);
		anteInput1.setDisable(p4e);
		pairPlusInput1.setDisable(p5e);
		deal2.setDisable(p6e);
		play2.setDisable(p7e);
		fold2.setDisable(p8e);
		anteInput2.setDisable(p9e);
		pairPlusInput2.setDisable(p10e);
	} // End of disableElems

	// cardPref:
	// Helper method to help set ImageView preferences
	private void cardPref(ImageView card1, ImageView card2, ImageView card3) {
		card1.setFitHeight(250);
		card1.setFitWidth(100);
		card1.setPreserveRatio(true);
		card2.setFitHeight(250);
		card2.setFitWidth(100);
		card2.setPreserveRatio(true);
		card3.setFitHeight(250);
		card3.setFitWidth(100);
		card3.setPreserveRatio(true);
	} // End of cardPref
}