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

import project.configuration.ConfigurationReader;
import project.model.countries.Country;
import project.constants.Constants;

/**
 * @author kolkol
 * Scorecalc has the task to calculates scores and keep track of the timer
 */
public class ScoreCalc {

    /**
     * Empty constructor
     */
    public ScoreCalc() {
    }

    /**
     * calculateScore calculates the score with the given information
     * Takes the timeleft variable into the calculation
     *
     * @param country The asked country object
     * @param answer The user provided answer
     * @param d The used difficulty
     * @return the amount of points awared
     */
    public int calculateScore(Country country, String answer, Constants.Difficulty d, int timeLeft) {
        String[] answers = null;
        int retval = 0;

        switch (d) {
            case EASY:
                answers = country.getEasyAnswers();
                retval = calculateSpecificScore(answers, answer, 1, 100 * timeLeft/ConfigurationReader.getIntegerSetting("TIME_OUT_EASY"));
                break;
            case MEDIUM:
                answers = country.getMediumAnswers();
                retval = calculateSpecificScore(answers, answer, 4, 100 * timeLeft/ConfigurationReader.getIntegerSetting("TIME_OUT_MEDIUM"));
                break;
            case HARD:
                answers = country.getHardAnswers();
                retval = calculateSpecificScore(answers, answer, 6, 100 * timeLeft/ConfigurationReader.getIntegerSetting("TIME_OUT_HARD"));
                break;
        }

        return retval;
    }

    /**
     * Used by above mwthod to
     * Check the answer and calculates the score with a give multiple
     * @param answers the correct answers
     * @param userAnswer the answer provided by the user
     * @param multiplicity a multiplicity
     * @return
     */
    private int calculateSpecificScore(String[] answers, String userAnswer, int multiplicity, int time_left) {
        if (time_left < 0) {
            return -multiplicity;
        }
        int retval = 0;
        userAnswer = userAnswer.toLowerCase();

        for (int i = 0; i < answers.length; i++) {
            if (answers[i].toLowerCase().equals(userAnswer)) {
                retval = multiplicity * time_left;
                break;
            } else {
                retval = -multiplicity;
            }
        }
        //TODO: ta bort println
        System.out.println(multiplicity);
        System.out.println(retval);
        System.out.println(userAnswer);
        System.out.println(answers[0]);
        return retval;
    }
}
