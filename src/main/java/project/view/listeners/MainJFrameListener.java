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

import project.controller.Controller;
import project.view.HighscoreFrame;
import project.view.MainJFrame;
import project.view.SettingsFrame;
import project.view.Startframe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * An action listener for the game main frame.
 *
 * @author Oskar
 */
public class MainJFrameListener implements ActionListener {
    private Controller controller;
    private Startframe startFrame;
    private AbstractButton button;
    private CloseWindowListener windowListener = new CloseWindowListener();

    /**
     * Constructor for the <code>MainJFrameListener</code>.
     * @param c The <code>Controller</code>.
     */
    public MainJFrameListener(Controller c) {
        this.controller = c;
    }

    /**
     * Action handler for the different buttons in tha main frame window.
     * Handles the buttons "Starta Spel", "Högsta Poäng", "Inställningar",
     * "Om Map Masters", "Avsluta" and "Instruktioner". Also holds all the
     * string information displayed in "Om Map Masters" and "Instruktioner".
     * @param e <code>AtionEvent</code>.
     */
    public void actionPerformed(ActionEvent e) {

        MainJFrame.setButtonsEnabled(false);
        MainJFrame.setMenuEnabled(false);
        button = (AbstractButton) e.getSource();
        System.out.println(button.getText());

        if (button.getText().equals("Starta Spel")) {
            if (this.startFrame == null) {
                new Startframe(controller).addWindowListener(windowListener);
            } else {
                this.startFrame.setVisible(true);
            }
        }

        if (button.getText().equals("Högsta Poäng")) {
            try {
            new HighscoreFrame(controller).addWindowListener(windowListener);
            }
            catch(Exception exep)
            {
                JOptionPane.showMessageDialog(null, "Fel" + exep, "Fel", JOptionPane.ERROR_MESSAGE);
                exep.printStackTrace();
            }
        }

        if (button.getText().equals("Inställningar")) {
            new SettingsFrame().addWindowListener(windowListener);
        }

        if (button.getText().equals("Om Map Masters")) {
            JOptionPane.showMessageDialog(null, ""
                    + "Skapad av Pear Softworks:\n"
                    + "Version 1.0 rev 192 \n"
                    + "licensierad under zlib\n"
                    + "\n"
                    + "Johan Schedin Jigland\n"
                    + "Fredrik Román\n"
                    + "Oskar Höglund\n"
                    + "Nikolai Padyukov\n"
                    + "Kim Torgensen\n"
                    + "Christian Castillo\n"
                    + "Joakim Lahtinen\n"
                    + "Mihgyuan Jiang\n"
                    + "Johan Berg\n"
                    + "Andreas Norberg\n\n"
                    + "Programidé: Soft 5"
                    , "Om Map Masters",JOptionPane.PLAIN_MESSAGE,null);

             MainJFrame.setButtonsEnabled(true);
             MainJFrame.setMenuEnabled(true);
        }

        if (button.getText().equals("Avsluta")) {
            int quit = JOptionPane.showConfirmDialog(null, "Vill du verkligen avsluta?", "Avsluta?", JOptionPane.YES_NO_OPTION);

            if (quit == 0) {
                System.exit(0);
            } else {
            }
             MainJFrame.setButtonsEnabled(true);
             MainJFrame.setMenuEnabled(true);
        }

        if (button.getText().equals("Instruktioner")) {
             JOptionPane.showMessageDialog(null, ""
                    + "Starta spelet genom att trycka på \"Starta Spel\". \n"
                    + "Kryssa i antal spelare och fyll i deras namn. Välj \n"
                    + "kontinent och svårighetsnivå och tryck därefter \n"
                    + "på \"Börja Spela\". Notera att spelarnas namn är i \n"
                    + "en specifik färg som kommer att användas i spelet. \n"
                    + "När spelet startar blinkar ett av länderna i en viss \n"
                    + "färg. Spelaren med respektive färg ska då, så snabbt\n"
                    + "som möjligt, skriva in landets namn i textrutan. \n"
                    + "Pluspoäng ges för rätta svar, minuspoäng för felaktiga. \n"
                    + "Antalet poäng baseras på hur snabbt spelaren svarar, \n"
                    + "samt vald svårighetsnivån."
                    , "Instruktioner",JOptionPane.PLAIN_MESSAGE,null);

             MainJFrame.setButtonsEnabled(true);
             MainJFrame.setMenuEnabled(true);
        }

    }
}
