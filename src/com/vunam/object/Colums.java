package com.vunam.object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import com.vunam.gui.GUI;

public class Colums {
    private int x;
    private int yUp, yDown;
    private int width, heightUp, heightDown;
    private Image imgUp, imgDown;
    public static final int   SPEED = -3;

    public int getX() {
        return x;
    }

    public int getyUp() {
        return yUp;
    }

    public int getyDown() {
        return yDown;
    }

    public Image getImgUp() {
        return imgUp;
    }

    public Image getImgDown() {
        return imgDown;
    }

    public Colums(int x, int yUp, int yDown, int width, int heightUP,
            int heightDown, Image imgUp, Image imgDown) {
        this.x = x;
        this.yUp = yUp;
        this.yDown = yDown;
        this.width = width;
        this.heightUp = heightUP;
        this.heightDown = heightDown;
        this.imgUp = imgUp;
        this.imgDown = imgDown;
    }

    public void drawColums(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.drawImage(imgUp, x, yUp, width, heightUp, null);
        g2d.drawImage(imgDown, x, yDown, width, heightDown, null);
    }

    public void moveColums(int time){

        if (time % SPEED != 0) {
            return;
        }
        x -=1;
        if (x == -90) {
            x = GUI.WIDTH_FRAME + 3000;
        }
    }

    public Rectangle getRectColumTop() {
        Rectangle rectOngTren = new Rectangle(x+2, yUp, width-10,heightUp-10);
        return rectOngTren;
    }

    public Rectangle getRectBoth() {
        Rectangle rectOngDuoi = new Rectangle(x+2, yDown+5,width-10,heightDown-10);
        return rectOngDuoi;
    }
}
