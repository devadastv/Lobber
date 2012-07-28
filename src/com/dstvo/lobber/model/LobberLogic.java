/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dstvo.lobber.model;

import com.dstvo.lobber.LobberConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

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
        topBoundary = LobberConstants.ROW_COUNT - 1;
        leftBoundary = LobberConstants.COLUMN_COUNT - 1;
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
            if (row >= 0 && row < LobberConstants.ROW_COUNT && column >= 0 && column < LobberConstants.COLUMN_COUNT)
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
            if (row >= 0 && row < LobberConstants.ROW_COUNT && column >= 0 && column < LobberConstants.COLUMN_COUNT)
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
            if (bottomBounadry >= LobberConstants.ROW_COUNT)
            {
                bottomBounadry = LobberConstants.ROW_COUNT - 1;
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
            if (rightBoundary >= LobberConstants.COLUMN_COUNT)
            {
                rightBoundary = LobberConstants.COLUMN_COUNT - 1;
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
                    int score = getRating(position, playGrid);
                    
                    if (score > bestRating)
                    {
                        bestRating = score;
                        bestPositionList.clear();
                        bestPositionList.add(position);
                        System.out.println("For position " + position + ", score is " + score + " white bestScore WAS " + bestRating);
                    } else if (score == bestRating)
                    {
                        System.out.println("For position " + position + ", score is " + score + " same as best score");
                        bestPositionList.add(position);
                    }
                }
            }
        }
        System.out.println("The best position is " + bestPositionList.get(0));
        return (GridPosition) bestPositionList.get(0);
    }

    int getRating(GridPosition position, byte[][] playGrid)
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
            int playerScore = chipsCount(line, CellContent.PLAYER_CELL) + 1;
            int opponentScore = chipsCount(line, CellContent.OPPONENT_CELL);
            if (playerScore > opponentScore)
            {
                weightage[direction] = playerScore;
            } else
            {
                weightage[direction] = opponentScore;
            }
        }

        /*
         * Now we have the weightage value for each direction for the selected cell.
         * Now sort this from max to min within the array so that the cumulative
         * weightage for the selected cell can be calculated
         */
        for (int i = 0; i < 3; i++)
        {
            for (int j = i + 1; j < 4; j++)
            {
                if (weightage[i] < weightage[j])
                {
                    int temp = weightage[i];
                    weightage[i] = weightage[j];
                    weightage[j] = temp;
                }
            }
        }
//        Arrays.sort(weightage);
//        return weightage[3] * 64 + weightage[2] * 16 + weightage[1] * 4 + weightage[0];
        /*
         * Some weightage value is given to each direction weightages to get the final rating
         * for the selected cell
         */
        return weightage[0] * 64 + weightage[1] * 16 + weightage[2] * 4 + weightage[3];
    }

    int chipsCount(int line[], int player)
    {
        int best = 0;
        for (int i = 1; i < 6; i++)
        {
            int last;
            int gaps;
            int isNear;
            int first = last = gaps = isNear = 0;
            int s;
            for (s = i; gaps < 4 && s < i + 4; s++)
            {
                if (line[s] == 0)
                {
                    gaps++;
                } else if (line[s] != player)
                {
                    gaps = 4;
                } else
                {
                    last = s;
                    if (first == 0)
                    {
                        first = s;
                    }
                    isNear = isNear == 0 && s != 4 && s != 5 ? 0 : 1;
                }
            }

            //More value if gaps are less with no opponent cells in the 4 cells selected earlier
            s = (4 - gaps) * (4 - gaps);

            // +1 if the player's cells are adjacent in the 4 cells selected earlier
            if (last - first < 4 - gaps)
            {
                s++;
            }

            //+1 if the cell to be played is near to any of his own cells
            // cells 4 and 5 in the line comes adjacent to the selected cell
            if (isNear != 0)
            {
                s++;
            }

            // +4 if the cells before and after the 4 earlier selected cells are either
            // the players own cell or empty
            if ((line[i - 1] == 0 || line[i - 1] == player) && (line[i + 4] == 0 || line[i + 4] == player))
            {
                s += 4;
            }
            if (s > best)
            {
                best = s;
            }
        }

        return best;
    }
}
