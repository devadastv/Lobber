/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dstvo.lobber.util;

import java.awt.Container;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.util.HashMap;

/**
 *
 * @author user
 */
public class ImageCache
{
    private static HashMap imageMap = new HashMap();

    private ImageCache()
    {
    }

    public static void addImage(String imagePath)
    {
        if ((imagePath != null) && (!imageMap.containsKey(imagePath)))
        {
            imageMap.put(imagePath, loadImage(imagePath));
        }
    }

    public static Image getImage(String imagePath)
    {
        if (!imageMap.containsKey(imagePath))
        {
            addImage(imagePath);
        }
        return (Image) imageMap.get(imagePath);
    }

    private static Object loadImage(String fileName)
    {
        Image image = Toolkit.getDefaultToolkit().createImage(fileName);
        MediaTracker tracker = new MediaTracker(new Container());
        tracker.addImage(image, 0);
        try
        {
            tracker.waitForAll();
        } catch (InterruptedException ex)
        {
            ex.printStackTrace();
        }
        return image;
    }
}
