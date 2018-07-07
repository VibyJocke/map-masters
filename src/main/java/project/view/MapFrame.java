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
package project.view;

import project.configuration.ConfigurationReader;
import project.controller.Controller;
import project.model.PlayerDTO;
import project.view.listeners.AnswerActionListener;
import project.view.listeners.EndGameButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author Andreas Norberg
 */
public class MapFrame extends JFrame {

    private JLabel players[];
    private JLabel currentPlayer;
    private JButton endButton;
    JTextField answer;
    JTextField timerField;
    private MapPanel map;
    private Controller controller;
    private int[] country_index = new int[256];

    /**
     * Constructor. Creates a new <code>MapFrame</code> instance.
     *
     * @param controller Controller.
     * @throws IOException originating from <code>initiateFrame</code>.
     */
    public MapFrame(Controller controller) throws IOException {
        this.controller = controller;
        controller.addNewCountryListener(new MapFrameObserver(this));

        this.setTitle("Map Masters");

        initiateFrame();

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setSize(ConfigurationReader.getIntegerSetting("WINDOW_WIDTH"), ConfigurationReader.getIntegerSetting("WINDOW_HEIGHT"));

        this.setVisible(true);
        this.setExtendedState(MAXIMIZED_BOTH);

    }

    /**
     * Initiates the frame, creating and adding all the components.
     *
     * @throws IOException originating from <code>MapPanel</code>.
     */
    private void initiateFrame() throws IOException {
        //komponenter
        setPlayerLabels();
        currentPlayer = new JLabel();
        answer = new JTextField();
        timerField = new JTextField();

        String image_path = controller.getContinentImagePath();
        InputStream is = ClassLoader.getSystemResourceAsStream(image_path);
        map = new MapPanel(is);

        answer.setAction(new AnswerActionListener(controller));

        clearGreens();

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(4, //Ovanför
                4, //till vänster
                4, //Under
                4); //till höger

        //spelare
        for (int i = 0; i < players.length; i++) {
            c.gridx = i;
            this.add(players[i], c);
        }

        //karta
        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        this.add(map, c);

        //nuvarande spelare
        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 0;
        this.add(currentPlayer, c);

        //svarsfält
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 3;
        c.weighty = 0;
        answer.setFont(new Font(answer.getFont().getName(), Font.BOLD, 60));
        answer.setHorizontalAlignment(JTextField.CENTER);
        this.add(answer, c);

        //Timer field
        c.gridwidth = 1;
        c.gridx = 3;
        c.gridy = 3;
        c.weighty = 0;
        this.timerField.setFont(new Font(timerField.getFont().getName(), Font.BOLD, 60));
        this.timerField.setHorizontalAlignment(JTextField.CENTER);
        this.timerField.setEditable(false);
        this.add(timerField, c);

        //Avslutaknapp
        c.gridwidth = 1;
        c.gridx = 4;
        c.gridy = 3;
        c.weighty = 0;
        c.fill = GridBagConstraints.VERTICAL;

        this.endButton = new JButton("Avsluta");
        this.endButton.addActionListener(new EndGameButtonListener(this));
        this.add(endButton, c);
    }

    /**
     * Creates an index for all countries based on the original
     * map image and then sets all countries to white.
     */
    private void clearGreens() {
        for (int i, green = 1; green <= 255; ++green) {
            i = map.getColorIndex(0, green, 0);
            if (i >= 0) {
                country_index[green] = i;
                map.setColorIndex(i, 0xFF, 0xFF, 0xFF);
            }
        }
    }

    /**
     * Highlights a players coutry.
     *
     * @param countryIndex Index of the current country.
     * @param red Player color information.
     * @param green Player color information.
     * @param blue Player color information.
     */
    public void highLightCountry(int countryIndex, int red, int green, int blue) {
        map.setColorIndex(country_index[countryIndex], red, green, blue);
    }

    /**
     * Updates the player labels with score.
     */
    public void updatePlayerLabels() {
        List<PlayerDTO> playerList = controller.getAllPlayers();
        int numberOfPlayers = playerList.size();

        if (numberOfPlayers != players.length) {
            System.err.println("Number of player conflict");
            setPlayerLabels();
        }

        for (int i = 0; i < numberOfPlayers; i++) {
            PlayerDTO player = playerList.get(i);
            String label = player.getName() + ": " + player.getPoints();
            this.players[i].setText(label);
        }
    }

    /**
     * Creates and sets the player labels.
     */
    private void setPlayerLabels() {
        List<PlayerDTO> playerList = controller.getAllPlayers();

        int numberOfPlayers = playerList.size();
        this.players = new JLabel[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            PlayerDTO player = playerList.get(i);
            String label = player.getName() + ": " + player.getPoints();
            this.players[i] = new JLabel(label);
            this.players[i].setForeground(player.getColor());
        }

    }

    /**
     * Updates the current player's score.
     */
    public void updateCurrentPlayerLabel() {
        PlayerDTO player = controller.getCurrentPlayerDTO();
        currentPlayer.setText(player.getName() + ":");
        currentPlayer.setForeground(player.getColor());
    }

    private Color current_color;
    private int current_index;
    private MapFrame self = this;
    //*
    private Timer timer = new Timer(500, new ActionListener() {

        private boolean hl = false;

        public void actionPerformed(ActionEvent e) {
            hl = !hl;
            if (hl) {
                self.highLightCountry(current_index, current_color.getRed(), current_color.getGreen(), current_color.getBlue());
            } else {
                self.highLightCountry(current_index, 255, 255, 255);
            }
            self.repaint();
        }
    });
    //*/

    /**
     * Sets the current country highlight, and starts timer.
     *
     * @param index The current country index.
     * @param color The color to set it to.
     */
    void setCurrentCountry(int index, Color color) {
        current_color = color;
        current_index = index;
        self.highLightCountry(index, color.getRed(), color.getGreen(), color.getBlue());
        timer.start();
    }

    /**
     * Resets the current country color and stops the timer.
     *
     * @param b A boolean value.
     */
    void resetCurrentCountry(boolean b) {
        Color color;
        if (b) {
            color = current_color.brighter();
        } else {
            color = current_color.darker();
        }
        this.highLightCountry(current_index, color.getRed(), color.getGreen(), color.getBlue());
        timer.stop();
    }

    /**
     * Disables the textfield at the end of the game.
     */
    public void disableTextField() {
        this.answer.setEnabled(false);
        this.answer.setText("SPELET KLART!");
    }
}
