/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dstvo.lobber.model;

/**
 *
 * @author user
 */
public interface IGridModel
{
    void moveRight();

    void moveLeft();

    void moveDown();

    void moveUp();

    void processSelection();

    void initialize();
}
