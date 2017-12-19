package com.vunam.gui;
import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.vunam.chuyendulieu.GUIExit;

public class GUI extends JFrame implements GUIExit {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final int   HEIGHT_FRAME     = 600;
    public static final int   WIDTH_FRAME      = 800;

    private MyContainer       myContainer;

    private WindowAdapter     guiAdapter       = new WindowAdapter() {

                                                   @Override
                                                   public void windowClosing(
                                                           WindowEvent e) {
                                                       int resuilt = JOptionPane
                                                               .showConfirmDialog(
                                                                       null,
                                                                       "Ban Co Muon Thoat Hay Khong",
                                                                       "Thong Bao",
                                                                       JOptionPane.YES_NO_OPTION);
                                                       if (resuilt == JOptionPane.YES_OPTION) {
                                                           dispose();
                                                           PlayGame.IS_RUNNING=false;
                                                       }
                                                   }

                                               };

    public GUI() {
        initGUI();
        initComps();
        addComps();
    }

    private void initGUI() {
        this.setLayout(new CardLayout());
        this.setTitle("FlappyBird");
        this.setSize(WIDTH_FRAME, HEIGHT_FRAME);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    private void initComps() {
        myContainer = new MyContainer();
        myContainer.setExitGame(this);
    }

    private void addComps() {
        add(myContainer);
        addWindowListener(guiAdapter);
    }

    @Override
    public void exit() {
        dispose();

    }
}
