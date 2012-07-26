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
    boolean isHighlighted;
    boolean isSelected;
    int cellValue; //CellContent
    Color cellColor; // Original bg Color

    public LobberCell()
    {
        cellValue = CellContent.NON_FILLED_CELL;
    }

    public void setBackground(Color backgroundColor)
    {
        cellColor = backgroundColor;
        super.setBackground(backgroundColor);
    }

    public void setHighlighted(boolean highlightStatus)
    {
        this.isHighlighted = highlightStatus;
        if (isHighlighted)
        {
            super.setBackground(LobberConstants.FOCUSED_CELL_COLOR);
        } else
        {
            super.setBackground(cellColor);
        }
    }

    public void setSelected(boolean selectStatus)
    {
        this.isSelected = selectStatus;
        if (isSelected)
        {
            setText("*" + cellValue);
        } else
        {
            setText("" + cellValue);
        }

    }

    public void setCellValue(int cellValue)
    {
        this.cellValue = cellValue;
        setText("" + cellValue);
    }
}
