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

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This class represents the logo which is displayed in the main frame.
 * It extends JPanel and when a new object of this type is initiated it
 * reads an image which will be drawn in the middle of the panel.
 * @author Roman
 */
class ImagePanel extends JPanel {

    private BufferedImage img;

    /**
     * Creates a new <code>ImagePanel</code> instance.
     * @param path The image path.
     */
    ImagePanel(String path) {

        try {
            img = ImageIO.read(ClassLoader.getSystemResourceAsStream(path));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Draws the image with a background color.
     * @param g the <code>Graphics</code> object to draw with.
     */
    @Override
    public void paintComponent(Graphics g) {

        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();

        int panelWidth = this.getWidth();
        int panelHeight = this.getHeight();

        g.setColor(new Color(0x99cdff));
        g.fillRect(0, 0, panelWidth, panelHeight);


        g.drawImage(img, (panelWidth - imgWidth) / 2,
                (panelHeight - imgHeight) / 2,
                imgWidth, imgHeight, null);
    }
}
