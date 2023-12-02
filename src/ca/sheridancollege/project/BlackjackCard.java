/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 * Represents a card in the game of Blackjack.
 */
public class BlackjackCard {

    // The rank of the card (e.g., Ace, King, Queen, 2, 3, ...)
    private final String rank;

    // The suit of the card (e.g., Spades, Hearts, Diamonds, Clubs)
    private final String suit;

    /**
     * Constructs a BlackjackCard with a given rank and suit.
     *
     * @param rank the rank of the card.
     * @param suit the suit of the card.
     */
    public BlackjackCard(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Gets the rank of the card.
     *
     * @return the rank of the card.
     */
    public String getRank() {
        return rank;
    }

    /**
     * Gets the suit of the card.
     *
     * @return the suit of the card.
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Gets the numerical value of the card in Blackjack. Aces have a value of 11, face cards (King, Queen, Jack) have a
     * value of 10, and numbered cards have their face value.
     *
     * @return the numerical value of the card.
     */
    public int getValue() {
        if (rank.equals("Ace")) {
            return 11;
        } else if (rank.equals("King") || rank.equals("Queen") || rank.equals("Jack")) {
            return 10;
        } else {
            // Parse the rank as an integer for numbered cards
            return Integer.parseInt(rank);
        }
    }
}
