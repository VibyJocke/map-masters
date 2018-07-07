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
import project.controller.Controller;
import project.view.listeners.MainJFrameListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * <code>MainJFrame</code> extends <code>JFrame</code> and is the frame which is
 * shown when the program is started. It contains two panels, The first panel
 * displays the logo and the second panel contains five buttons.
 * @author Oskar
 */
public class MainJFrame extends JFrame {

    private Controller controller;
    private BufferedImage logo;
    private ActionListener listener;
    private static JButton startButton;
    private static JButton highScoreButton;
    private static JButton settingsButton;
    private static JButton aboutButton;
    private static JButton quitButton;
    private static javax.swing.JMenu Arkiv;
    private static javax.swing.JMenu About;
    private static javax.swing.JMenu Help;

    /**
     * Creates a new <code>MainJFrame</code> instance.
     */
    public MainJFrame() {
        this.controller = new Controller();
        this.setSize(ConfigurationReader.getIntegerSetting("WINDOW_WIDTH"),
                ConfigurationReader.getIntegerSetting("WINDOW_HEIGHT"));
        initiateFrame();

        this.setTitle("Map Masters");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
    }

    /**
     * Creates the panels and sets the layout.
     */
    private void initiateFrame() {
        this.setLayout(new GridBagLayout());

        GridBagConstraints conBPanel = new GridBagConstraints();

        conBPanel.gridx = 1;
        conBPanel.gridy = 2;
        conBPanel.gridwidth = 1;
        conBPanel.gridheight = 1;
        conBPanel.fill = GridBagConstraints.BOTH;
        conBPanel.anchor = GridBagConstraints.CENTER;
        conBPanel.weightx = 1;
        conBPanel.weighty = 0;
        conBPanel.insets = new Insets(4, //Ovanför
                4, //till vänster
                4, //Under
                12); //till höger

        this.add(createButtonPanel(), conBPanel);
        this.setJMenuBar(createMenu());

        ImagePanel logoPanel = new ImagePanel("logo.png");

        GridBagConstraints conLogo = new GridBagConstraints();

        conLogo.gridx = 1;
        conLogo.gridy = 1;
        conLogo.gridwidth = 1;
        conLogo.gridheight = 1;
        conLogo.fill = GridBagConstraints.BOTH;
        conLogo.anchor = GridBagConstraints.CENTER;
        conLogo.weightx = 1;
        conLogo.weighty = 1;
        conLogo.insets = new Insets(0, //Ovanför
                0, //till vänster
                0, //Under
                0); //till höger
        this.add(logoPanel, conLogo);


    }

    /**
     * Creates a panel which contains the buttons, sets the layout of the
     * buttons and returns it.
     * @return The created <code>JPanel</code>.
     */
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());

        listener = new MainJFrameListener(controller);

        GridBagConstraints conButton = new GridBagConstraints();

        conButton.gridx = 1;
        conButton.gridy = 1;
        conButton.gridwidth = 1;
        conButton.gridheight = 1;
        conButton.fill = GridBagConstraints.BOTH;
        conButton.anchor = GridBagConstraints.BASELINE;
        conButton.weightx = 1;
        conButton.weighty = 0;
        conButton.insets = new Insets(8, //Ovanför
                8, //till vänster
                8, //Under
                8); //till höger

        startButton = new JButton("Starta Spel");
        startButton.addActionListener(listener);
        buttonPanel.add(startButton, conButton);

        conButton.gridx = 2;
        conButton.gridy = 1;

        highScoreButton = new JButton("Högsta Poäng");
        highScoreButton.addActionListener(listener);
        buttonPanel.add(highScoreButton, conButton);

        conButton.gridx = 3;
        conButton.gridy = 1;

        settingsButton = new JButton("Inställningar");
        settingsButton.addActionListener(listener);
        buttonPanel.add(settingsButton, conButton);

        conButton.gridx = 4;
        conButton.gridy = 1;

        aboutButton = new JButton("Om Map Masters");
        aboutButton.addActionListener(listener);
        buttonPanel.add(aboutButton, conButton);

        conButton.gridx = 5;
        conButton.gridy = 1;

        quitButton = new JButton("Avsluta");
        quitButton.addActionListener(listener);
        buttonPanel.add(quitButton, conButton);

        return buttonPanel;
    }

    /**
     * Creates a <code>JMenuBar</code> with menus and items and returns it.
     * @return The created <code>JMenuBar</code>.
     */
    private JMenuBar createMenu() {
        javax.swing.JMenuBar bar = new JMenuBar();
        Arkiv = new javax.swing.JMenu("Arkiv");
        About = new javax.swing.JMenu("Om");
        Help = new javax.swing.JMenu("Hjälp");

        //kortkommandon till menyn
        bar.add(Arkiv);
        Arkiv.setMnemonic(KeyEvent.VK_1);
        bar.add(Help);
        Help.setMnemonic(KeyEvent.VK_2);
        bar.add(About);
        About.setMnemonic(KeyEvent.VK_3);

        JMenuItem aboutAction = new JMenuItem("Om Map Masters");
        JMenuItem helpAction = new JMenuItem("Instruktioner");
        JMenuItem settingsAction = new JMenuItem("Inställningar");

        JMenuItem nyttSpelAction = new JMenuItem("Starta Spel");
        JMenuItem highScoreAction = new JMenuItem("Högsta Poäng");
        JMenuItem avslutaAction = new JMenuItem("Avsluta");

        /*Add to actionlistener*/
        ActionListener menuAL = this.listener;
        avslutaAction.addActionListener(menuAL);
        highScoreAction.addActionListener(menuAL);
        aboutAction.addActionListener(menuAL);
        helpAction.addActionListener(menuAL);
        nyttSpelAction.addActionListener(menuAL);
        settingsAction.addActionListener(menuAL);


        /*Add menuitems to correct menu*/
        Arkiv.add(nyttSpelAction);
        Arkiv.add(settingsAction);
        Arkiv.addSeparator();
        Arkiv.add(avslutaAction);

        //kortkommandon direkt till menyalternativ
        helpAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.CTRL_MASK));
        aboutAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, InputEvent.CTRL_MASK));
        highScoreAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));

        Help.add(helpAction);
        About.add(aboutAction);

        return bar;
    }

    /**
     * Enables/Disables all buttons in all <code>MainJFrame</code> instances.
     * @param b True to enable the buttons, false to disable the buttons.
     */
    public static void setButtonsEnabled(boolean b) {
        startButton.setEnabled(b);
        highScoreButton.setEnabled(b);
        settingsButton.setEnabled(b);
        aboutButton.setEnabled(b);
        quitButton.setEnabled(b);
    }

    public static void setMenuEnabled(boolean b) {
        Arkiv.setEnabled(b);
        About.setEnabled(b);
        Help.setEnabled(b);
    }
}
