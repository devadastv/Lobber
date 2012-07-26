/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dstvo.lobber.view;

import com.dstvo.lobber.model.GridPosition;
import com.dstvo.lobber.LobberConstants;
import com.dstvo.lobber.model.CellContent;
import java.awt.Color;
import java.awt.Container;
import java.awt.Label;

/**
 *
 * @author user
 */
public class LobberGrid extends Container
{
    Label[][] cells;

    // TODO: Use layout instead of hard-coding bounds
    void initializeCells()
    {
        cells = new Label[LobberConstants.ROW_COUNT][LobberConstants.COLUMN_COUNT];
        int cellWidth = getWidth() / LobberConstants.ROW_COUNT;
        int cellHeight = getHeight() / LobberConstants.COLUMN_COUNT;
        int cellY_Pos, cellX_Pos;
        for (int row = 0; row < cells.length; row++) //Each row
        {
            cellY_Pos = cellHeight * row;
            for (int column = 0; column < cells[row].length; column++)
            {
                cellX_Pos = cellWidth * column;
                Label cell = cells[row][column] = new Label();
                cell.setBounds(cellX_Pos, cellY_Pos, cellWidth, cellHeight);
                cell.setBackground(new Color(row * 15, column * 15, 100));
                cell.setAlignment(Label.CENTER);
                add(cell);
                cell.setVisible(true);
            }
        }
    }

    // TODO: Modify logic to update only the changed cells
    public void updateCells(byte[][] cellValues, GridPosition currentOpponentPosition,
            GridPosition lastPlayerPosition, GridPosition lastOpponentPosition)
    {
        for (int row = 0; row < cellValues.length; row++)
        {
            for (int column = 0; column < cellValues[row].length; column++)
            {
                byte b = cellValues[row][column];
                System.out.println("b = " + b);
                if (b == CellContent.PLAYER_CELL)
                {
                    cells[row][column].setText("P");
                } else if (b == CellContent.OPPONENT_CELL)
                {
                    cells[row][column].setText("O");
                }
                if (currentOpponentPosition.getRow() == row && currentOpponentPosition.getColumn() == column)
                {
                    cells[row][column].setBackground(Color.white);
                }
                else
                {
                    cells[row][column].setBackground(new Color(row * 15, column * 15, 100));
                }
            }
        }
    }
}
