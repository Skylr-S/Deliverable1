/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class that models a Blackjack game.
 */
public class Blackjack extends Game {

    private final int MAX_PLAYERS = 7;
    private final int MAX_SCORE = 21;
    private final int DEALER_MIN_SCORE = 17;
    private GroupOfCards deck;
    private ArrayList<Player> players;
    private Player dealer;
    private boolean gameStarted;

    public Blackjack() {
        super("Blackjack");
        deck = new GroupOfCards(52);
        players = new ArrayList<>();
        dealer = new Player("Dealer") {
            @Override
            public void play() {
                while (getScore() < DEALER_MIN_SCORE) {
                    hit(deck);
                }
            }
        };
        gameStarted = false;
    }

    // Getter for players
    public ArrayList<Player> getPlayers() {
        return players;
    }

    // Getter for MAX_PLAYERS
    public int getMaxPlayers() {
        return MAX_PLAYERS;
    }

    // Method to start the game
    public void start() {
        gameStarted = true;
    }

    // Method to check if the game has started
    public boolean isGameStarted() {
        return gameStarted;
    }

    /**
     * Adds a player to the game.
     *
     * @param name the name of the player to add.
     */
    public void addPlayer(String name, GroupOfCards deck) {
        if (players.size() < MAX_PLAYERS) {
            players.add(new Player(name) {
                @Override
                public void play() {
                    Scanner input = new Scanner(System.in);
                    String choice;
                    do {
                        System.out.println("Do you want to hit or stand? (h/s)");
                        choice = input.nextLine().toLowerCase();
                        if (choice.equals("h")) {
                            hit(deck);
                        }
                    } while (!choice.equals("s") && getScore() < MAX_SCORE);
                }
            });
        } else {
            System.out.println("The maximum number of players has been reached.");
        }
    }

    /**
     * Deals two cards to each player and the dealer.
     */
    public void deal() {
        deck.shuffle();
        for (Player player : players) {
            player.clearHand();
            player.addCard(deck.draw());
            player.addCard(deck.draw());
        }
        dealer.clearHand();
        dealer.addCard(deck.draw());
        dealer.addCard(deck.draw());
    }

    /**
     * Calculates the score of a player's hand.
     *
     * @param player the player whose score to calculate.
     * @return the score of the player's hand.
     */
    public int calculateScore(Player player) {
        int score = 0;
        boolean hasAce = false;
        for (BlackjackCard card : player.getHand()) {
            int value = card.getValue();
            if (value == 1) {
                hasAce = true;
            }
            score += value;
        }
        if (hasAce && score + 10 <= MAX_SCORE) {
            score += 10;
        }
        return score;
    }

    /**
     * Allows each player to take their turn and then the dealer takes their turn.
     */
    public void play() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Blackjack!");
        System.out.println("How many players are there?");
        int numPlayers = input.nextInt();
        input.nextLine();
        for (int i = 1; i <= numPlayers; i++) {
            System.out.println("Enter the name of player " + i + ":");
            addPlayer(input.nextLine(), deck);
        }
        deal();
        for (Player player : players) {
            System.out.println(player.getName() + "'s turn:");
            player.play();
            System.out.println(player.getName() + " has " + calculateScore(player));
            System.out.println();
        }
        System.out.println("Dealer's turn:");
        dealer.play();
        System.out.println(dealer.getName() + " has " + calculateScore(dealer));
        System.out.println();
    }

    /**
     * Determines and displays the winner(s) of the game.
     */
    public void declareWinner() {
        int dealerScore = calculateScore(dealer);
        for (Player player : players) {
            int playerScore = calculateScore(player);
            if (playerScore > MAX_SCORE || (dealerScore <= MAX_SCORE && dealerScore > playerScore)) {
                System.out.println(player.getName() + " loses.");
            } else if (dealerScore > MAX_SCORE || dealerScore < playerScore) {
                System.out.println(player.getName() + " wins!");
            } else {
                System.out.println(player.getName() + " ties with the dealer.");
            }
        }
    }

    public static void main(String[] args) {
        Blackjack blackJack = new Blackjack(); // Remove the name parameter
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            blackJack.play();
            blackJack.declareWinner();
            System.out.print("Play again? (y/n): ");
            String input = scanner.nextLine().toLowerCase();
            playAgain = input.equals("y") || input.equals("yes");
        }

        scanner.close(); // Close the scanner to avoid resource leak
    }

}
