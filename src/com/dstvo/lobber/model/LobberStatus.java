/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dstvo.lobber.model;

/**
 *
 * @author user
 */
public interface LobberStatus
{
    int PLAYER_WON = 1;
    int OPPONENT_WON = 2;
    int PLAYER_THINKING = 3;
    int WAITING_FOR_OPPONENT = 4;
}
