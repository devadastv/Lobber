/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dstvo.lobber.view;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author user
 */
public class SlideshowGadget extends ImageBox {
    private String[] slides;
    private int delay;
    Timer timer = new Timer();
    

    void setSlides (String[] slides)
    {
        this.slides = slides;
    }

    void setDelay(int delay)
    {
        this.delay = delay;
    }

    void startSlideShow()
    {
        TimerTask task = new TimerTask() {
            int slideIndex;
            public void run()
            {
                setImage(slides[slideIndex++]);
                if (slideIndex >= slides.length)
                {
                    slideIndex = 0;
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, delay);
    }
}
