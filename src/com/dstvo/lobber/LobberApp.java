/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dstvo.lobber;

import com.dstvo.lobber.view.LobberView;
import com.dstvo.lobber.model.LobberModel;
import com.dstvo.lobber.controller.LobberController;

/**
 *
 * @author Devadas.Vijayan
 */
public class LobberApp
{
    int ROW_COLUMN_COUNT = 10;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        new LobberApp().startApp();
    }

    private void startApp()
    {
        LobberView view = new LobberView();
        LobberModel model = new LobberModel(view);
        LobberController controller = new LobberController();
        view.setKeyListener(controller);
        controller.setModel(model);
        controller.startPlay();
    }
}
