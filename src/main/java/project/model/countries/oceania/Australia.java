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

package project.model.countries.oceania;

import project.model.countries.Country;

/**
 * This class defines the country values corresponding to australia
 * @author kolkol, Johan
 */

public class Australia extends Country {

    /**
     * Creates and sets correct values
     */
    public Australia() {
        String[] easyAnswers = {"Australia","Australien","Austrliien","Australya","Austrialia"};
        String[] mediumAnswers = {"Australia","Australien"};
        String[] hardAnswers = {"Australien"};

        int countryColorIndex = 162;

        super.setEasyAnswers(easyAnswers);
        super.setMediumAnswers(mediumAnswers);
        super.setHardAnswers(hardAnswers);
        super.setCountryColor(countryColorIndex);
    }
}