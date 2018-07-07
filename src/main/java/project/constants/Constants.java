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

package project.constants;

/**
 * This class contains a static enum of constants.
 * @author Johans
 */
public class Constants {

    // För ren data som ovanstående: lägg istället till det till settings.conf i com.lahtinen.app.mapmaster.resources
    // Använd sen ConfigurationReader.getSetting("namn_på_variabeln") eller 
    // ConfigurationReader.getIntegerSetting("namn_på_variabeln") för att hämta värdet
    // T ex: lägg till raden WINDOW_WIDTH=800, och använd sedan ConfigurationReader.getIntegerSetting("WINDOW_WIDTH") så returneras 800.
    public static enum Difficulty {

        EASY,
        MEDIUM,
        HARD
    }
}
