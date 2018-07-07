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

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Displays an image with a changable palette
 *
 * @author Andreas Norberg
 */
public class MapPanel extends JPanel {

    private final WritableRaster raster;
    private final byte[] r, g, b, a;
    private final int bits, size;
    private BufferedImage image;

    /**
     *
     * @param fileName  image file name
     * @throws IOException
     */
    @Deprecated
    public MapPanel(String fileName) throws IOException {
        this(new File(fileName));
    }

    /**
     *
     * @param url  image url
     * @throws IOException
     */
    public MapPanel(URL url) throws IOException {
        this(ImageIO.read(url));
    }

    /**
     *
     * @param file  image file
     * @throws IOException
     */
    public MapPanel(File file) throws IOException {
        this(ImageIO.read(file));
    }

    /**
     *
     * @param inStream  image input stream
     * @throws IOException
     */
    public MapPanel(InputStream inStream) throws IOException {
        this(ImageIO.read(inStream));
    }

    /**
     *
     * @param image  an image
     * @throws IOException
     */
    public MapPanel(BufferedImage image) {
        this.image = image;

        raster = image.getRaster();
        //XXX: kolla att bilden är indexerad
        IndexColorModel icm = (IndexColorModel) image.getColorModel();

        bits = icm.getPixelSize();
        size = icm.getMapSize();

        r = new byte[size];
        g = new byte[size];
        b = new byte[size];
        a = new byte[size];

        icm.getReds(r);
        icm.getGreens(g);
        icm.getBlues(b);
        icm.getAlphas(a);
    }

    /**
     * Set palette color at specified index
     *
     * @param index  the index to change
     * @param red    new red value
     * @param green  new green value
     * @param blue   new blue value
     */
    public void setColorIndex(int index, int red, int green, int blue) {
        image = null;
        r[index] = (byte) red;
        g[index] = (byte) green;
        b[index] = (byte) blue;
        a[index] = -1;
    }

    /**
     * Set palette color at specified index
     *
     * @param index  the index to change
     * @param red    new red value
     * @param green  new green value
     * @param blue   new blue value
     * @param alpha  new alpha value
     */
    public void setColorIndex(int index, int red, int green, int blue, int alpha) {
        image = null;
        r[index] = (byte) red;
        g[index] = (byte) green;
        b[index] = (byte) blue;
        a[index] = (byte) alpha;
    }

    /**
     * Gets the index for a given color
     *
     * @param red
     * @param green
     * @param blue
     * @return       color index or -1 if the searched color is not found
     */
    public int getColorIndex(int red, int green, int blue) {
        for (int i = 0; i < size; ++i) {
            if (r[i] == (byte) red && g[i] == (byte) green && b[i] == (byte) blue) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D gr = (Graphics2D) graphics;
        //gr.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        if (image == null) {
            IndexColorModel icm = new IndexColorModel(bits, size, r, g, b, a);
            image = new BufferedImage(icm, raster, false, null);
        }
        int img_width = raster.getWidth();
        int img_height = raster.getHeight();

        int panel_width = getWidth();
        int panel_heigth = getHeight();

        double scaling_factor = Math.min(
                (double) panel_width / img_width,
                (double) panel_heigth / img_height);

        img_width = (int) (img_width * scaling_factor);
        img_height = (int) (img_height * scaling_factor);

        String color = ConfigurationReader.getSetting("BACKGROUND_COLOR");
        Color background = new Color(Integer.parseInt(color, 16));
        gr.setColor(background);
        gr.fill(new Rectangle(0, 0, panel_width, panel_heigth));
        gr.drawImage(image,
                (panel_width - img_width) / 2,
                (panel_heigth - img_height) / 2,
                img_width, img_height, null);
    }
}
