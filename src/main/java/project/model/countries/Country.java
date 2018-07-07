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

package project.model.countries;

/**
 * A superclass specifying country information, ALL countries shall extend this
 *
 * @author Oskar, Johan
 */
public class Country {

    private String[] easyAnswers;
    private String[] mediumAnswers;
    private String[] hardAnswers;
    private int countryColorIndex;

    /**
     * Set this country's color index
     * @param countryColorIndex A color index, as an int between 1 and 255
     */
    public void setCountryColor(int countryColorIndex) {
        this.countryColorIndex = countryColorIndex;
    }

    /**
     * Set the easyAnswers of a country
     *
     * @param easyAnswers an array of strings containing the answers accepted
     * on the easy difficulty setting
     */
    public void setEasyAnswers(String[] easyAnswers) {
        this.easyAnswers = easyAnswers;
    }

    /**
     * Set the hardAnswers of a country
     *
     * @param hardAnswers an array of strings containing the answers accepted
     * on the hard difficulty setting
     */
    public void setHardAnswers(String[] hardAnswers) {
        this.hardAnswers = hardAnswers;
    }

    /**
     * Set the mediumAnswers of a country
     *
     * @param mediumAnswers an array of strings containing the answers accepted
     * on the medium difficulty setting
     */
    public void setMediumAnswers(String[] mediumAnswers) {
        this.mediumAnswers = mediumAnswers;
    }

    /**
     *
     * @return An array of strings containing the answers accepted on the easy
     * difficulty setting.
     */
    public String[] getEasyAnswers() {
        return easyAnswers;
    }

    /**
     *
     * @return An array of strings containing the answers accepted on the hard
     * difficulty setting.
     */
    public String[] getHardAnswers() {
        return hardAnswers;
    }

    /**
     *
     * @return An array of strings containing the answers accepted on the medium
     * difficulty setting.
     */
    public String[] getMediumAnswers() {
        return mediumAnswers;
    }

    /**
     *
     * @return the unique colorIndex
     */
    public int getCountryColor() {
        return countryColorIndex;
    }

    /**
     * Creates a String representation fo this object
     * @return a String representation
     */
    public String toString()
    {
        return this.hardAnswers[0];
    }
}
