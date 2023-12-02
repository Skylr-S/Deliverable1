/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A concrete class that represents any grouping of cards for a Game. This class provides functionality for managing a
 * group of cards, such as shuffling, drawing, and calculating total value.
 *
 * HINT: You might want to subclass this more than once. The group of cards has a maximum size attribute which is
 * flexible for reuse.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 */
public class GroupOfCards {

    // The list of cards in the group
    private ArrayList<BlackjackCard> cards;

    // The maximum size of the group
    private int size;

    /**
     * Constructs a group of cards with a given size.
     *
     * @param size the maximum size of the group.
     */
    public GroupOfCards(int size) {
        this.size = size;
        this.cards = new ArrayList<>();
        initializeCards();
    }

    /**
     * Adds a card to the group.
     *
     * @param card the card to add.
     */
    public void addCard(BlackjackCard card) {
        cards.add(card);
    }

    /**
     * Clears all cards from the group.
     */
    public void clear() {
        cards.clear();
    }

    /**
     * Calculates the total value of the cards in the group. Adjusts for Aces if the total value is over 21.
     *
     * @return the total value of the cards.
     */
    public int getTotalValue() {
        int totalValue = 0;
        int numAces = 0;
        for (BlackjackCard card : cards) {
            if (card.getRank().equals("Ace")) {
                numAces++;
            }
            totalValue += card.getValue();
        }
        while (totalValue > 21 && numAces > 0) {
            totalValue -= 10;
            numAces--;
        }
        return totalValue;
    }

    /**
     * Shuffles the cards in the group.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Draws a card from the group.
     *
     * @return the card drawn from the group.
     */
    public BlackjackCard draw() {
        return cards.remove(0);
    }

    /**
     * Adds a card to the group from a given deck.
     *
     * @param deck the deck from which to draw a card.
     */
    public void hit(GroupOfCards deck) {
        addCard(deck.draw());
    }

    /**
     * Initializes the cards in the group with standard playing cards.
     */
    private void initializeCards() {
        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new BlackjackCard(rank, suit));
            }
        }
    }
}
