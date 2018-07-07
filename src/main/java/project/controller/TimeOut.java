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

package project.controller;

import project.configuration.ConfigurationReader;
import project.constants.Constants;

import java.util.TimerTask;

/**
 * This class manage to timer in the game.
 * @author Andreas Norberg
 */
class TimeOut extends TimerTask {
    private Controller controller;
    private int time;

    /**
     * Creates a new <code>TimeOut</code> instance.
     * @param diff The game difficulty
     * @param controller The <code>Controller</code> instance.
     */
    public TimeOut(Constants.Difficulty diff, Controller controller) {
        this.controller = controller;
        switch (diff) {
            case EASY:
                time = ConfigurationReader.getIntegerSetting("TIME_OUT_EASY") + 1;
                break;
            case MEDIUM:
                time = ConfigurationReader.getIntegerSetting("TIME_OUT_MEDIUM") + 1;
                break;
            case HARD:
                time = ConfigurationReader.getIntegerSetting("TIME_OUT_HARD") + 1;
                break;
        }
    }

    /**
     * Returns the time.
     * @return An int representing the time.
     */
    public int getTime() {
        return time;
    }

    /**
     * The <code>TimeOut</code>s task.
     */
    @Override
    public void run() {
        if (--time >= 0) {
            controller.updateTime(time);
        } else {
            this.cancel();
            controller.checkAnswer("");
        }
    }
}
