package com.vunam.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import com.vunam.chuyendulieu.ActionNewGameAndExit;

public class BacgroundMainGame extends BaseContainer implements MouseListener {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Image                backgroundFirst;
    private ImageIcon            icon;
    private ActionNewGameAndExit actionNewGameAndExit;

    public void setShowPlayGame(ActionNewGameAndExit actionNewGameAndExit) {
        this.actionNewGameAndExit = actionNewGameAndExit;
    }

    @Override
    public void initContainer() {
        icon = new ImageIcon(getClass()
                .getResource("/images/backgroundgame.jpg"));
        backgroundFirst = icon.getImage();
        addMouseListener(this);
    }

    @Override
    public void intiComps() {

    }

    @Override
    public void addComps() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(backgroundFirst, 0, 0, GUI.WIDTH_FRAME, GUI.HEIGHT_FRAME,
                null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {   
        actionNewGameAndExit.showPlayGame();
        PlayGame.GAME_START = true;
     
      

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

}
