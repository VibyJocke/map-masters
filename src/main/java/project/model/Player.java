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

import java.awt.*;

/**
 * The Player object.
 * @author Kim Torgersen
 */
public class Player {
    String  name;
    Color   color;
    int     points;
    int     nrOfCorrectAnswers;
    int     nrOfIncorrectAnswers;

    /**
     * Constructor of the player object.
     * @param name The player's name.
     * @param color The player's color.
     */
    public Player(String name, Color color){
        this.name = name;
        this.color = color;
        this.nrOfCorrectAnswers = 0;
        this.nrOfIncorrectAnswers = 0;
        this.points = 0;
    }

    /**
     * Sets a new value to player's points.
     * @param points The new value to be set.
     */
    public void setPoints(int points){
        this.points = points;
    }

    /**
     * Returns the player's points.
     * @return The player's points.
     */
    public int getPoints(){
        return points;
    }

    /**
     * Sets a new value to the player's number of correct answers.
     * @param nrOfCorrectAnswers The new value to be set.
     */
    public void setNrOfCorrectAnswers(int nrOfCorrectAnswers){
        this.nrOfCorrectAnswers = nrOfCorrectAnswers;
    }

    /**
     * Returns the player's number of correct answers.
     * @return The player's number of correct answers.
     */
    public int getNrOfCorrectAnswers(){
        return nrOfCorrectAnswers;
    }

    /**
     * Set a new value to the player's number of incorrect answers.
     * @param nrOfIncorrectAnswers The new value to be set.
     */
    public void setNrOfIncorrectAnswers(int nrOfIncorrectAnswers){
        this.nrOfIncorrectAnswers = nrOfIncorrectAnswers;
    }

    /**
     * Returns the player's number of incorrect answers.
     * @return the player's number of incorrect answers.
     */
    public int getNrOfIncorrectAnswers(){
        return this.nrOfIncorrectAnswers;
    }

    /**
     * Adds points.
     * @param points How many points to add.
     */
    public void addPoints(int points){
        this.points = this.points + points;
    }

    /**
     * Adds a value to correct answers.
     * @param answer Value of to add too correct answers.
     */
    public void addCorrectAnswer(int answer){
        this.nrOfCorrectAnswers = this.nrOfCorrectAnswers + answer;
    }

    /**
     * Adds a value to incorrect answers.
     * @param answer Value of to add too incorrect answers.
     */
    public void addIncorrectAnswer(int answer){
        this.nrOfIncorrectAnswers = this.nrOfIncorrectAnswers + answer;
    }

    /**
     * Returns player color.
     * @return Player color.
     */
    public Color getColor() {
        return this.color;
    }
    /**
     * Returns player name.
     * @return Player name.
     */
    public String getName() {
        return this.name;
    }

}
