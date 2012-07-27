/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dstvo.lobber.model;

/**
 *
 * @author Devadas.Vijayan
 */
public interface CellContent
{
    byte OPPONENT_CELL = 1;
    byte PLAYER_CELL = 2;
    byte NON_FILLED_CELL = 0; // This is the value on which the byte array is initialized and no need to assign a value for each byte
    byte OUT_OF_BOUND_CELL = 3;
}
