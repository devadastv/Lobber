/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dstvo.lobber.view;

import com.dstvo.lobber.model.GridPosition;
import com.dstvo.lobber.LobberConstants;
import com.dstvo.lobber.model.LobberState;
import com.dstvo.lobber.util.ImageCache;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.KeyListener;

/**
 *
 * @author user
 */
public class LobberView extends Frame
{
    private LobberGrid grid;
    private Label statusText;
    private KeyListener listener;

    public void initGui()
    {
        this.setBounds(LobberConstants.APP_X_POS, LobberConstants.APP_Y_POS,
                LobberConstants.APP_WIDTH, LobberConstants.APP_HEIGHT);
        this.setBackground(Color.black);
        Container localContainer = new Container() {

            public void paint(Graphics g)
            {
                g.drawImage(ImageCache.getImage("resources/wallpaper-23.jpg"), 0, 0, LobberConstants.APP_WIDTH, LobberConstants.APP_HEIGHT, null);
                super.paint(g);
            }

        };
        localContainer.setBounds(getBounds());
        createLobberGrid();
        createStatusDisplay();
        localContainer.add(grid);
        localContainer.add(statusText);
        this.add(localContainer);
        this.setResizable(false);
        
    }


    public void setKeyListener(KeyListener keyListener)
    {
        this.listener = keyListener;
    }

    private void createLobberGrid()
    {
        grid = new LobberGrid();
        grid.setBounds(LobberConstants.GRID_X_POS,
                LobberConstants.GRID_Y_POS, LobberConstants.GRID_WIDTH, LobberConstants.GRID_HEIGHT);
        System.out.println("Frame Bounds is " + getBounds());
        System.out.println("Grid Bounds is " + grid.getBounds());
        grid.initializeCells();
        grid.setVisible(true);
    }

    private void createStatusDisplay()
    {
        statusText = new Label();
        statusText.setBounds(LobberConstants.STATUS_X_POS, LobberConstants.STATUS_Y_POS,
                LobberConstants.STATUS_WIDTH, LobberConstants.STATUS_HEIGHT);
        statusText.setBackground(Color.white);
        statusText.setAlignment(Label.CENTER);
        statusText.setVisible(true);
    }


    public void shiftFocusToCell(GridPosition position)
    {
        grid.shiftFocusToCell(position);
    }

    public void selectCell(GridPosition position, byte cellValue)
    {
        grid.selectCell(position, cellValue);
    }

    public void displayStatus(int status)
    {
        String statusMessage = LobberConstants.WAITING_FOR_OPPONENT_STATUS_MESSAGE;
        switch (status)
        {
            case LobberState.OPPONENT_WON:
                statusText.setBackground(Color.green);
                statusMessage = LobberConstants.OPPONENT_WON_STATUS_MESSAGE;
                break;
            case LobberState.PLAYER_THINKING:
                statusMessage = LobberConstants.PLAYER_THINKING_STATUS_MESSAGE;
                break;
            case LobberState.PLAYER_WON:
                statusText.setBackground(Color.red);
                statusMessage = LobberConstants.PLAYER_WON_STATUS_MESSAGE;
                break;
            case LobberState.WAITING_FOR_OPPONENT:
                statusMessage = LobberConstants.WAITING_FOR_OPPONENT_STATUS_MESSAGE;
                break;
            case LobberState.GAME_DRAWN:
                statusText.setBackground(Color.yellow);
                statusMessage = LobberConstants.GAME_DRAWN_STATUS_MESSAGE;
                break;
            case LobberState.WELCOME_PLAYER:
                statusMessage = LobberConstants.WELCOME_AND_WAIT_STATUS_MESSAGE;
                break;
        }
        statusText.setText(statusMessage);
    }

    public void reset()
    {
        statusText.setBackground(Color.white);
        grid.reset();
    }

    public void showUI()
    {
        this.setVisible(true);
        this.addKeyListener(listener);
        this.requestFocus();
    }
}
