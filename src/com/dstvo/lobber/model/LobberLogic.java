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

    public byte testWin(byte[][] playGrid, GridPosition position)
    {
        byte currentPlayed = playGrid[position.getRow()][position.getColumn()];
        for (int direction = 0; direction < 4; direction++)
        {
            byte[] line = createLine(playGrid, position, direction);
//            printLine(line, direction); //Debugging
            int counter = 0;
            for (int i = 1; i < (line.length - 1); i++) // Means, loop will skip first and last elements
            {
                if (line[i] == currentPlayed)
                {
                    if (++counter == (LobberConstants.COUNT_FOR_WIN - 1))
                    {
                        return currentPlayed;
                    }
                } else
                {
                    counter = 0;
                }
            }
        }
        return 0;
    }

    private byte[] createLine(byte[][] playGrid, GridPosition position, int direction)
    {
        byte[] line = new byte[LobberConstants.COUNT_FOR_WIN * 2];
        int rowDiff = 0, colDiff = 0;
        switch (direction)
        {
            case HORIZONTAL_DIRECTION:
                rowDiff = 0;
                colDiff = 1;
                break;
            case VERTICAL_DIRECTION:
                rowDiff = 1;
                colDiff = 0;
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
        
        int row = position.getRow();
        int column = position.getColumn();
        
        //Filling first five elements of line (index 0 to 4)
        for (int i = 0; i < LobberConstants.COUNT_FOR_WIN; i++)
        {
            row -= rowDiff;
            column -= colDiff;
            if (row >= 0 && row < LobberConstants.ROW_COUNT && column >= 0 && column < LobberConstants.COLUMN_COUNT)
            {
                line[LobberConstants.COUNT_FOR_WIN - (i + 1)] = playGrid[row][column];
            } else
            {
                line[LobberConstants.COUNT_FOR_WIN - (i + 1)] = CellContent.OUT_OF_BOUND_CELL;
            }
        }

        row = position.getRow();
        column = position.getColumn();
        //Filling last five elements of line (index 5 to 9)
        for (int i = 0; i < LobberConstants.COUNT_FOR_WIN; i++)
        {
            row += rowDiff;
            column += colDiff;
            if (row >= 0 && row < LobberConstants.ROW_COUNT && column >= 0 && column < LobberConstants.COLUMN_COUNT)
            {
                line[LobberConstants.COUNT_FOR_WIN + i] = playGrid[row][column];
            } else
            {
                line[LobberConstants.COUNT_FOR_WIN + i] = CellContent.OUT_OF_BOUND_CELL;
            }
        }
        return line;
    }

    private void printLine(byte[] line, int direction)
    {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < line.length; i++)
        {
            byte b = line[i];
            buffer.append(b);
            buffer.append(" ");
        }
        System.out.println("Line in direction " + direction + " is " + buffer.toString());
    }
}
