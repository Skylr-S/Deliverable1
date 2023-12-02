/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 * The base class that models a card game. Students should create a more specific child class and implement the given
 * methods.
 *
 * This class provides a basic structure for managing players in a game.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 */
public abstract class Game {

    // The name of the game
    private String name;

    // The list of players in the game
    private ArrayList<Player> players;

    /**
     * Constructs a new game with a given name.
     *
     * @param name the name of the game.
     */
    public Game(String name) {
        this.name = name;
        this.players = new ArrayList<>();
    }

    /**
     * Gets the name of the game.
     *
     * @return the name of the game.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the list of players in the game.
     *
     * @return the list of players.
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Adds a player to the game.
     *
     * @param player the player to add.
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Removes a player from the game.
     *
     * @param player the player to remove.
     */
    public void removePlayer(Player player) {
        players.remove(player);
    }

    /**
     * Clears the list of players in the game.
     */
    public void clearPlayers() {
        players.clear();
    }

    /**
     * Abstract method to be implemented by specific game classes. It represents the main logic of playing the game.
     */
    public abstract void play();
}
