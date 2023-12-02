/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a deck of cards used in the game of Blackjack.
 */
public class Deck {

    // The list of cards in the deck
    private ArrayList<BlackjackCard> cards;

    /**
     * Constructs a new deck by initializing it with standard playing cards.
     */
    public Deck() {
        this.cards = new ArrayList<>();
        // Define standard suits and ranks for a deck of cards
        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

        // Create cards for each combination of suit and rank
        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new BlackjackCard(rank, suit));
            }
        }
    }

    /**
     * Shuffles the cards in the deck.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Draws a card from the deck.
     *
     * @return the card drawn from the deck.
     */
    public BlackjackCard draw() {
        // Remove and return the first card in the deck
        return cards.remove(0);
    }
}
