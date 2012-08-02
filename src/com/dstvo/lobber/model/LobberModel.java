/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dstvo.lobber.model;

import com.dstvo.lobber.LobberConstants;
import com.dstvo.lobber.LobberUtil;
import com.dstvo.lobber.view.LobberView;

/**
 *
 * @author user
 */
public class LobberModel
{
    LobberView view;
    byte[][] playGrid;
    GridPosition currentOpponentPos;
    LobberLogic logic = new LobberLogic();
    private int state;

    public LobberModel(LobberView view)
    {
        this.view = view;
        view.initGui();
        playGrid = new byte[LobberUtil.getRowCount()][LobberUtil.getColumnCount()];
        currentOpponentPos = new GridPosition(LobberUtil.getRowCount() / 2, LobberUtil.getColumnCount() / 2);
    }

    public void start()
    {
        shiftFocusToCell(currentOpponentPos);
        view.showUI();
        displayStatus(LobberState.WELCOME_PLAYER);
    }

    public void moveRight()
    {
        if (state == LobberState.WELCOME_PLAYER || state == LobberState.WAITING_FOR_OPPONENT)
        {
            if (currentOpponentPos.getColumn() < LobberUtil.getColumnCount() - 1)
            {
                currentOpponentPos.setColumn(currentOpponentPos.getColumn() + 1);
            }
            shiftFocusToCell(currentOpponentPos);
        }
    }

    public void moveLeft()
    {
        if (state == LobberState.WELCOME_PLAYER || state == LobberState.WAITING_FOR_OPPONENT)
        {
            if (currentOpponentPos.getColumn() > 0)
            {
                currentOpponentPos.setColumn(currentOpponentPos.getColumn() - 1);
            }
            shiftFocusToCell(currentOpponentPos);
        }
    }

    public void moveDown()
    {
        if (state == LobberState.WELCOME_PLAYER || state == LobberState.WAITING_FOR_OPPONENT)
        {
            if (currentOpponentPos.getRow() < LobberUtil.getRowCount() - 1)
            {
                currentOpponentPos.setRow(currentOpponentPos.getRow() + 1);
            }
            shiftFocusToCell(currentOpponentPos);
        }

    }

    public void moveUp()
    {
        if (state == LobberState.WELCOME_PLAYER || state == LobberState.WAITING_FOR_OPPONENT)
        {
            if (currentOpponentPos.getRow() > 0)
            {
                currentOpponentPos.setRow(currentOpponentPos.getRow() - 1);
            }
            shiftFocusToCell(currentOpponentPos);
        }

    }

    public void processSelection()
    {
        if (state == LobberState.WELCOME_PLAYER || state == LobberState.WAITING_FOR_OPPONENT)
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
                    displayStatus(LobberState.OPPONENT_WON);
                    System.out.println("OPPONENT WON......");
                } else
                {
                    if (checkDrawnCondition())
                    {
                        displayStatus(LobberState.GAME_DRAWN);
                    } else
                    {
                        displayStatus(LobberState.PLAYER_THINKING);
                        GridPosition playerPosition = getBestMove();
                        selectCell(playerPosition, CellContent.PLAYER_CELL);
                        boolean playerWinStatus = checkWinStatus(CellContent.PLAYER_CELL, playerPosition);
                        if (playerWinStatus)
                        {
                            displayStatus(LobberState.PLAYER_WON);
                            System.out.println("PLAYER WON......");
                        } else
                        {
                            if (checkDrawnCondition())
                            {
                                displayStatus(LobberState.GAME_DRAWN);
                            } else
                            {
                                displayStatus(LobberState.WAITING_FOR_OPPONENT);
                            }

                        }
                    }
                }
            }
            checkDrawnCondition();
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

    private void selectCell(GridPosition position, byte cellValue)
    {
        playGrid[position.getRow()][position.getColumn()] = cellValue;
        view.selectCell(position, cellValue);
        logic.updateBoundary(position);
    }

    private boolean checkWinStatus(byte playerToCheck, GridPosition positionToCheck)
    {
        return (logic.testWin(playGrid, positionToCheck) == playerToCheck);
    }

    private void displayStatus(int status)
    {
        this.state = status;
        view.displayStatus(status);
    }

    public void reset(boolean difficultyLevelChange)
    {
        playGrid = new byte[LobberUtil.getRowCount()][LobberUtil.getColumnCount()];
        view.reset(difficultyLevelChange);
        logic.reset();
        currentOpponentPos = new GridPosition(LobberUtil.getRowCount() / 2, LobberUtil.getColumnCount() / 2);
        shiftFocusToCell(currentOpponentPos);
        displayStatus(LobberState.WELCOME_PLAYER);
    }

    private boolean checkDrawnCondition()
    {
        int status = LobberState.GAME_DRAWN;
        mainloop:
        for (int i = 0; i < playGrid.length; i++)
        {
            for (int j = 0; j < playGrid[i].length; j++)
            {
                if (playGrid[i][j] == CellContent.NON_FILLED_CELL)
                {
                    status = LobberState.WAITING_FOR_OPPONENT;
                    break mainloop;
                }
            }
        }
        if (status == LobberState.GAME_DRAWN)
        {
            return true;
        }
        return false;
    }
}
