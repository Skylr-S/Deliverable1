/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 * A class that models each Player in the game. Players have an identifier, which should be unique.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 */
public abstract class Player {

    private String name;
    private ArrayList<BlackjackCard> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addCard(BlackjackCard card) {
        hand.add(card);
    }

    public void clearHand() {
        hand.clear();
    }

    public int getScore() {
        int score = 0;
        int numAces = 0;
        for (BlackjackCard card : hand) {
            if (card.getRank().equals("Ace")) {
                numAces++;
            }
            score += card.getValue();
        }
        while (score > 21 && numAces > 0) {
            score -= 10;
            numAces--;
        }
        return score;
    }

    public ArrayList<BlackjackCard> getHand() {
        return hand;
    }

    public void hit(GroupOfCards deck) {
        addCard(deck.draw());
    }

    public abstract void play();

}