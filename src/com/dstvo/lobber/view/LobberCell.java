/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dstvo.lobber.view;

import com.dstvo.lobber.LobberConstants;
import com.dstvo.lobber.model.CellContent;
import com.dstvo.lobber.util.ImageCache;
import java.awt.Graphics;
import java.awt.Image;
import org.havi.ui.HComponent;

/**
 *
 * @author user
 */
public class LobberCell extends HComponent
{
    private Image image;
    boolean isHighlighted;
    boolean isSelected;
    int cellValue; //CellContent

    public LobberCell()
    {
        image = ImageCache.getImage(LobberConstants.NO_FILLED_NO_HIGHLIGHT);
    }

    void setHighlighted(boolean highlightStatus)
    {
        this.isHighlighted = highlightStatus;
        updateImage();
        this.repaint();
    }

    void setSelected(boolean selectStatus)
    {
        this.isSelected = selectStatus;
        updateImage();
        this.repaint();
    }

    public void setCellValue(int cellValue)
    {
        this.cellValue = cellValue;
        updateImage();
        this.repaint();
    }

    public void paint(Graphics g)
    {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
//        super.paint(g);
    }

    private void updateImage()
    {
        if (isHighlighted)
        {
            switch (cellValue)
            {
                case CellContent.PLAYER_CELL:
                    if (isSelected)
                    {
                        image = ImageCache.getImage(LobberConstants.PLAYER_1_HIGHLIGHTED_LAST_SELECTED);
                    } else
                    {
                        image = ImageCache.getImage(LobberConstants.PLAYER_1_HIGHLIGHTED);
                    }
                    break;
                case CellContent.OPPONENT_CELL:
                    if (isSelected)
                    {
                        image = ImageCache.getImage(LobberConstants.PLAYER_2_HIGHLIGHTED_LAST_SELECTED);
                    } else
                    {
                        image = ImageCache.getImage(LobberConstants.PLAYER_2_HIGHLIGHTED);
                    }
                    break;
                case CellContent.NON_FILLED_CELL:
                    image = ImageCache.getImage(LobberConstants.NO_FILLED_HIGHLIGHTED);
                    break;
            }
        } else
        {
            switch (cellValue)
            {
                case CellContent.PLAYER_CELL:
                    if (isSelected)
                    {
                        image = ImageCache.getImage(LobberConstants.PLAYER_1_NO_HIGHLIGHT_LAST_SELECTED);
                    } else
                    {
                        image = ImageCache.getImage(LobberConstants.PLAYER_1_NO_HIGHLIGHT);
                    }
                    break;
                case CellContent.OPPONENT_CELL:
                    if (isSelected)
                    {
                        image = ImageCache.getImage(LobberConstants.PLAYER_2_NO_HIGHLIGHT_LAST_SELECTED);
                    } else
                    {
                        image = ImageCache.getImage(LobberConstants.PLAYER_2_NO_HIGHLIGHT);
                    }
                    break;
                case CellContent.NON_FILLED_CELL:
                    image = ImageCache.getImage(LobberConstants.NO_FILLED_NO_HIGHLIGHT);
                    break;
            }
        }
    }
}
