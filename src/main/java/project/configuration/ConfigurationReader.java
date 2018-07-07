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

package project.configuration;

import java.io.IOException;

/**
 * This is a static class to be used for reading values from the settings.conf
 * file.
 * @author Oskar
 */
public class ConfigurationReader {
    static ConfigurationManager manager = null;

    /**
     * Initializes a <code>ConfigurationManager</code>.
     */
    private static void initManager() {
        if (manager == null) {
            try {
                manager = new ConfigurationManager();
            } catch (IOException ex) {
                //XXX
                System.err.println("getSetting failed");
            }
        }
    }

    /**
     * Returns a setting by its key. For example if the user wants the setting
     * for <code>TIME_OUT_EASY</code> it uses <code>TIME_OUT_EASY</code> as the
     * key.
     * @param key A <code>String</code> with the setting name.
     * @return A <code>String</code> with the setting.
     */
    public static String getSetting(String key) {
        initManager();
        return manager.getSetting(key);
    }

    /**
     * Returns a setting by its key. For example if the user wants the setting
     * for <code>TIME_OUT_EASY</code> it uses <code>TIME_OUT_EASY</code> as the
     * key.
     * @param key A <code>String</code> with the setting name.
     * @return An int with the setting.
     */
    public static int getIntegerSetting(String key) {
        initManager();
        return manager.getIntegerSetting(key);
    }

    /**
     * Sets a setting by its key. For example if the user wants to set the
     * <code>TIME_OUT_EASY</code> setting it uses <code>TIME_OUT_EASY</code> as the
     * key and an int with the new setting.
     * @param key A <code>String</code> with the setting name.
     * @param value An int with the setting.
     */
    public static void setSetting(String string, int value) {
        initManager();
        manager.setSetting(string, value);
    }

    /**
     * Sets a setting by its key. For example if the user wants to set the
     * <code>TIME_OUT_EASY</code> setting it uses <code>TIME_OUT_EASY</code> as the
     * key and a <code>String</code> with the new setting.
     * @param key A <code>String</code> with the setting name.
     * @param value A <code>String</code> with the setting.
     */
    public static void setSetting(String string, String value) {
        initManager();
        manager.setSetting(string, value);
    }

    /**
     * This method saves all the settings to the settings.conf file.
     * @throws IOException Throws an <code>IOException</code> from
     * <code>BufferedWriter</code> which could happen if the file do not exist.
     */
    public static void saveConfigurationFile() {
        initManager();
        try {
            manager.saveConfigurationFile();
        } catch (IOException ex) {
            //XXX
            System.err.println("getSetting failed");
        }
    }
}
