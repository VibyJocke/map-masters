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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Reads and writes to the settings.conf file.
 * @author Oskar
 */
class ConfigurationManager {
    private final File settings_file;
    private final Properties settings;

    /**
     * Creates a new <code>ConfigurationManager</code> instance and initiate a
     * new HashMap.
     */
    public ConfigurationManager() throws IOException {
        Properties defaults = new Properties();
        InputStream in = ClassLoader.getSystemResourceAsStream("settings.conf.txt");
        defaults.load(in);
        in.close();

        String jar_file = this.getClass().getProtectionDomain().getCodeSource().getLocation().getFile().replace("%20", " ");
        settings_file = new File(new File(jar_file).getParentFile(), "settings.conf");

        settings = new Properties(defaults);
        if (settings_file.exists()) {
            in = new FileInputStream(settings_file);
            settings.load(in);
            in.close();
        }
    }

    /**
     * Returns a setting by its key. For example if the user wants the setting
     * for <code>TIME_OUT_EASY</code> it uses <code>TIME_OUT_EASY</code> as the
     * key.
     * @param key A <code>String</code> with the setting name.
     * @return A <code>String</code> with the setting.
     */
    public String getSetting(String key) {
        return settings.getProperty(key);
    }

    /**
     * Returns a setting by its key. For example if the user wants the setting
     * for <code>TIME_OUT_EASY</code> it uses <code>TIME_OUT_EASY</code> as the
     * key.
     * @param key A <code>String</code> with the setting name.
     * @return An int with the setting.
     */
    public int getIntegerSetting(String key) {
        return Integer.parseInt(settings.getProperty(key));
    }

    /**
     * Sets a setting by its key. For example if the user wants to set the
     * <code>TIME_OUT_EASY</code> setting it uses <code>TIME_OUT_EASY</code> as the
     * key and a <code>String</code> with the new setting.
     * @param key A <code>String</code> with the setting name.
     * @param value A <code>String</code> with the setting.
     */
    public void setSetting(String key, String value) {
        settings.setProperty(key, value);
    }

    /**
     * Sets a setting by its key. For example if the user wants to set the
     * <code>TIME_OUT_EASY</code> setting it uses <code>TIME_OUT_EASY</code> as the
     * key and an int with the new setting.
     * @param key A <code>String</code> with the setting name.
     * @param value An int with the setting.
     */
    public void setSetting(String key, int value) {
        settings.setProperty(key, Integer.toString(value));
    }

    /**
     * This method saves all the settings to the settings.conf file.
     * @throws IOException Throws an <code>IOException</code> from
     * <code>BufferedWriter</code> which could happen if the file do not exist.
     */
    public void saveConfigurationFile() throws IOException {
        FileOutputStream out = new FileOutputStream(settings_file);
        settings.store(out, null);
    }
}
