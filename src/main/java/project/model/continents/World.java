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

package project.model.continents;

import project.model.Continent;
import project.model.countries.oceania.Australia;
import project.model.countries.oceania.NewZealand;
import project.model.countries.oceania.PapuaNewGuinea;


/**
 * @author kolkol
 */
public class World extends Continent {

    /**
     * Creates the continent "World", using the worldmap and containing all countries
     */
    public World() {
        super.name = "World";
        this.setImagePath("world.png");

        //Oceania
        this.addCountry(new Australia());
        this.addCountry(new NewZealand());
        this.addCountry(new PapuaNewGuinea());
    }

}
