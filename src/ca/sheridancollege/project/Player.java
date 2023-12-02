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
 * This class provides methods for managing a player's hand, calculating score, and taking turns in the game.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 */
public abstract class Player {

    // The name of the player
    private String name;

    // The list of cards in the player's hand
    private ArrayList<BlackjackCard> hand;

    /**
     * Constructs a player with a given name.
     *
     * @param name the name of the player.
     */
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    /**
     * Gets the name of the player.
     *
     * @return the name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Adds a card to the player's hand.
     *
     * @param card the card to add.
     */
    public void addCard(BlackjackCard card) {
        hand.add(card);
    }

    /**
     * Clears all cards from the player's hand.
     */
    public void clearHand() {
        hand.clear();
    }

    /**
     * Calculates the score of the player's hand. Adjusts for Aces if the total score is over 21.
     *
     * @return the score of the player's hand.
     */
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

    /**
     * Gets the list of cards in the player's hand.
     *
     * @return the list of cards in the hand.
     */
    public ArrayList<BlackjackCard> getHand() {
        return hand;
    }

    /**
     * Adds a card to the player's hand from a given deck.
     *
     * @param deck the deck from which to draw a card.
     */
    public void hit(GroupOfCards deck) {
        addCard(deck.draw());
    }

    /**
     * Abstract method to be implemented by specific player classes. Represents the main logic of a player's turn in the
     * game.
     */
    public abstract void play();
}
