/**
 * Programvaruteknik KTH
 * VT 2011
 * Group: Pear Softworks
 * <p>
 * Licensed under zlib/libpng
 * <p>
 * Copyright (c) <2011> <Pear Softworks>
 * <p>
 * This software is provided 'as-is', without any express or implied
 * warranty. In no event will the authors be held liable for any damages
 * arising from the use of this software.
 * <p>
 * Permission is granted to anyone to use this software for any purpose,
 * including commercial applications, and to alter it and redistribute it
 * freely, subject to the following restrictions:
 * <p>
 * 1. The origin of this software must not be misrepresented; you must not
 * claim that you wrote the original software. If you use this software
 * in a product, an acknowledgment in the product documentation would be
 * appreciated but is not required.
 * <p>
 * 2. Altered source versions must be plainly marked as such, and must not be
 * misrepresented as being the original software.
 * <p>
 * 3. This notice may not be removed or altered from any source
 * distribution.
 */

package project.model;

import project.model.countries.Country;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a superclass that every continent should extend,
 * It defines data structures and methods for entering data into the data structures
 *
 * @author Kim Torgersen
 */
public class Continent {
    protected String name;
    private List<Country> countries;
    private String imagePath;

    /**
     * Constructor of the continent.
     */
    public Continent() {
        this.countries = new ArrayList<Country>();
    }

    /**
     * Returns all countries in the continent.
     * @return All countries in the continent.
     */
    public List<Country> getAllCountries() {

        return countries;
    }

    /**
     * Adds a country to the continent.
     * @param country Country to add to the continent.
     */
    protected void addCountry(Country country) {
        this.countries.add(country);
    }

    /**
     * Sets image path of the continent.
     * @param path Path of the continent image to be set.
     */
    protected void setImagePath(String path) {
        this.imagePath = path;
    }

    /**
     * Returns image path of the continent.
     * @return Image path of the continent.
     */
    public String getImagePath() {
        return this.imagePath;
    }

    /**
     * Returns continent name.
     * @return Name of the continent.
     */
    public String getName() {
        return name;
    }

}
