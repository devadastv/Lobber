/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dstvo.lobber;

/**
 *
 * @author user
 */
public class LobberUtil
{
    private static int difficultyLevel = LobberConstants.LEVEL_MEDIUIM;

    public static void setDifficultyLevel(int level)
    {
        difficultyLevel = level;
    }

    public static int getRowCount()
    {
        int rowCount = 0;
        switch (difficultyLevel)
        {
            case LobberConstants.LEVEL_EASY:
                rowCount = LobberConstants.EASY_LEVEL_ROW_COUNT;
                break;
            case LobberConstants.LEVEL_MEDIUIM:
                rowCount = LobberConstants.MEDIUIM_LEVEL_ROW_COUNT;
                break;
            case LobberConstants.LEVEL_DIFFICULT:
                rowCount = LobberConstants.DIFFICULT_LEVEL_ROW_COUNT;
                break;
        }
        return rowCount;
    }

    public static int getColumnCount()
    {
        int columnCount = 0;
        switch (difficultyLevel)
        {
            case LobberConstants.LEVEL_EASY:
                columnCount = LobberConstants.EASY_LEVEL_COLUMN_COUNT;
                break;
            case LobberConstants.LEVEL_MEDIUIM:
                columnCount = LobberConstants.MEDIUIM_LEVEL_COLUMN_COUNT;
                break;
            case LobberConstants.LEVEL_DIFFICULT:
                columnCount = LobberConstants.DIFFICULT_LEVEL_COLUMN_COUNT;
                break;
        }
        return columnCount;
    }
}
