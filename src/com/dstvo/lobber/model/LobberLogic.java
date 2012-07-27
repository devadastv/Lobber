/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dstvo.lobber.model;

import com.dstvo.lobber.LobberConstants;

/**
 *
 * @author Devadas.Vijayan
 */
public class LobberLogic
{
    private static final int HORIZONTAL_DIRECTION = 0;
    private static final int VERTICAL_DIRECTION = 1;
    private static final int LEFT_TOP_DIAGONAL_DIRECTION = 2;
    private static final int RIGHT_TOP_DIAGONAL_DIRECTION = 3;

    public byte testWin(byte[] playGrid, GridPosition position)
    {
        for (int direction = 0; direction < 4; direction++)
        {
            byte[] line = createLine(playGrid, position, direction);

        }
        return 0;
    }

    private byte[] createLine(byte[] playGrid, GridPosition position, int direction)
    {
        byte[] line = new byte[LobberConstants.COUNT_FOR_WIN * 2];
        int rowDiff, colDiff;
        switch (direction)
        {
            case HORIZONTAL_DIRECTION:
                rowDiff = 1;
                colDiff = 0;
                break;
            case VERTICAL_DIRECTION:
                rowDiff = 0;
                colDiff = 1;
                break;
            case LEFT_TOP_DIAGONAL_DIRECTION:
                rowDiff = 1;
                colDiff = 1;
                break;
            case RIGHT_TOP_DIAGONAL_DIRECTION:
                rowDiff = 1;
                colDiff = -1;
                break;
        }
        //Populate top part of the line
        int row, column;
        for (int i = LobberConstants.COUNT_FOR_WIN; i >= 0; i--)
        {
            

        }
        return null;
    }
}
