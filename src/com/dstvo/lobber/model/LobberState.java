/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dstvo.lobber.model;

/**
 *
 * @author user
 */
public interface LobberState
{
    int PLAYER_WON = 1;
    int OPPONENT_WON = 2;
    int PLAYER_THINKING = 3;
    int WAITING_FOR_OPPONENT = 4;
    int GAME_DRAWN = 5;
    int WELCOME_PLAYER = 6;
}
