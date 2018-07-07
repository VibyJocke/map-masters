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
import project.constants.Constants;
import project.controller.Controller;
import project.view.listeners.StartframeListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Starting frame of the game. Here you can select number of players, continent
 * and difficulty.
 *
 * @author Johan Berg
 */
public class Startframe extends JFrame {

    private Controller controller;
    private JComboBox continentComboBox;
    private JComboBox diffComboBox;
    private JTextField player1;
    private JTextField player2;
    private JTextField player3;
    private JTextField player4;
    private List<Color> colors;

    /**
     * Creates a new <code>Startframe</code> instance.
     * @param contr The <code>Controller</code>.
     */
    public Startframe(Controller contr) {

        colors = new ArrayList<Color>();
        colors.add(Color.red);
        colors.add(Color.blue);
        colors.add(Color.orange);
        colors.add(Color.magenta);

        controller = contr;

        int windowWidth = ConfigurationReader.getIntegerSetting("WINDOW_SMALL_WIDTH");
        int windowHeight = ConfigurationReader.getIntegerSetting("WINDOW_SMALL_HEIGHT");

        this.setTitle("Map Masters: Nytt Spel");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(windowWidth, windowHeight);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (screen.width / 2) - (windowWidth / 2);
        int y = (screen.height / 2) - (windowHeight / 2);

        this.setLocation(x, y);
        this.add(createPanel());
        this.setResizable(false);

        this.setVisible(true);
    }

    /**
     * Creates the panels and sets the layout.
     * @return JPanel The panel created in this method.
     */
    private JPanel createPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //Player 1
        JLabel player1Label = new JLabel("Namn spelare 1: ");
        c.gridx = 0;
        c.gridy = 0;
        panel.add(player1Label, c);

        player1 = new JTextField(15);
        c.gridx = 1;
        c.gridy = 0;
        player1.setForeground(Color.red);
        player1.setText("Player1");
        player1.selectAll();
        panel.add(player1, c);

        final JCheckBox player1Check = new JCheckBox("spelar");
        player1Check.setSelected(true);
        c.gridx = 2;
        c.gridy = 0;
        panel.add(player1Check);

        //Player 2
        JLabel player2Label = new JLabel("Namn spelare 2: ");
        c.insets = new Insets(10, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 1;
        panel.add(player2Label, c);

        player2 = new JTextField(15);
        c.gridx = 1;
        c.gridy = 1;
        player2.setForeground(Color.blue);
        player2.setText("Player2");
        player2.selectAll();
        panel.add(player2, c);

        final JCheckBox player2Check = new JCheckBox("spelar");
        player2Check.setSelected(true);
        c.gridx = 2;
        c.gridy = 1;
        panel.add(player2Check, c);


        //Player 3
        JLabel player3Label = new JLabel("Namn spelare 3: ");
        c.insets = new Insets(10, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 2;
        panel.add(player3Label, c);

        player3 = new JTextField(15);
        player3.setEditable(false);
        c.gridx = 1;
        c.gridy = 2;
        panel.add(player3, c);

        final JCheckBox player3Check = new JCheckBox("spelar");
        c.gridx = 2;
        c.gridy = 2;
        panel.add(player3Check, c);


        //Player 4
        JLabel player4Label = new JLabel("Namn spelare 4: ");
        c.insets = new Insets(10, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 3;
        panel.add(player4Label, c);

        player4 = new JTextField(15);
        player4.setEditable(false);
        c.gridx = 1;
        c.gridy = 3;
        panel.add(player4, c);

        final JCheckBox player4Check = new JCheckBox("spelar");
        c.gridx = 2;
        c.gridy = 3;
        panel.add(player4Check, c);


        //Continent chooser
        JLabel continentLabel = new JLabel("Välj kontinent: ");
        c.insets = new Insets(10, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 4;
        panel.add(continentLabel, c);

        String[] continents = {"Nordamerika", "Sydamerika", "Afrika", "Europa", "Asien", "Oceanien", "Världen"};
        continentComboBox = new JComboBox(continents);
        c.gridx = 1;
        c.gridy = 4;
        panel.add(continentComboBox, c);


        //Difficulty chooser
        JLabel diffLabel = new JLabel("Välj svårighetsgrad: ");
        c.insets = new Insets(10, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 5;
        panel.add(diffLabel, c);

        String[] difficulty = {"Lätt", "Medium", "Svårt"};
        diffComboBox = new JComboBox(difficulty);
        c.gridx = 1;
        c.gridy = 5;
        panel.add(diffComboBox, c);


        //Starting button
        JButton startButton = new JButton("Börja spela");
        c.insets = new Insets(25, 0, 0, 0);
        c.gridx = 2;
        c.gridy = 6;
        panel.add(startButton, c);

        startButton.addActionListener(new StartframeListener(controller, this));

        /**
         * Listeners for the checkboxes. If the checkbox is marked the textfield
         * next to it is editable. If the checkbox is unmarked the textfield isn't
         * editable.
         */
        ItemListener CheckListener = new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                Object source = e.getItemSelectable();

                if (source == player1Check) {
                    player1.setEditable(true);
                    player1.setText("Player1");
                    if (e.getStateChange() == ItemEvent.DESELECTED) {
                        player1.setEditable(false);
                        player1.setText("");
                    }
                }

                if (source == player2Check) {
                    player2.setEditable(true);
                    player2.setText("Player2");
                    if (e.getStateChange() == ItemEvent.DESELECTED) {
                        player2.setEditable(false);
                        player2.setText("");
                    }
                }

                if (source == player3Check) {
                    player3.setEditable(true);
                    player3.setForeground(Color.orange);
                    player3.setText("Player3");
                    if (e.getStateChange() == ItemEvent.DESELECTED) {
                        player3.setEditable(false);
                        player3.setText("");
                    }
                }

                if (source == player4Check) {
                    player4.setEditable(true);
                    player4.setForeground(Color.magenta);
                    player4.setText("Player4");
                    if (e.getStateChange() == ItemEvent.DESELECTED) {
                        player4.setEditable(false);
                        player4.setText("");
                    }
                }
            }
        };

        player1Check.addItemListener(CheckListener);
        player2Check.addItemListener(CheckListener);
        player3Check.addItemListener(CheckListener);
        player4Check.addItemListener(CheckListener);

        return panel;
    }

    /**
     *
     * @return The players names in a List.
     */
    public List<String> getPlayers() {
        List<String> l = new ArrayList<String>();

        if (!player1.getText().equals("")) {
            l.add(player1.getText());
        }
        if (!player2.getText().equals("")) {
            l.add(player2.getText());
        }
        if (!player3.getText().equals("")) {
            l.add(player3.getText());
        }
        if (!player4.getText().equals("")) {
            l.add(player4.getText());
        }

        return l;
    }

    /**
     *
     * @param player The player's name as a string.
     * @return The color of the chosen player.
     */
    public Color getColor(String player) {

        if (player.equalsIgnoreCase(player1.getText())) {
            return colors.get(0);
        } else if (player.equalsIgnoreCase(player2.getText())) {
            return colors.get(1);
        } else if (player.equalsIgnoreCase(player3.getText())) {
            return colors.get(2);
        } else {
            return colors.get(3);
        }
    }

    /**
     *
     * @return The chosen difficulty.
     */
    public Constants.Difficulty getDifficulty() {
        Constants.Difficulty diff = Constants.Difficulty.MEDIUM;

        if (diffComboBox.getSelectedItem().equals("Lätt")) {
            diff = Constants.Difficulty.EASY;
        } else if (diffComboBox.getSelectedItem().equals("Medium")) {
            diff = Constants.Difficulty.MEDIUM;
        } else if (diffComboBox.getSelectedItem().equals("Svårt")) {
            diff = Constants.Difficulty.HARD;
        }

        return diff;
    }

    /**
     *
     * @return The chosen continent.
     */
    public String getContinent() {
        //TODO: Fix the cast
        return (String) continentComboBox.getSelectedItem();
    }
}
