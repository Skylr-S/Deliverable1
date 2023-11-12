/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A concrete class that represents any grouping of cards for a Game. HINT, you might want to subclass this more than
 * once. The group of cards has a maximum size attribute which is flexible for reuse.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 */
public class GroupOfCards {

    private ArrayList<BlackjackCard> cards;
    private int size;

    public GroupOfCards(int size) {
        this.size = size;
        this.cards = new ArrayList<>();
        initializeCards();
    }

    public void addCard(BlackjackCard card) {
        cards.add(card);
    }

    public void clear() {
        cards.clear();
    }

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

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public BlackjackCard draw() {
        return cards.remove(0);
    }

    public void hit(GroupOfCards deck) {
        addCard(deck.draw());
    }

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