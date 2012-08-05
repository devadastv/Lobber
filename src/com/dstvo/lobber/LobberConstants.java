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
    int LEVEL_EASY = 1; //10
    int LEVEL_MEDIUIM = 2;//12
    int LEVEL_DIFFICULT = 3;//16

    //The row and column count for app
    int EASY_LEVEL_ROW_COUNT = 10;
    int EASY_LEVEL_COLUMN_COUNT = 10;
    int MEDIUIM_LEVEL_ROW_COUNT = 12;
    int MEDIUIM_LEVEL_COLUMN_COUNT = 12;
    int DIFFICULT_LEVEL_ROW_COUNT = 16;
    int DIFFICULT_LEVEL_COLUMN_COUNT = 16;

    // Win criteria
    int COUNT_FOR_WIN = 5;

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
    int STATUS_X_POS = 30;
    int STATUS_Y_POS = 210;
    int STATUS_WIDTH = 240;
    int STATUS_HEIGHT = 150;

    // Location of the lobber grid within UI
    int HELPTEXT_X_POS = 30;
    int HELPTEXT_Y_POS = 390;
    int HELPTEXT_WIDTH = 240;
    int HELPTEXT_HEIGHT = 260;

    // Location of the lobber grid within UI
    int APP_TITLE_X_POS = 30;
    int APP_TITLE_Y_POS = 100;
    int APP_TITLE_WIDTH = 240;
    int APP_TITLE_HEIGHT = 75;
    
    // Location of the lobber grid within UI
    int ADVT_X_POS = 950;
    int ADVT_Y_POS = 300;
    int ADVT_WIDTH = 280;
    int ADVT_HEIGHT = 340;

    String APP_TITLE_IMAGE = "resources/AppTitle.png";

    String PLAYER_WON_STATUS_IMAGE = "resources/status_icons/Status_Lost.png";
    String OPPONENT_WON_STATUS_IMAGE = "resources/status_icons/Status_Won.png";
    String GAME_DRAWN_STATUS_IMAGE = "resources/status_icons/Status_Draw.png";
    String PLAYER_THINKING_STATUS_IMAGE = "resources/status_icons/Status_Thinking.png";
    String WELCOME_AND_WAIT_STATUS_IMAGE = "resources/status_icons/Status_Welcome.png";
    String WAITING_FOR_OPPONENT_STATUS_IMAGE = "resources/status_icons/Status_Waiting.png";
    
    
    // Images to show in cells on each scenarios
    String NO_FILLED_NO_HIGHLIGHT = "resources/cell_icons/NF_HN.png";
    String NO_FILLED_HIGHLIGHTED = "resources/cell_icons/NF_HY.png";
    String PLAYER_1_NO_HIGHLIGHT = "resources/cell_icons/P1_NS_HN.png";
    String PLAYER_1_NO_HIGHLIGHT_LAST_SELECTED = "resources/cell_icons/P1_LS_HN.png";
    String PLAYER_1_HIGHLIGHTED = "resources/cell_icons/P1_NS_HY.png";
    String PLAYER_1_HIGHLIGHTED_LAST_SELECTED = "resources/cell_icons/P1_LS_HY.png";
    String PLAYER_2_NO_HIGHLIGHT = "resources/cell_icons/P2_NS_HN.png";
    String PLAYER_2_NO_HIGHLIGHT_LAST_SELECTED = "resources/cell_icons/P2_LS_HN.png";
    String PLAYER_2_HIGHLIGHTED = "resources/cell_icons/P2_NS_HY.png";
    String PLAYER_2_HIGHLIGHTED_LAST_SELECTED = "resources/cell_icons/P2_LS_HY.png";

    String HELP_IMAGE_START = "resources/help_images/Help_Start.png";
    String HELP_IMAGE_PLAYING = "resources/help_images/Help_Playing.png";
    String HELP_IMAGE_PLAY_OVER = "resources/help_images/Help_GameOver.png";

    String[] ADVT_SLIDESHOW_IMAGES =
    {
        "resources/advt_slideshow/ad1.png", "resources/advt_slideshow/ad2.png",
        "resources/advt_slideshow/ad3.png", "resources/advt_slideshow/ad4.png",
        "resources/advt_slideshow/ad5.png", "resources/advt_slideshow/ad6.png",
        "resources/advt_slideshow/ad7.png", "resources/advt_slideshow/ad8.png",
        "resources/advt_slideshow/ad9.png", "resources/advt_slideshow/ad10.png"
    };
    Color FOCUSED_CELL_COLOR = Color.white;
}
