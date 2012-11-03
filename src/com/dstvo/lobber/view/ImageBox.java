/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dstvo.lobber.view;

import com.dstvo.lobber.util.ImageCache;
import java.awt.Graphics;
import java.awt.Image;
import org.havi.ui.HComponent;

/**
 *
 * @author user
 */
public class ImageBox extends HComponent
{
    Image image;
    void setImage(String imagePath)
    {
        System.out.println("Inside image box paint with " + imagePath);
        image = ImageCache.getImage(imagePath);
        repaint();
    }

    public void paint(Graphics g)
    {
        if (null != image)
        {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        }
    }
}
