/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dstvo.lobber.view;

import com.dstvo.lobber.model.GridPosition;
import com.dstvo.lobber.LobberConstants;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.KeyListener;

/**
 *
 * @author user
 */
public class LobberView extends Frame
{
    LobberGrid grid;

    public void initGui()
    {
        this.setBounds(LobberConstants.APP_X_POS, LobberConstants.APP_Y_POS,
                LobberConstants.APP_WIDTH, LobberConstants.APP_HEIGHT);
        this.setBackground(Color.black);
        createLobberGrid();
        this.setResizable(false);
        this.setVisible(true);
        this.requestFocus();
    }

//
//    public void displayPlayField(byte[][] playGrid, GridPosition currentOpponentPosition)
//    {
//        StringBuffer buffer = new StringBuffer();
//        for (int i = 0; i < playGrid.length; i++)
//        {
//            if (buffer.length() > 0)
//            {
//                buffer.delete(0, buffer.length());
//            }
//            byte[] bs = playGrid[i];
//            for (int j = 0; j < bs.length; j++)
//            {
//                byte b = bs[j];
//                buffer.append(b);
//                if (i == currentOpponentPosition.row && j == currentOpponentPosition.column)
//                {
//                    buffer.append("*  ");
//                } else
//                {
//                    buffer.append("   ");
//                }
//            }
//            System.out.println(buffer.toString());
//        }
//    }

//    public void updateField(byte[][] playGrid, GridPosition currentOpponentPosition, GridPosition lastPlayerPosition, GridPosition lastOpponentPosition)
//    {
//        grid.updateCells(playGrid, currentOpponentPosition, lastPlayerPosition, lastOpponentPosition);
//    }

    public void setKeyListener(KeyListener keyListener)
    {
        this.addKeyListener(keyListener);
    }

    private void createLobberGrid()
    {
        grid = new LobberGrid();
        grid.setBounds(LobberConstants.GRID_LEFT_OFFSET,
                LobberConstants.GRID_TOP_OFFSET,
                getWidth() - (LobberConstants.GRID_LEFT_OFFSET + LobberConstants.GRID_RIGHT_OFFSET),
                getHeight() - (LobberConstants.GRID_TOP_OFFSET + LobberConstants.GRID_BOTTOM_OFFSET));
        System.out.println("Frame Bounds is " + getBounds());
        System.out.println("Grid Bounds is " + grid.getBounds());
        grid.initializeCells();
        grid.setVisible(true);
        this.add(grid);
    }

    public void shiftFocusToCell(GridPosition position)
    {
        grid.shiftFocusToCell(position);
    }

    public void updateLobberStatus(int lobberStatus)
    {
        // TODO
    }

    public void selectCell(GridPosition position, byte cellValue)
    {
        grid.selectCell(position, cellValue);
    }

}
