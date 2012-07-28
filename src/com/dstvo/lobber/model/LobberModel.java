/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dstvo.lobber.model;

import com.dstvo.lobber.LobberConstants;
import com.dstvo.lobber.view.LobberView;

/**
 *
 * @author user
 */
public class LobberModel implements IGridModel
{
    
    LobberView view;
    byte[][] playGrid;
    GridPosition currentOpponentPos;
    LobberLogic logic = new LobberLogic();

    public LobberModel(LobberView view)
    {
        this.view = view;
        view.initGui();
        playGrid = new byte[LobberConstants.ROW_COUNT][LobberConstants.COLUMN_COUNT];
        currentOpponentPos = new GridPosition(LobberConstants.ROW_COUNT / 2, LobberConstants.COLUMN_COUNT / 2);
    }

    public void initialize()
    {
        shiftFocusToCell(currentOpponentPos);
    }

    public void moveRight()
    {
        if (currentOpponentPos.getColumn() < LobberConstants.COLUMN_COUNT - 1)
        {
            currentOpponentPos.setColumn(currentOpponentPos.getColumn() + 1);
        }
        shiftFocusToCell(currentOpponentPos);
    }

    public void moveLeft()
    {
        if (currentOpponentPos.getColumn() > 0)
        {
            currentOpponentPos.setColumn(currentOpponentPos.getColumn() - 1);
        }
        shiftFocusToCell(currentOpponentPos);
    }

    public void moveDown()
    {
        if (currentOpponentPos.getRow() < LobberConstants.ROW_COUNT - 1)
        {
            currentOpponentPos.setRow(currentOpponentPos.getRow() + 1);
        }
        shiftFocusToCell(currentOpponentPos);
    }

    public void moveUp()
    {
        if (currentOpponentPos.getRow() > 0)
        {
            currentOpponentPos.setRow(currentOpponentPos.getRow() - 1);
        }
        shiftFocusToCell(currentOpponentPos);
    }

    public void processSelection()
    {
        if (playGrid[currentOpponentPos.getRow()][currentOpponentPos.getColumn()] == CellContent.NON_FILLED_CELL)
        {
            /*
             * 1. Check if opponent won. If so, display message and give options
             * 2. If not, player to find the best move and play it. Update the field.
             * 3. Check if player won, display message and give options
             * 4. Else, wait for opponent to play
             */
            selectCell(currentOpponentPos, CellContent.OPPONENT_CELL);
            boolean opponentWinStatus = checkWinStatus(CellContent.OPPONENT_CELL, currentOpponentPos);
            if (opponentWinStatus)
            {
                System.out.println("OPPONENT WON......");
            } else
            {
                GridPosition playerPosition = getBestMove();
                selectCell(playerPosition, CellContent.PLAYER_CELL);
                boolean playerWinStatus = checkWinStatus(CellContent.PLAYER_CELL, playerPosition);
                if (playerWinStatus)
                {
                    System.out.println("PLAYER WON......");
                }
            }
        }
    }

    // NOTE: DUMMY IMPLEMENTATION
    private GridPosition getBestMove()
    {
        return logic.getBestMove(playGrid);
    }

    private void shiftFocusToCell(GridPosition position)
    {
        view.shiftFocusToCell(position);
    }

    private void updateLobberStatus(int lobberStatus)
    {
        view.updateLobberStatus(lobberStatus);
    }

    private void selectCell (GridPosition position, byte cellValue)
    {
        playGrid[position.getRow()][position.getColumn()] = cellValue;
        view.selectCell(position, cellValue);
        logic.updateBoundary(position);
    }
    
    

    private boolean checkWinStatus(byte playerToCheck, GridPosition positionToCheck)
    {
        return (logic.testWin(playGrid, positionToCheck) == playerToCheck);
    }
}
