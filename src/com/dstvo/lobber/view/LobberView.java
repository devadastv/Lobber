/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dstvo.lobber.view;

import com.dstvo.lobber.model.GridPosition;
import com.dstvo.lobber.LobberConstants;
import com.dstvo.lobber.model.LobberState;
import com.dstvo.lobber.util.ImageCache;
import com.dstvo.lobber.util.VideoHandler;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.event.KeyListener;

/**
 *
 * @author user
 */
public class LobberView extends Frame
{
    private LobberGrid grid;
    private KeyListener listener;
    private ImageBox statusBox;
    private ImageBox helpText;
    Container localContainer;
    private ImageBox appTitle;

    public void initGui()
    {
        this.setBounds(LobberConstants.APP_X_POS, LobberConstants.APP_Y_POS,
                LobberConstants.APP_WIDTH, LobberConstants.APP_HEIGHT);
        this.setBackground(Color.black);
        localContainer = new Container() {
            public void paint(Graphics g)
            {
                g.drawImage(ImageCache.getImage("resources/Background.png"), 0, 0,
                        LobberConstants.APP_WIDTH, LobberConstants.APP_HEIGHT, null);
                super.paint(g);
            }
        };
        localContainer.setBounds(getBounds());
        createAppTitle();
        createLobberGrid();
        createStatusDisplay();
        createHelpDisplay();
        createAdvtSlideShow();
        this.add(localContainer);
        this.setResizable(false);
        VideoHandler.resizeVideo(new Rectangle(LobberConstants.VIDEO_X_POS,
                LobberConstants.VIDEO_Y_POS, LobberConstants.VIDEO_WIDTH,
                LobberConstants.VIDEO_HEIGHT));
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
        localContainer.add(grid);
        grid.setVisible(true);
    }

    private void createStatusDisplay()
    {
        statusBox = new ImageBox();
        statusBox.setBounds(LobberConstants.STATUS_X_POS, LobberConstants.STATUS_Y_POS,
                LobberConstants.STATUS_WIDTH, LobberConstants.STATUS_HEIGHT);
        localContainer.add(statusBox);
        statusBox.setVisible(true);
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
        switch (status)
        {
            case LobberState.OPPONENT_WON:
                statusBox.setImage(LobberConstants.OPPONENT_WON_STATUS_IMAGE);
                break;
            case LobberState.PLAYER_THINKING:
                statusBox.setImage(LobberConstants.PLAYER_THINKING_STATUS_IMAGE);
                break;
            case LobberState.PLAYER_WON:
                statusBox.setImage(LobberConstants.PLAYER_WON_STATUS_IMAGE);
                break;
            case LobberState.WAITING_FOR_OPPONENT:
                statusBox.setImage(LobberConstants.WAITING_FOR_OPPONENT_STATUS_IMAGE);
                break;
            case LobberState.GAME_DRAWN:
                statusBox.setImage(LobberConstants.GAME_DRAWN_STATUS_IMAGE);
                break;
            case LobberState.WELCOME_PLAYER:
                statusBox.setImage(LobberConstants.WELCOME_AND_WAIT_STATUS_IMAGE);
                break;
        }
        updateHelpText(status);
    }

    private void updateHelpText(int status)
    {
        switch (status)
        {
            case LobberState.OPPONENT_WON:
            case LobberState.PLAYER_WON:
            case LobberState.GAME_DRAWN:
                helpText.setImage(LobberConstants.HELP_IMAGE_PLAY_OVER);
                break;
            case LobberState.PLAYER_THINKING:
            case LobberState.WAITING_FOR_OPPONENT:
                helpText.setImage(LobberConstants.HELP_IMAGE_PLAYING);
                break;
            case LobberState.WELCOME_PLAYER:
                helpText.setImage(LobberConstants.HELP_IMAGE_START);
                break;
        }
    }

    public void reset(boolean difficultyLevelChange)
    {
        statusBox.setImage(LobberConstants.WELCOME_AND_WAIT_STATUS_IMAGE);
        grid.reset(difficultyLevelChange);
    }

    public void showUI()
    {
        this.setVisible(true);
        this.addKeyListener(listener);
        this.requestFocus();
    }

    private void createHelpDisplay()
    {
        helpText = new ImageBox();
        helpText.setBounds(LobberConstants.HELPTEXT_X_POS, LobberConstants.HELPTEXT_Y_POS,
                LobberConstants.HELPTEXT_WIDTH, LobberConstants.HELPTEXT_HEIGHT);
        helpText.setImage(LobberConstants.HELP_IMAGE_START);
        localContainer.add(helpText);
        helpText.setVisible(true);
    }

    private void createAdvtSlideShow()
    {
        SlideshowGadget slideShow = new SlideshowGadget();
        slideShow.setBounds(LobberConstants.ADVT_X_POS, LobberConstants.ADVT_Y_POS,
                LobberConstants.ADVT_WIDTH, LobberConstants.ADVT_HEIGHT);
        slideShow.setDelay(10000);
        slideShow.setSlides(LobberConstants.ADVT_SLIDESHOW_IMAGES);
        localContainer.add(slideShow);
        slideShow.startSlideShow();
        slideShow.setVisible(true);
    }

    private void createAppTitle()
    {
        appTitle = new ImageBox();
        appTitle.setBounds(LobberConstants.APP_TITLE_X_POS, LobberConstants.APP_TITLE_Y_POS,
                LobberConstants.APP_TITLE_WIDTH, LobberConstants.APP_TITLE_HEIGHT);
        appTitle.setImage(LobberConstants.APP_TITLE_IMAGE);
        localContainer.add(appTitle);
        appTitle.setVisible(true);
    }
}
