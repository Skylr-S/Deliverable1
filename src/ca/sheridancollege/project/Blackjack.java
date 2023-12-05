/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

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

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getMaxPlayers() {
        return MAX_PLAYERS;
    }

    public void start() {
        gameStarted = true;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void addPlayer(String name, GroupOfCards deck) {
        if (players.size() < MAX_PLAYERS) {
            players.add(new Player(name) {
                @Override
                public void play() {
                    Scanner input = new Scanner(System.in);
                    String choice;
                    do {
                        System.out.println(getName() + "'s hand: " + getHand());
                        System.out.println("Do you want to hit or stand? (h/s)");
                        choice = input.nextLine().toLowerCase();
                        if (choice.equals("h")) {
                            hit(deck);
                            System.out.println(getName() + " draws a card.");
                        }
                    } while (!choice.equals("s") && getScore() < MAX_SCORE);
                }
            });
        } else {
            System.out.println("The maximum number of players has been reached.");
        }
    }

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
            System.out.println(player.getName() + "'s final hand: " + player.getHand());
            System.out.println(player.getName() + "'s score: " + calculateScore(player));
            System.out.println();
        }
        System.out.println("Dealer's turn:");
        dealer.play();
        System.out.println(dealer.getName() + "'s final hand: " + dealer.getHand());
        System.out.println(dealer.getName() + "'s score: " + calculateScore(dealer));
        System.out.println();
    }
    

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
        Blackjack blackjack = new Blackjack();
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            blackjack.play();
            blackjack.declareWinner();
            System.out.print("Play again? (y/n): ");
            String input = scanner.nextLine().toLowerCase();
            playAgain = input.equals("y") || input.equals("yes");
        }

        scanner.close();
    }
}
