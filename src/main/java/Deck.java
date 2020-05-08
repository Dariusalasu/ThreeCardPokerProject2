import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Deck extends ArrayList<Card> {
    private char characters[] = {'C', 'D', 'S', 'H'};

    Deck() {
        for(char c : characters) {
            for(int v = 2; v <= 14; v++) {
                this.add(new Card(c, v));
            }
        }
        Collections.shuffle(this);
    } // End of constructor

    public void newDeck() {
        this.clear();
        for(char c : characters) {
            for(int v = 2; v <= 14; v++) {
                this.add(new Card(c, v));
            }
        }
        Collections.shuffle(this);
    }
}
