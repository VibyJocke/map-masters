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

package project.view.listeners;

import project.controller.Controller;
import project.exceptions.IncompleteDataForCreationOfGameroundDataException;
import project.view.Startframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles listeners from the <code>Startframe</code>.
 * @author Oskar
 */
public class StartframeListener implements ActionListener {
    private Controller controller;
    private Startframe frame;

    /**
     * Creates a new <code>StartframeListener</code> instance.
     * @param c The <code>Controller</code>.
     * @param sf The <code>Startframe</code>.
     */
    public StartframeListener(Controller c, Startframe sf) {
        this.controller = c;
        this.frame = sf;
    }

    /**
     * Handles clicking on the "Börja spela" button. Starts the game.
     * @param e
     */
    public void actionPerformed(ActionEvent e) {

        List<String> players = frame.getPlayers();
        List<Color> colors = new ArrayList<Color>();

        for (String s : players) {
            colors.add(frame.getColor(s));
        }

        try {
            controller.startGame(frame.getContinent(), players, colors, frame.getDifficulty());
            this.frame.setVisible(false);
        } catch (IncompleteDataForCreationOfGameroundDataException ex) {
            System.err.println("IncompleteDataForCreationOfGameroundDataException");
            JOptionPane.showMessageDialog(null, "Fyll i spelarnamn!");
        }

        //MainJFrame.setButtonsEnabled(true);
    }
}
