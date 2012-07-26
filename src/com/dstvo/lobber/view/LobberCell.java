/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dstvo.lobber.view;

import com.dstvo.lobber.LobberConstants;
import com.dstvo.lobber.model.CellContent;
import java.awt.Color;
import java.awt.Label;

/**
 *
 * @author user
 */
public class LobberCell extends Label
{
    public static int NORMAL_STATE = 1;
    public static int FOCUSED_STATE = 2;
    public static int PREVIOUSLY_SELECTED_STATE = 3;
    
    int cellValue; //CellContent
    int cellState; // Focused, previous selected or normal
    Color cellColor; // Original bg Color

    public LobberCell()
    {
        cellValue = CellContent.NON_FILLED_CELL;
        cellState = NORMAL_STATE;
    }


    public void setBackground(Color backgroundColor)
    {
        cellColor = backgroundColor;
        super.setBackground(backgroundColor);
    }

    public void setCellState(int cellState)
    {
        this.cellState = cellState;  //TODO: Is this variable required????
        if (cellState == FOCUSED_STATE)
        {
            super.setBackground(LobberConstants.FOCUSED_CELL_COLOR);
        } else if (cellState == PREVIOUSLY_SELECTED_STATE)
        {
            super.setBackground(cellColor); // Draw text in bold in general -
            //and in addition, blink text for some time for player selected cell
        } else {
            super.setBackground(cellColor);
        }
        repaint();
    }

    public void setCellValue(int cellValue)
    {
        this.cellValue = cellValue;
        setText("" + cellValue);
    }

}
