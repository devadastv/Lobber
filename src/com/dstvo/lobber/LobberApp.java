/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dstvo.lobber;

import com.dstvo.lobber.view.LobberView;
import com.dstvo.lobber.model.LobberModel;
import com.dstvo.lobber.controller.LobberController;
import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;

/**
 *
 * @author Devadas.Vijayan
 */
public class LobberApp implements Xlet
{
    private void startApp()
    {
        LobberView view = new LobberView();
        LobberModel model = new LobberModel(view);
        LobberController controller = new LobberController();
        view.setKeyListener(controller);
        controller.setModel(model);
        controller.startPlay();
    }

    public void initXlet(XletContext xc) throws XletStateChangeException
    {
    }

    public void startXlet() throws XletStateChangeException
    {
        startApp();
    }

    public void pauseXlet()
    {
    }

    public void destroyXlet(boolean bln) throws XletStateChangeException
    {
    }
}
