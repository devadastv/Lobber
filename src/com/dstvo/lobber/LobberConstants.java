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
    int COUNT_FOR_WIN = 5;

    // Location of the app UI
    int APP_X_POS = 0;
    int APP_Y_POS = 0;
    int APP_WIDTH = 600;
    int APP_HEIGHT = 600;

    // Relative location of the lobber grid within UI
    int GRID_X_POS = 0;
    int GRID_Y_POS = 0;
    int GRID_WIDTH = 600;
    int GRID_HEIGHT = 600;

    int STATUS_DISPLAY_X_POS = 0;
    
    Color FOCUSED_CELL_COLOR = Color.white;
}
