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

import project.configuration.ConfigurationReader;
import project.view.listeners.ConfigureListener;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents a frame where the user can see and change some of the
 * program settings.
 * @author Roman
 */
public class SettingsFrame extends JFrame {

    private JTextField easyText;
    private JTextField mediumText;
    private JTextField hardText;
    private JTextField windowSmallWidthText;
    private JTextField windowSmallHeightText;
    private JTextField windowBigWidthText;
    private JTextField windowBigHeightText;
    private JTextField backgroundColorText;
    private ConfigurationReader confReader;

    /**
     * Creates a new <code>SettingsFrame</code> instance and initiates the frame
     * with size, location, title etc.
     */
    public SettingsFrame() {


        int windowWidth = ConfigurationReader.getIntegerSetting("WINDOW_SMALL_WIDTH");
        int windowHeight = ConfigurationReader.getIntegerSetting("WINDOW_SMALL_HEIGHT");

        this.setTitle("Map Masters: Inställningar");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(windowWidth, windowHeight);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (screen.width / 2) - (windowWidth / 2);
        int y = (screen.height / 2) - (windowHeight / 2);

        this.setLocation(x, y);

        createPanel();
    }

    /**
     * Creates <code>JButton</code>s, <code>JLabel</code>s and <code>JTextField</code>s
     * and sets the layout of the items in the frame. The method name may imply
     * that the method creates some sort of panel like a <code>JPanel</code>,
     * but this is not the case.
     */
    private void createPanel() {

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        //c.anchor = GridBagConstraints.PAGE_START;

        JLabel time = new JLabel("Tid");
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 10, 0);
        this.add(time, c);

        JLabel easy = new JLabel("Lätt:");
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 0, 0);
        this.add(easy, c);

        easyText = new JTextField(5);
        easyText.setText("" + ConfigurationReader.getIntegerSetting("TIME_OUT_EASY"));
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 0, 200);
        this.add(easyText, c);

        JLabel medium = new JLabel("Medium:");
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(0, 0, 0, 0);
        this.add(medium, c);

        mediumText = new JTextField(5);
        mediumText.setText("" + ConfigurationReader.getIntegerSetting("TIME_OUT_MEDIUM"));
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(0, 0, 0, 200);
        this.add(mediumText, c);

        JLabel hard = new JLabel("Svårt:");
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(0, 0, 0, 0);
        this.add(hard, c);

        hardText = new JTextField(5);
        hardText.setText("" + ConfigurationReader.getIntegerSetting("TIME_OUT_HARD"));
        c.gridx = 1;
        c.gridy = 3;
        c.insets = new Insets(0, 0, 0, 200);
        this.add(hardText, c);

        JLabel windowSmallLabel = new JLabel("Små fönster");
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(10, 0, 10, 0);
        this.add(windowSmallLabel, c);

        JLabel windowSmallWidth = new JLabel("Bredd:");
        c.gridx = 0;
        c.gridy = 5;
        c.insets = new Insets(0, 0, 0, 0);
        this.add(windowSmallWidth, c);

        windowSmallWidthText = new JTextField(5);
        windowSmallWidthText.setEditable(false);
        windowSmallWidthText.setText("" + ConfigurationReader.getIntegerSetting("WINDOW_SMALL_WIDTH"));
        c.gridx = 1;
        c.gridy = 5;
        c.insets = new Insets(0, 0, 0, 200);
        this.add(windowSmallWidthText, c);

        JLabel windowSmallHeight = new JLabel("Höjd:");
        c.gridx = 0;
        c.gridy = 6;
        c.insets = new Insets(0, 0, 0, 0);
        this.add(windowSmallHeight, c);

        windowSmallHeightText = new JTextField(5);
        windowSmallHeightText.setEditable(false);
        windowSmallHeightText.setText("" + ConfigurationReader.getIntegerSetting("WINDOW_SMALL_HEIGHT"));
        c.gridx = 1;
        c.gridy = 6;
        c.insets = new Insets(0, 0, 0, 200);
        this.add(windowSmallHeightText, c);

        JLabel windowBigLabel = new JLabel("Stora fönster");
        c.gridx = 0;
        c.gridy = 7;
        c.insets = new Insets(10, 0, 10, 0);
        this.add(windowBigLabel, c);

        JLabel windowBigWidth = new JLabel("Bredd:");
        c.gridx = 0;
        c.gridy = 8;
        c.insets = new Insets(0, 0, 0, 0);
        this.add(windowBigWidth, c);

        windowBigWidthText = new JTextField(5);
        windowBigWidthText.setEditable(false);
        windowBigWidthText.setText("" + ConfigurationReader.getIntegerSetting("WINDOW_WIDTH"));
        c.gridx = 1;
        c.gridy = 8;
        c.insets = new Insets(0, 0, 0, 200);
        this.add(windowBigWidthText, c);

        JLabel windowBigHeight = new JLabel("Höjd:");
        c.gridx = 0;
        c.gridy = 9;
        c.insets = new Insets(0, 0, 0, 0);
        this.add(windowBigHeight, c);

        windowBigHeightText = new JTextField(5);
        windowBigHeightText.setEditable(false);
        windowBigHeightText.setText("" + ConfigurationReader.getIntegerSetting("WINDOW_HEIGHT"));
        c.gridx = 1;
        c.gridy = 9;
        c.insets = new Insets(0, 0, 0, 200);
        this.add(windowBigHeightText, c);

        JLabel backgroundColor = new JLabel("Bakgrundsfärg (HEX):");
        c.gridx = 0;
        c.gridy = 10;
        c.insets = new Insets(20, 0, 20, 0);
        this.add(backgroundColor, c);

        backgroundColorText = new JTextField(5);
        backgroundColorText.setText("" + ConfigurationReader.getSetting("BACKGROUND_COLOR"));
        c.gridx = 1;
        c.gridy = 10;
        c.insets = new Insets(0, 0, 0, 200);
        this.add(backgroundColorText, c);

        JButton highscoreResetButton = new JButton("Radera highscore");
        c.gridx = 0;
        c.gridy = 11;
        c.insets = new Insets(0, 0, 20, 0);
        this.add(highscoreResetButton, c);
        highscoreResetButton.addActionListener(new ConfigureListener(this));

        JButton apply = new JButton("Tillämpa");
        c.gridx = 1;
        c.gridy = 12;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.PAGE_END;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(0, 0, 0, 0);
        this.add(apply, c);
        apply.addActionListener(new ConfigureListener(this));
        this.setVisible(true);

        JButton reset = new JButton("Återställ");
        c.gridx = 0;
        c.gridy = 12;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.PAGE_END;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(0, 0, 0, 0);
        this.add(reset, c);
        reset.addActionListener(new ConfigureListener(this));
        this.setVisible(true);
    }

    /**
     * Returns the input from the <code>JTextField</code> which represents
     * the time for difficulty easy.
     * @return An int.
     */
    public int getEasyTime() {
        return Integer.parseInt(easyText.getText());
    }

    /**
     * Returns the input from the <code>JTextField</code> which represents
     * the time for difficulty medium.
     * @return An int.
     */
    public int getMediumTime() {
        return Integer.parseInt(mediumText.getText());
    }

    /**
     * Returns the input from the <code>JTextField</code> which represents
     * the time for difficulty hard.
     * @return An int.
     */
    public int getHardTime() {
        return Integer.parseInt(hardText.getText());
    }

    /**
     * Returns the input from the <code>JTextField</code> which represents
     * the width of small windows.
     * @return An int.
     */
    public int getSmallWindowWidth() {
        return Integer.parseInt(windowSmallWidthText.getText());
    }

    /**
     * Returns the input from the <code>JTextField</code> which represents
     * the height of small windows.
     * @return An int.
     */
    public int getSmallWindowHeight() {
        return Integer.parseInt(windowSmallHeightText.getText());
    }

    /**
     * Returns the input from the <code>JTextField</code> which represents
     * the width of big windows.
     * @return An int.
     */
    public int getBigWindowWidth() {
        return Integer.parseInt(windowBigWidthText.getText());
    }

    /**
     * Returns the input from the <code>JTextField</code> which represents
     * the height of big windows.
     * @return An int.
     */
    public int getBigWindowHeight() {
        return Integer.parseInt(windowBigHeightText.getText());
    }

    /**
     * Returns the input from the <code>JTextField</code> which represents
     * the background color of the game maps.
     * @return A <code>String</code>.
     */
    public String getBackgroundColor() {
        return backgroundColorText.getText();
    }
}
