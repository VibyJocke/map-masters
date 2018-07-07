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
package project.view;

import project.controller.NextCountryObserver;
import project.model.countries.Country;

import java.awt.*;

/**
 * An class that observs the <code>MapFrame</code>.
 * @author Andreas Norberg
 */
class MapFrameObserver implements NextCountryObserver {

    private MapFrame mapFrame;

    /**
     * Creates a new <code>MapFrameObserver</code> instance.
     * @param mapFrame Saves a <code>MapFrame</code> in a private variable.
     */
    public MapFrameObserver(MapFrame mapFrame) {
        this.mapFrame = mapFrame;
    }

    /**
     * Handles events that has to occur when changing to next country.
     * @param country The next country.
     * @param color The color.
     */
    public void nextCountry(Country country, Color color) {
        int country_index = country.getCountryColor();
        mapFrame.setCurrentCountry(country_index, color);
        mapFrame.updateCurrentPlayerLabel();
        mapFrame.answer.setEnabled(true);
        mapFrame.repaint();
    }

    /**
     * Handles events that has to occur to old countrys when moving to a new country.
     * @param country The old country.
     * @param right A boolean value.
     */
    public void oldCountry(Country country, boolean right) {
        mapFrame.resetCurrentCountry(right);
        mapFrame.repaint();
    }

    /**
     * Updates the player labels.
     */
    public void updatePlayers() {
        mapFrame.updatePlayerLabels();
    }

    /**
     * Shows the response in the textfield.
     * @param respons The response as a string.
     * @param correct A boolean value to check if the answer is correct.
     */
    public void showRespons(String respons, boolean correct) {
        mapFrame.answer.setText(respons);
        if (correct) {
            mapFrame.answer.setSelectionColor(Color.green);
        } else {
            mapFrame.answer.setSelectionColor(Color.red);
        }
        mapFrame.answer.selectAll();
    }

    /**
     * Updates the timer.
     * @param time The time as an int.
     */
    public void updateTime(int time)
    {
        mapFrame.timerField.setText(Integer.toString(time));
    }

    /**
     * Disables the textfield.
     */
    public void disable()
    {
        mapFrame.disableTextField();
    }
}
