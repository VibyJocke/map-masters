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

package project.model.continents;

import project.model.Continent;
import project.model.countries.oceania.Australia;
import project.model.countries.oceania.NewZealand;
import project.model.countries.oceania.PapuaNewGuinea;

/**
 *
 * @author Johan Berg
 */
public class Oceania extends Continent {

    /**
     * Creates the continent Oceania, and creates the countries
     * affiliated with the continent
     */
    public Oceania() {
        super.name = "Oceania";
        this.setImagePath("oceania.png");

        this.addCountry(new Australia());
        this.addCountry(new NewZealand());
        this.addCountry(new PapuaNewGuinea());
    }

}
