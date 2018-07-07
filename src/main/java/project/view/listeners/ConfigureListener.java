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
package project.view.listeners;

import project.configuration.ConfigurationReader;
import project.model.Highscore;
import project.view.MainJFrame;
import project.view.SettingsFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles the buttonlisteners for the <code>SettingsFrame</code>.
 *
 * @author Roman, Johan Berg, Jocke
 */
public class ConfigureListener implements ActionListener {

    private SettingsFrame frame;

    /**
     * Stores the SettingsFrame in a private variable.
     * @param frame The <code>SettingsFrame</code>, which holds all the buttons.
     */
    public ConfigureListener(SettingsFrame frame) {
        this.frame = frame;
    }

    /**
     * Handles events of the different buttons. The "Tillämpa" button appies the new settings.
     * The "Återställ" resets the settings to default values. The "Radera highscore" button
     * erases all the highscores.
     * @param ae The ActionEvent.
     */
    public void actionPerformed(ActionEvent ae) {

        JButton source = (JButton) ae.getSource();
        ConfigurationReader conf = new ConfigurationReader();
        Highscore highscore = new Highscore("africa");

        if (source.getText().equals("Tillämpa")) {
            ConfigurationReader.setSetting("TIME_OUT_EASY", frame.getEasyTime());
            ConfigurationReader.setSetting("TIME_OUT_MEDIUM", frame.getMediumTime());
            ConfigurationReader.setSetting("TIME_OUT_HARD", frame.getHardTime());
            ConfigurationReader.setSetting("WINDOW_SMALL_WIDTH", frame.getSmallWindowWidth());
            ConfigurationReader.setSetting("WINDOW_SMALL_HEIGHT", frame.getSmallWindowHeight());
            ConfigurationReader.setSetting("WINDOW_WIDTH", frame.getBigWindowWidth());
            ConfigurationReader.setSetting("WINDOW_HEIGHT", frame.getBigWindowHeight());
            ConfigurationReader.setSetting("BACKGROUND_COLOR", frame.getBackgroundColor());
            ConfigurationReader.saveConfigurationFile();
        } else if (source.getText().equals("Återställ")) {
            ConfigurationReader.setSetting("TIME_OUT_EASY", 30);
            ConfigurationReader.setSetting("TIME_OUT_MEDIUM", 20);
            ConfigurationReader.setSetting("TIME_OUT_HARD", 10);
            ConfigurationReader.setSetting("WINDOW_SMALL_WIDTH", 450);
            ConfigurationReader.setSetting("WINDOW_SMALL_HEIGHT", 425);
            ConfigurationReader.setSetting("WINDOW_WIDTH", 800);
            ConfigurationReader.setSetting("WINDOW_HEIGHT", 600);
            ConfigurationReader.setSetting("BACKGROUND_COLOR", "99cdff");
            ConfigurationReader.saveConfigurationFile();
        } else if (source.getText().equals("Radera highscore")) {
            try {
                int reset = JOptionPane.showConfirmDialog(null,
                        "Vill du verkligen radera alla highscores?", "Radera highscores?",
                        JOptionPane.YES_NO_OPTION);
                if (reset == 0) {
                    highscore.resetHighscores();
                    highscore.setContinent("asia");
                    highscore.resetHighscores();
                    highscore.setContinent("europe");
                    highscore.resetHighscores();
                    highscore.setContinent("northamerica");
                    highscore.resetHighscores();
                    highscore.setContinent("oceania");
                    highscore.resetHighscores();
                    highscore.setContinent("southamerica");
                    highscore.resetHighscores();
                    highscore.setContinent("world");
                    highscore.resetHighscores();
                    return;
                } else {
                    return;
                }
            } catch (IOException ex) {
                Logger.getLogger(ConfigureListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        frame.setVisible(false);
        MainJFrame.setButtonsEnabled(true);
        MainJFrame.setMenuEnabled(true);
    }
}
