/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dstvo.lobber.view;

import com.dstvo.lobber.model.GridPosition;
import com.dstvo.lobber.LobberConstants;
import com.dstvo.lobber.LobberUtil;
import com.dstvo.lobber.model.CellContent;
import java.awt.Container;

/**
 *
 * @author user
 */
public class LobberGrid extends Container
{
    private LobberCell[][] cells;
    private LobberCell lastPlayerSelectedCell;
    private LobberCell lastOpponentSelectedCell;
    private LobberCell lastFocusCell;

    void initializeCells()
    {
        cells = new LobberCell[LobberUtil.getRowCount()][LobberUtil.getColumnCount()];
        int cellWidth = getWidth() / LobberUtil.getColumnCount();
        int cellHeight = getHeight() / LobberUtil.getRowCount();
        int cellY_Pos, cellX_Pos;
        System.out.println("cells.length = " + cells.length);
        for (int row = 0; row < cells.length; row++) //Each row
        {
            cellY_Pos = cellHeight * row;
            System.out.println("cells[row].length = " + cells[row].length);
            for (int column = 0; column < cells[row].length; column++)
            {
                cellX_Pos = cellWidth * column;
                LobberCell cell = cells[row][column] = new LobberCell();
                cell.setBounds(cellX_Pos, cellY_Pos, cellWidth - 1, cellHeight - 1);
                add(cell);
                cell.setVisible(true);
            }
        }
    }

    

    void shiftFocusToCell(GridPosition currentPos)
    {
       LobberCell currentFocusCell = cells[currentPos.getRow()][currentPos.getColumn()];
       currentFocusCell.setHighlighted(true);
       if ((null != lastFocusCell) && (lastFocusCell != currentFocusCell))
       {
           lastFocusCell.setHighlighted(false);
       }
       lastFocusCell = currentFocusCell;
    }

    void selectCell(GridPosition currentPos, byte cellValue)
    {
        LobberCell currentSelectedCell = cells[currentPos.getRow()][currentPos.getColumn()];
        currentSelectedCell.setCellValue(cellValue);
        currentSelectedCell.setSelected(true);
        switch (cellValue)
        {
            case CellContent.OPPONENT_CELL:
                if (null != lastOpponentSelectedCell)
                {
                    lastOpponentSelectedCell.setSelected(false);
                }
                lastOpponentSelectedCell = currentSelectedCell;
                break;
            case CellContent.PLAYER_CELL:
                if (null != lastPlayerSelectedCell)
                {
                    lastPlayerSelectedCell.setSelected(false);
                }
                lastPlayerSelectedCell = currentSelectedCell;
                break;
        }
    }

    void reset(boolean difficultyLevelChange)
    {
        if (difficultyLevelChange)
        {
            this.removeAll();
            initializeCells();
            repaint();
        } else
        {
            for (int row = 0; row < cells.length; row++)
            {
                for (int column = 0; column < cells[row].length; column++)
                {
                    LobberCell cell = cells[row][column];
                    cell.setCellValue(CellContent.NON_FILLED_CELL);
                    cell.setSelected(false);
                    cell.setHighlighted(false);
                }
            }
        }
    }
}
