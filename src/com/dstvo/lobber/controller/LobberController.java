/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dstvo.lobber.controller;

import com.dstvo.lobber.LobberConstants;
import com.dstvo.lobber.LobberUtil;
import com.dstvo.lobber.model.LobberModel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.dvb.event.UserEvent;
import org.dvb.event.UserEventListener;
import org.havi.ui.event.HRcEvent;

/**
 *
 * @author Devadas.Vijayan
 */
public class LobberController implements KeyListener, UserEventListener
{
    private LobberModel model;
    private boolean matchInProgress;

    public void setModel(LobberModel model)
    {
        this.model = model;
    }

    public void startPlay()
    {
        model.start();
    }

    public void keyTyped(KeyEvent e)
    {
    }

    public void keyPressed(KeyEvent e)
    {
        processKeyCode(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e)
    {
    }

    public void userEventReceived(UserEvent userEvent)
    {
        if (userEvent.getType() == HRcEvent.KEY_PRESSED)
        {
            processKeyCode(userEvent.getCode());
        }
    }

    private void processKeyCode(int keyCode)
    {
        switch (keyCode)
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
                matchInProgress = true;
                model.processSelection();
                break;
            case KeyEvent.VK_1:
                matchInProgress = false;
                model.reset(false);
                break;
            case KeyEvent.VK_9:
                if (!matchInProgress)
                {
                    LobberUtil.setDifficultyLevel(LobberConstants.LEVEL_DIFFICULT);
                    model.reset(true);
                }
                break;
            case KeyEvent.VK_8:
                if (!matchInProgress)
                {
                    LobberUtil.setDifficultyLevel(LobberConstants.LEVEL_MEDIUIM);
                    model.reset(true);
                }
                break;
            case KeyEvent.VK_7:
                if (!matchInProgress)
                {
                    LobberUtil.setDifficultyLevel(LobberConstants.LEVEL_EASY);
                    model.reset(true);
                }
                break;
        }
    }
}
