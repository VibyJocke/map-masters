/**
 * Programvaruteknik KTH
 * VT 2011
 * Group: Pear Softworks
 *
 * Licensed under zlib/libpng
 *
 * Copyright (c) <2011> <Pear Softworks>
 *
 * This software is provided 'as-is', without any express or implied
 * warranty. In no event will the authors be held liable for any damages
 * arising from the use of this software.
 *
 * Permission is granted to anyone to use this software for any purpose,
 * including commercial applications, and to alter it and redistribute it
 * freely, subject to the following restrictions:

 * 1. The origin of this software must not be misrepresented; you must not
 * claim that you wrote the original software. If you use this software
 * in a product, an acknowledgment in the product documentation would be
 * appreciated but is not required.

 * 2. Altered source versions must be plainly marked as such, and must not be
 * misrepresented as being the original software.

 * 3. This notice may not be removed or altered from any source
 * distribution.
 *
 */

package project.model;

import project.constants.Constants;
import project.exceptions.NoMoreCountriesLeftException;
import project.model.countries.Country;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 *
 * @author Kim Torgersen
 */
public class Gameround {

    private Continent continent;
    private List<Player> players;
    //private List askedCountries;
    private Queue<Country> unaskedCountries;
    private Player currentPlayer;
    private List<Country> countries;
    private Country currentCountry;
    private Constants.Difficulty diff;

    /**
     * Constructor.
     * @param continent Continent
     * @param players Players
     * @param d Difficulty
     */
    public Gameround(Continent continent, List<Player> players, Constants.Difficulty d) {
        this.continent = continent;
        this.players = players;
        this.countries = continent.getAllCountries();
        this.unaskedCountries = new LinkedList<Country>();
        this.unaskedCountries.addAll(countries); //TODO randomize
        Collections.shuffle((List)this.unaskedCountries);   // TODO: kanske fixa cast

        this.currentPlayer = players.get(0);
        this.diff = d;
    }

    /**
     * Returns a random country that has not yet been asked. By taking a random
     * number and testing if that country already has been asked for.
     * @return A new country.
     */
    public Country getNextCountry() throws NoMoreCountriesLeftException {
        try {
            this.currentCountry = this.unaskedCountries.remove();
        } catch (NoSuchElementException e) {
            throw new NoMoreCountriesLeftException();
        }
        return this.currentCountry;

    }

    /**
     * Picks out the next player, in order. If last player, then starts again
     * with the first one.
     * @return Next player.
     */
    public Player getNextPlayer() {
        int nextPlayer = players.indexOf(currentPlayer);

        nextPlayer++;

        if (nextPlayer >= players.size()) {
            nextPlayer = 0;
        }

        currentPlayer = (Player) players.get(nextPlayer);

        return currentPlayer;
    }

    /**
     * Returns current continent.
     * @return Current continent.
     */
    public Continent getContinent() {
        return continent;
    }

    /**
     * Returns current player.
     * @return Current player.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Returns current country.
     * @return Current country.
     */
    public Country getCurrentCountry() {
        return currentCountry;
    }

    public String getContinentImagePath() {
        return this.continent.getImagePath();
    }

    /**
     * Returns a list of player DTOs
     * @return A <code>List<PlayerDTO></code>
     */
    public List<PlayerDTO> getAllPlayers() {
        List<PlayerDTO> list = new ArrayList();

        for (int i = 0; i < players.size(); i++) {
            list.add(new PlayerDTO((Player) players.get(i)));
        }
        return list;
    }

    /**
     * Returns a DTO for the current player.
     * @return A DTO for the current player
     */
    public PlayerDTO getCurrentPlayerDTO() {
        return new PlayerDTO(currentPlayer);
    }

    /**
     * Returns current difficulty.
     * @return Current difficulty.
     */
    public Constants.Difficulty getDifficulty() {
        return this.diff;
    }
}
