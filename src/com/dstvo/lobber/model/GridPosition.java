/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dstvo.lobber.model;

/**
 *
 * @author user
 */
public class GridPosition
{

    private int row, column;

    public int getColumn()
    {
        return column;
    }

    public int getRow()
    {
        return row;
    }

    public void setColumn(int column)
    {
        this.column = column;
    }

    public void setRow(int row)
    {
        this.row = row;
    }

    
    public GridPosition(int row, int column)
    {
        this.row = row;
        this.column = column;
    }

    public String toString()
    {
        return "GridPosition [Row=" + row + ", Column=" + column + "]";
    }
}
