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
import java.io.Serializable;

/**
 * The Player Data Transfer Object
 * @author Kim Torgersen
 */
public class PlayerDTO implements Serializable, Comparable<PlayerDTO> {
    String  name;
    Color   color;
    int     points;
    int     nrOfCorrectAnswers;
    int     nrOfIncorrectAnswers;

    /**
     * Constructor of the player data transfer object object.
     * @param name The Player's name.
     * @param color The Player's color.
     * @param points The Player's points.
     * @param nrOfCorrectAnswers The Player's number of correct answers.
     * @param nrOfIncorrectAnswers The Player's number of incorrect answers.
     */
    public PlayerDTO(String name, Color color, int points, 
        int nrOfCorrectAnswers, int nrOfIncorrectAnswers){
        this.name = name;
        this.color = color;
        this.points = points;
        this.nrOfCorrectAnswers = nrOfCorrectAnswers;
        this.nrOfIncorrectAnswers = nrOfIncorrectAnswers;
    }

    PlayerDTO(Player currentPlayer) {
        this.name = currentPlayer.getName();
        this.points = currentPlayer.getPoints();
        this.color = currentPlayer.getColor();
        this.nrOfCorrectAnswers = currentPlayer.getNrOfCorrectAnswers();
        this.nrOfIncorrectAnswers = currentPlayer.getNrOfIncorrectAnswers();
    }

    /**
     * Returns the player's name.
     * @return the player's name.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Returns the player's color.
     * @return the player's color.
     */
    public Color getColor(){
        return this.color;
    }

    /**
     * Returns the player's points.
     * @return the player's points.
     */
    public int getPoints(){
        return this.points;
    }

    /**
     * Returns the player's number of correct answers.
     * @return the player's number of correct answers.
     */
    public int getNrOfCorrectAnswers(){
        return this.nrOfCorrectAnswers;
    }

    /**
     * Returns the player's number of incorrect answers.
     * @return the player's number of incorrect answers.
     */
    public int getNrOfIncorrectAnswers(){
        return this.nrOfIncorrectAnswers;
    }

    /**
     * Compares 2 players.
     * @param o Player to compare player to.
     * @return 1 if player smaller than player compared to. 0 if equal.
     * -1 if bigger.
     */
    public int compareTo(PlayerDTO o) {
        if (getPoints() < o.getPoints())
            return 1;
        else if (getPoints() == o.getPoints())
            return 0;
        else
            return -1;
    }
}
