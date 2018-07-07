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
package project.model;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents the high scores for a continent of the game.
 * @author Johan Berg
 */
public class Highscore {
    private final File dir;

    private String continent;

    /**
     * Creates a new <code>Highscore</code> instance and will control the high
     * score of a continent.
     * @param continent The continent to control.
     */
    public Highscore(String continent) {
        this.continent = continent;

        String jar_file = this.getClass().getProtectionDomain().getCodeSource().getLocation().getFile().replace("%20", " ");
        dir = new File(jar_file).getParentFile();
    }

    /**
     * Sets the continent to control
     * @param continent The continent to control.
     */
    public void setContinent(String continent) {
        this.continent = continent;
    }

    /**
     * Returns a list of <code>PlayerDTO</code> instances from the high score list.
     * @param continent Which high score to return.
     * @return A <code>List<PlayerDTO></code> instance as the high score.
     * @throws IOException Thrown if there is a file error.
     * @throws ClassNotFoundException Thrown if there is a file reading error.
     */
    public List<PlayerDTO> readHighScores() throws IOException, ClassNotFoundException {
        File hs_file = new File(dir, "hs_" + continent.toLowerCase() + ".mm");
        if (!hs_file.exists()) {
            resetHighscores();
        }

        List<PlayerDTO> objectReader;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(hs_file));
            objectReader = new ArrayList<PlayerDTO>();
            objectReader = (ArrayList<PlayerDTO>) ois.readObject();
        } catch (EOFException eof) {
            objectReader = new ArrayList<PlayerDTO>();
        }

        return objectReader;
    }

    /**
     * Updates the high score.
     * @param players The <code>List</code> of <code>PlayerDTO</code>s to add to
     * the high score.
     * @throws ClassNotFoundException Thrown if there is a file reading error.
     * @throws IOException Thrown if there is a file error.
     */
    public void updateHighscore(List<PlayerDTO> players) throws ClassNotFoundException, IOException {

        List<PlayerDTO> sortedList = new ArrayList<PlayerDTO>();

        //Load old highscorelist and merge with the list of players that just played
        List<PlayerDTO> highscoreListFromFile = readHighScores();
        List<PlayerDTO> highscoreList = new ArrayList<PlayerDTO>();
        highscoreList.addAll(players);
        highscoreList.addAll(highscoreListFromFile);

        Collections.sort(highscoreList); //Sort List

        for (int i = 0; i < 10 && i < highscoreList.size(); i++) {
            sortedList.add(highscoreList.get(i));
        }
        writeHighscores(sortedList);
    }

    /**
     * Writes a list of players to the high score file.
     * @param players A <code>List</code> of <code>PlayerDTO</code> to write.
     * @throws IOException Thrown if there is a file error.
     */
    private void writeHighscores(List<PlayerDTO> players) throws IOException {
        File file = new File(dir, "hs_" + continent.toLowerCase() + ".mm");

        file.delete();

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(players);
        oos.close();
    }

    /**
     * Resets the high score.
     * @throws IOException Thrown if there is a file error.
     */
    public void resetHighscores() throws IOException {
        PlayerDTO player = new PlayerDTO("Ej spelat", null, -999, 0, 0);
        List<PlayerDTO> players = new ArrayList<PlayerDTO>();
        for (int i = 0; i < 10; i++) {
            players.add(player);
        }
        writeHighscores(players);
    }
}
