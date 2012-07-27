/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dstvo.lobber.controller;

import com.dstvo.lobber.model.IGridModel;
import com.dstvo.lobber.model.LobberModel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Devadas.Vijayan
 */
public class LobberController implements KeyListener
{
    private IGridModel model;

    public void setModel(LobberModel model)
    {
        this.model = model;
    }

    public void startPlay()
    {
        model.initialize();
    }

    public void keyTyped(KeyEvent e)
    {
    }

    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_RIGHT:
                model.moveRight();
                break;
            case KeyEvent.VK_LEFT:
                model.moveLeft();
                break;
            case KeyEvent.VK_UP:
                model.moveUp();
                break;
            case KeyEvent.VK_DOWN:
                model.moveDown();
                break;
            case KeyEvent.VK_ENTER:
                model.processSelection();
                break;
        }
    }

    public void keyReleased(KeyEvent e)
    {
    }
}
