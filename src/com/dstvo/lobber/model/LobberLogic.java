/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dstvo.lobber.model;

import com.dstvo.lobber.LobberConstants;
import com.dstvo.lobber.LobberUtil;
import java.util.ArrayList;
import java.util.Arrays;

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
    private int topBoundary, bottomBounadry, leftBoundary, rightBoundary;

    public LobberLogic()
    {
        topBoundary = LobberUtil.getRowCount() - 1;
        leftBoundary = LobberUtil.getColumnCount() - 1;
        bottomBounadry = 0;
        rightBoundary = 0;
    }

    public byte testWin(byte[][] playGrid, GridPosition position)
    {
        byte currentPlayed = playGrid[position.getRow()][position.getColumn()];
        for (int direction = 0; direction < 4; direction++)
        {
            int[] line = createLine(playGrid, position, direction);
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

    private int[] createLine(byte[][] playGrid, GridPosition position, int direction)
    {
        int[] line = new int[LobberConstants.COUNT_FOR_WIN * 2];
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
            if (row >= 0 && row < LobberUtil.getRowCount() && column >= 0 && column < LobberUtil.getColumnCount())
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
            if (row >= 0 && row < LobberUtil.getRowCount() && column >= 0 && column < LobberUtil.getColumnCount())
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

    void updateBoundary(GridPosition position)
    {
        updateTopBoundary(position);
        updateBottomBoundary(position);
        updateLefyBoundary(position);
        updateRightBoundary(position);
    }

    private void updateTopBoundary(GridPosition position)
    {
        if (position.getRow() <= topBoundary)
        {
            topBoundary = position.getRow() - 1;
            if (topBoundary < 0)
            {
                topBoundary = 0;
            }
        }
    }

    private void updateBottomBoundary(GridPosition position)
    {
        if (position.getRow() >= bottomBounadry)
        {
            bottomBounadry = position.getRow() + 1;
            if (bottomBounadry >= LobberUtil.getRowCount())
            {
                bottomBounadry = LobberUtil.getRowCount() - 1;
            }
        }
    }

    private void updateLefyBoundary(GridPosition position)
    {
        if (position.getColumn() <= leftBoundary)
        {
            leftBoundary = position.getColumn() - 1;
            if (leftBoundary < 0)
            {
                leftBoundary = 0;
            }
        }
    }

    private void updateRightBoundary(GridPosition position)
    {
        if (position.getColumn() >= rightBoundary)
        {
            rightBoundary = position.getColumn() + 1;
            if (rightBoundary >= LobberUtil.getColumnCount())
            {
                rightBoundary = LobberUtil.getColumnCount() - 1;
            }
        }
    }

    GridPosition getBestMove(byte[][] playGrid)
    {
        int bestRating = 0;
        ArrayList bestPositionList = new ArrayList();
        System.out.println("\n\n\n==============================================");
        System.out.println("topBoundary = " + topBoundary);
        System.out.println("bottomBounadry = " + bottomBounadry);
        System.out.println("leftBoundary = " + leftBoundary);
        System.out.println("rightBoundary = " + rightBoundary);
        for (int row = topBoundary; row <= bottomBounadry; row++)
        {
            for (int column = leftBoundary; column <= rightBoundary; column++)
            {
                if (playGrid[row][column] == CellContent.NON_FILLED_CELL)
                {
                    GridPosition position = new GridPosition(row, column);
                    System.out.println("Inside getpositionRating for " + position);
                    int positionRating = getPositionRating(position, playGrid);
                    if (positionRating > bestRating)
                    {
                        bestRating = positionRating;
                        bestPositionList.clear();
                        bestPositionList.add(position);
                        System.out.println("For position " + position + ", score is " + positionRating + " white bestScore WAS " + bestRating);
                    } else if (positionRating == bestRating)
                    {
                        System.out.println("For position " + position + ", score is " + positionRating + " same as best score");
                        bestPositionList.add(position);
                    }
                }
            }
        }
        System.out.println("The best position is " + bestPositionList.get(0));
        return (GridPosition) bestPositionList.get(0);
    }

    int getPositionRating(GridPosition position, byte[][] playGrid)
    {
        int weightage[] = new int[4];

        /*
         * Weightage is calculated for each player on each direction for celected cell.
         * The final weightage is counted as the highest of weightage among player
         * and its opponent. (Logic is, if opponent has a high weightage on the
         * selected cell, the player has to block it by setting it as high weightage for himself)
         *
         */
        for (int direction = 0; direction < 4; direction++)
        {
            //Populate line for that cell in each direction
            int line[] = createLine(playGrid, position, direction);
            int playerLineScore = getBestScoreFromLine(line, CellContent.PLAYER_CELL) + 1;
            int opponentLineScore = getBestScoreFromLine(line, CellContent.OPPONENT_CELL);
            weightage[direction] = playerLineScore > opponentLineScore ? playerLineScore : opponentLineScore;
        }

        /*
         * Now we have the weightage value for each direction for the selected cell.
         * Now sort this from max to min within the array so that the cumulative
         * weightage for the selected cell can be calculated
         */
        Arrays.sort(weightage);
        /*
         * Some weightage value is given to each direction weightages to get the final rating
         * for the selected cell
         */
        return weightage[3] * 1000 + weightage[2] * 100 + weightage[1] * 10 + weightage[0];
    }

    int getBestScoreFromLine(int line[], int playerCellValue)
    {
        int bestScore = 0;

        // Iterate thru the line of length 10 in sections of 4. (ie, first section is 1-4,
        // then 2-5 then 3-6 and so on). Get the best scorer in this set of 4 based on below criterias.
        // Note that first one and last one in line are not counted for creating row
        for (int sectionStart = 1; sectionStart < (LobberConstants.COUNT_FOR_WIN + 1); sectionStart++) // N + 1
        {
            int firstOccurance = 0;
            int lastOccurance = 0;
            int gaps = 0;
            int isNear = 0;
            for (int i = sectionStart; i < sectionStart + (LobberConstants.COUNT_FOR_WIN - 1); i++)
            {
                // Add one gap for each non_filled cell
                if (line[i] == CellContent.NON_FILLED_CELL)
                {
                    gaps++;
                } // If there is an opponent cell or out_of_bounds cell in the section, make the gap as 4.
                // This reduces the score
                else if (line[i] != playerCellValue)
                {
                    gaps = (LobberConstants.COUNT_FOR_WIN - 1);
                    break;
                } // In case of the cell is the current player's cell, populate last and first occurance in section
                // Also check if the cell in the line near to the cell under test is player's own cell
                else
                {
                    firstOccurance = i;
                    if (lastOccurance == 0)
                    {
                        lastOccurance = i;
                    }
                    isNear = ((i == (LobberConstants.COUNT_FOR_WIN - 1))
                            || (i == LobberConstants.COUNT_FOR_WIN)) ? 1 : 0;
                }
            }

            //More value if gaps are less with no opponent cells in the 4 cells selected earlier
            int score = ((LobberConstants.COUNT_FOR_WIN - 1) - gaps) * ((LobberConstants.COUNT_FOR_WIN - 1) - gaps);

            // +1 if the player's cells are adjacent in the 4 cells selected earlier
            if (firstOccurance - lastOccurance < (LobberConstants.COUNT_FOR_WIN - 1) - gaps) //N-1
            {
                score++;
            }

            //+1 if the cell to be played is near to any of his own cells
            // cells 4 and 5 in the line comes adjacent to the selected cell
            if (isNear != 0)
            {
                score++;
            }

            // +4 if the cells before and after the 4 earlier selected cells are either
            // the players own cell or empty
            if ((line[sectionStart - 1] == 0
                    || line[sectionStart - 1] == playerCellValue)
                    && (line[sectionStart + (LobberConstants.COUNT_FOR_WIN - 1)] == 0
                    || line[sectionStart + (LobberConstants.COUNT_FOR_WIN - 1)] == playerCellValue))
            {
                score += (LobberConstants.COUNT_FOR_WIN - 1);
            }
            if (score > bestScore)
            {
                bestScore = score;
            }
        }
        return bestScore;
    }

    void reset()
    {
        topBoundary = LobberUtil.getRowCount() - 1;
        leftBoundary = LobberUtil.getColumnCount() - 1;
        bottomBounadry = 0;
        rightBoundary = 0;
    }
}
