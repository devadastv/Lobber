/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dstvo.lobber;

import java.awt.Color;

/**
 *
 * @author Devadas.Vijayan
 */
public interface LobberConstants
{
    //The row and column count for app
    int ROW_COUNT = 16;
    int COLUMN_COUNT = 16;
    
    // Win criteria
    int COUNT_FOR_WIN = 4;
    
    // Location of the app UI
    int APP_X_POS = 0;
    int APP_Y_POS = 0;
    int APP_WIDTH = 1280;
    int APP_HEIGHT = 720;
    
    // Location of the lobber grid within UI
    int GRID_X_POS = 300;
    int GRID_Y_POS = 50;
    int GRID_WIDTH = 600;
    int GRID_HEIGHT = 600;

    // Location of the lobber grid within UI
    int STATUS_X_POS = 950;
    int STATUS_Y_POS = 50;
    int STATUS_WIDTH = 300;
    int STATUS_HEIGHT = 200;

    // Status messages in application
    String PLAYER_WON_STATUS_MESSAGE = "YOU HAVE LOST THE GAME. TRY AGAIN";
    String OPPONENT_WON_STATUS_MESSAGE = "YOU HAVE WON THE GAME. WELL DONE";
    String GAME_DRAWN_STATUS_MESSAGE = "GAME IS DRAWN. LETS TRY AGAIN? ";
    String PLAYER_THINKING_STATUS_MESSAGE = "THINKING FOR THE MOVE...";
    String WELCOME_AND_WAIT_STATUS_MESSAGE = "WELCOME. PLEASE PLAY YOUR TURN...";
    String WAITING_FOR_OPPONENT_STATUS_MESSAGE = "WAITING FOR YOU TO PLAY...";
    
    Color FOCUSED_CELL_COLOR = Color.white;
}
