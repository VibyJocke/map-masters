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

import project.controller.Controller;
import project.model.PlayerDTO;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * A frame which displays the high scores of the game.
 * @author Johan Berg, Johan Schedin Jigland.
 */
public class HighscoreFrame extends JFrame {
    Controller controller;

    /**
     * 
     * @param c The <code>Controller</code>.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public HighscoreFrame(Controller c) throws IOException, ClassNotFoundException{
        this.controller = c;
        this.setTitle("Högsta Poäng");

        int windowWidth = 1000;
        int windowHeight = 400;

        
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(windowWidth, windowHeight);


        this.setLayout(new GridBagLayout());


        GridBagConstraints con = new GridBagConstraints();

        con.gridx = 1;
        con.gridy = 1;
        con.gridwidth = 1;
        con.gridheight = 1;
        con.fill = GridBagConstraints.BOTH;
        con.anchor = GridBagConstraints.PAGE_START;
        con.weightx = 1;
        con.weighty = 1;
        con.insets = new Insets(8, //Ovanför
                4, //till vänster
                8, //Under
                4); //till höger

        this.add(createPanel("africa"),con);
        con.gridx = 2;
        this.add(createPanel("asia"),con);
        con.gridx = 3;
        this.add(createPanel("europe"),con);
        con.gridx = 4;
        this.add(createPanel("northamerica"),con);
        con.gridx = 5;
        this.add(createPanel("oceania"),con);
        con.gridx = 6;
        this.add(createPanel("southamerica"),con);
        con.gridx = 7;
        this.add(createPanel("world"),con);

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (screen.width/2) - (windowWidth/2);
        int y = (screen.height/2) - (windowHeight/2);

        this.setLocation(x,y);
        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     *
     * @param continent The name of a continent as a string.
     * @return JPanel The panel created in this method.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private JPanel createPanel(String continent) throws IOException, ClassNotFoundException
    {
        JPanel continentPanel = new JPanel();

        Border topBorder = new BevelBorder(BevelBorder.LOWERED); //Lägg till en ram
        continentPanel.setBorder(topBorder);

        continentPanel.setLayout(new GridBagLayout());

        GridBagConstraints con = new GridBagConstraints();

        con.gridx = 1;
        con.gridy = 1;
        con.gridwidth = 1;
        con.gridheight = 1;
        con.fill = GridBagConstraints.BOTH;
        con.anchor = GridBagConstraints.PAGE_START;
        con.weightx = 1;
        con.weighty = 1;
        con.insets = new Insets(8, //Ovanför
                4, //till vänster
                8, //Under
                4); //till höger

        String cont;

        if (continent.equals("africa"))
            cont = "Afrika";
        else if(continent.equals("asia"))
            cont = "Asien";
        else if(continent.equals("europe"))
            cont = "Europa";
        else if(continent.equals("northamerica"))
            cont = "Nordamerika";
        else if(continent.equals("oceania"))
            cont = "Oceanien";
        else if(continent.equals("southamerica"))
            cont = "Sydamerika";
        else
            cont = "Världen";

        JLabel header = new JLabel(cont);
        header.setFont(new Font("Sans Serif",Font.BOLD,18));
        JLabel names = new JLabel("Namn");
        JLabel points = new JLabel("Poäng");


        con.gridx = 1;
        con.gridy = 1;
        con.gridwidth = 2;
        continentPanel.add(header,con);
        con.gridx = 1;
        con.gridy = 2;
        con.gridwidth = 1;
        continentPanel.add(names,con);
        con.gridx = 2;
        con.gridy = 2;
        continentPanel.add(points,con);
        con.anchor = GridBagConstraints.CENTER;

        List<PlayerDTO> players = controller.readHighscores(continent);

        for (int i = 0 ; i < 10 && i < players.size() ;i++)
        {
            con.gridx = 1;
            con.gridy = i + 3;
            continentPanel.add(new JLabel(players.get(i).getName()),con);
            con.gridx = 2;
            con.gridy = i + 3;
            continentPanel.add(new JLabel(Integer.toString(players.get(i).getPoints())),con);

        }

        return continentPanel;
    }

}
