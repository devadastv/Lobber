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
import org.dvb.event.EventManager;
import org.dvb.event.UserEventRepository;
import org.havi.ui.event.HRcEvent;

/**
 *
 * @author Devadas.Vijayan
 */
public class LobberApp implements Xlet
{
    LobberController controller;
    
    private void startApp()
    {
        LobberView view = new LobberView();
        LobberModel model = new LobberModel(view);
        controller = new LobberController();
        view.setKeyListener(controller);
        controller.setModel(model);
        addUserEventListener();
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
        EventManager.getInstance().removeUserEventListener(controller);
    }

    private void addUserEventListener()
    {
        UserEventRepository eventRepo = new UserEventRepository("LobberKeys");
        eventRepo.addAllArrowKeys();
        eventRepo.addKey(HRcEvent.VK_UP);
        eventRepo.addKey(HRcEvent.VK_DOWN);
        eventRepo.addKey(HRcEvent.VK_RIGHT);
        eventRepo.addKey(HRcEvent.VK_LEFT);
        eventRepo.addKey(HRcEvent.VK_ENTER);
        eventRepo.addKey(HRcEvent.VK_1);
        eventRepo.addKey(HRcEvent.VK_7);
        eventRepo.addKey(HRcEvent.VK_8);
        eventRepo.addKey(HRcEvent.VK_9);
        EventManager.getInstance().addUserEventListener(controller, eventRepo);
    }
}
