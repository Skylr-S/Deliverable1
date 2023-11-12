/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;


import java.util.ArrayList;

public class Blackjack extends Game {
    private GroupOfCards deck;
    private ArrayList<Player> players;
    private Player dealer;

    public Blackjack() {
        super("Blackjack");
        this.deck = new GroupOfCards(52);
        this.players = new ArrayList<>();
        this.dealer = new Player("Dealer") { // This might need to be adjusted based on the Player class's structure.
            @Override
            public void play() {
                // Logic for the dealer's play
            }
        };
    }

    public void addPlayer(String name, GroupOfCards deck) {
        // Logic to add a player
    }

    public void deal() {
        // Logic to deal cards to players
    }

    public int calculateScore(Player player) {
        // Logic to calculate a player's score
        return 0;
    }

    public void play() {
        // Logic to handle the game play
    }

    public void declareWinner() {
        // Logic to determine the winner
    }
}