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

package project.controller;

import project.constants.Constants;
import project.exceptions.IncompleteDataForCreationOfGameroundDataException;
import project.exceptions.NoMoreCountriesLeftException;
import project.model.Continent;
import project.model.Gameround;
import project.model.Highscore;
import project.model.Player;
import project.model.PlayerDTO;
import project.model.ScoreCalc;
import project.model.continents.Oceania;
import project.model.continents.World;
import project.model.countries.Country;
import project.view.MapFrame;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * The controller of this program manages communication between the view and model
 *
 * The controller also has the task of collection and creating the gameround object.
 */
public class Controller {

    private Gameround gameround;
    private List<Player> players;
    private ScoreCalc scorecalc;
    private List<NextCountryObserver> listeners;
    private Timer timer;
    private TimeOut time_out;

    /**
     * Standard constructor
     */
    public Controller() {
        listeners = new ArrayList<>();
    }

    /**
     * Selects the continent to be used in the new gameround
     *
     * @param continent The name of the continent given in swedish with a capital
     * first letter
     */
    private Continent selectContinent(String continent) {
        
        Continent tempcontinent = null;

        if (continent.equals("Oceanien")) {
            tempcontinent = new Oceania();
        } else if (continent.equals("Världen")) {
            tempcontinent = new World();
        }
        return tempcontinent;
    }

    /**
     * starts a new game if enough data have been entered into the game, otherwise
     * throws exception
     * @throws IncompleteDataForCreationOfGameroundDataException
     */
    public void startGame(String continent,List<String>players,List<Color>colors,Constants.Difficulty d) throws IncompleteDataForCreationOfGameroundDataException {
        if(players.size() > 0)
        {            
            List<Player> temp = new ArrayList<Player>();
            for (int i=0;i < players.size();i++)
            {
                temp.add(new Player(players.get(i),colors.get(i)));
            }
            this.gameround = new Gameround(selectContinent(continent),temp,d);
            this.scorecalc = new ScoreCalc();
            
            try {
                MapFrame mf = new MapFrame(this);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }

            if(timer != null){
                timer.cancel();
            }

            timer = new Timer();

            runGame();
        }
        else
        {
            throw new IncompleteDataForCreationOfGameroundDataException();
        }
    }

    /**
     * This method is called when a turn in the game has finished. It generates
     * the next country. If there is no more countries left it ends the game. It
     * also resets the game timer.
     */
    public void runGame() {       
        Country c = null;
        try {
            c = this.getNextCountry();
            this.updateNextCountry();
            time_out = new TimeOut(gameround.getDifficulty(), this);
            timer.scheduleAtFixedRate(time_out, 0, 1000);
        } catch (NoMoreCountriesLeftException e) {
            endGame();
        }
    }

    /**
     * Ends the game and writes the scores to the high score list.
     */
    private void endGame()
    {        
        for (NextCountryObserver listener : listeners) {
            listener.disable();
        }
        this.players = null;
        //new EndFrame(this);
        Highscore highscoreWriter = new Highscore(this.gameround.getContinent().getName());
        try {
            highscoreWriter.updateHighscore(this.getAllPlayers());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Returns the current <code>Country</code>.
     * @return A <code>Country</code> instance.
     */
    public Country getCurrentCountry() {
        return this.gameround.getCurrentCountry();
    }

    /**
     * Generates the next <code>Country</code> in the game and returns it.
     * @return A <code>Country</code> instance.
     * @throws NoMoreCountriesLeftException If there is no more countries in the
     * game.
     */
    public Country getNextCountry() throws NoMoreCountriesLeftException {
        Country country = null;
        country = this.gameround.getNextCountry();
        return country;
    }

    /**
     * Returns the current player in the game.
     * @return A <code>Player</code> instance.
     */
    public Player getCurrentPlayer() {
        return this.gameround.getCurrentPlayer();
    }

    /**
     * Returns a DTO of the current player in the game.
     * @return A <code>PlayerDTO</code> instance.
     */
    public PlayerDTO getCurrentPlayerDTO() {
        return this.gameround.getCurrentPlayerDTO();
    }

    /**
     * Generates the next player in the game and returns its instance.
     * @return A <code>Player</code> instance.
     */
    public Player getNextPlayer() {
        return this.gameround.getNextPlayer();
    }

    /**
     * Returns the path to the continent image.
     * @return A <code>String</code>.
     */
    public String getContinentImagePath() {
        return this.gameround.getContinentImagePath();
    }

    /**
     * calculate score calculates the score of a given answer, the method
     * also updates the score variable in the corresponding player object.
     *
     * @param answer an answer from the view
     */
    public int calculateScore(String answer) {
        int score = this.scorecalc.calculateScore(getCurrentCountry(), answer, gameround.getDifficulty(), time_out.getTime());

        //System.out.println("time: " + time_out.getTime() + ", score: " + score);

        this.gameround.getCurrentPlayer().addPoints(score);
        updateOldCountry(score >= 0);

        if (score > 0) {
            this.gameround.getCurrentPlayer().addCorrectAnswer(1);
        } else {
            this.gameround.getCurrentPlayer().addIncorrectAnswer(1);
        }
        updatePlayers();
        return score;
    }

    /**
     * Returns all players as thier DTO counterparts
     * @return A list of PlayerDTO objects
     */
    public List<PlayerDTO> getAllPlayers() {
        return this.gameround.getAllPlayers();
    }

    /**
     * Adds a <code>NextCountryObserver</code> to the <code>List<NextCountryObserver></code>
     * instance <code>listeners</code>. This means that in a game
     * this listener will update the <code>MapFrame</code> instance for the game
     * is updated.
     * @param nextCountryListener The <code>NextCountryObserver</code> instance
     *
     */
    public void addNewCountryListener(NextCountryObserver nextCountryListener) {
        listeners.add(nextCountryListener);
    }

    /**
     * Generates the next country in the game.
     */
    public void updateNextCountry() {
        Country country = getCurrentCountry();
        Color color = getCurrentPlayer().getColor();
        for (NextCountryObserver listener : listeners) {
            listener.nextCountry(country, color);
        }
    }

    /**
     * Updates the color of last country, this will give a different color to
     * the country depending on whether the answer was right or wrong.
     * @param right If the answer was right; true. Else false.
     */
    public void updateOldCountry(boolean right) {
        Country country = getCurrentCountry();
        for (NextCountryObserver listener : listeners) {
            listener.oldCountry(country, right);
        }
    }

    /**
     * Returns a list of <code>PlayerDTO</code> instances from the high score list.
     * @param continent Which high score to return.
     * @return A <code>List<PlayerDTO></code> instance as the high score.
     * @throws IOException Thrown if there is a file error.
     * @throws ClassNotFoundException Thrown if there is a file reading error.
     */
    public List<PlayerDTO> readHighscores(String continent) throws IOException, ClassNotFoundException
    {
       Highscore reader = new Highscore(continent);
       return reader.readHighScores();
    }

    /**
     * Update the player labels in the <code>MapFrame</code> instances.
     */
    private void updatePlayers() {
        for (NextCountryObserver listener : listeners) {
            listener.updatePlayers();
        }
    }

    /**
     * Update the timer in the <code>MapFrame</code> instances.
     * @param time The time to be written in the <code>MapFrame</code> instances.
     */
    void updateTime(int time) {
        for (NextCountryObserver listener : listeners) {
            listener.updateTime(time);
        }
    }

    /**
     * Generates a response to the user based on if the users answer was correct
     * or not.
     * @param respons A <code>String</code> with the response.
     * @param correct True if the answer was corrent. False if not.
     */
    private void showResponse(String respons, boolean correct) {
        for (NextCountryObserver listener : listeners) {
            listener.showRespons(respons, correct);
        }
    }

    /**
     * Checks if the answer is correct or not and gives the player a score based
     * on it. A response to the user is also sent.
     * @param answer A <code>Strin</code> with the users answer.
     */
    public void checkAnswer(String answer) {
        time_out.cancel();
        int points = calculateScore(answer);
        String respons = "RÄTT: +" + points;
        boolean correct = true;
        if (points <= 0) {
            respons = getCurrentCountry().getHardAnswers()[0] +": " + points;
            correct = false;
        }
        showResponse(respons, correct);

        getNextPlayer();
        runGame();
    }
}
