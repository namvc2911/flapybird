package com.vunam.bacground;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import com.vunam.gui.GUI;

public class Ground {
    private int   x;
    private int   y;
    private int   width;
    private int   height;
    private Image imgageNen;
    private int   speed = 3;

    public Ground(int x, int y, int width, int height, Image imgageNen) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.imgageNen = imgageNen;
    }

    public void drawMatDat(Graphics2D g2d) {
        g2d.drawImage(imgageNen, x, y, width, height, null);
    }

    public void moveMatDat(int time) {
        if (time % speed != 0) {
            return;
        }
        x -= 1;
        if (x == -GUI.WIDTH_FRAME) {
            x = GUI.WIDTH_FRAME;
        }
    }

    public int getX() {
        return x;
    }

    public int getY(){
        return y;
    }

    public Rectangle getRectMatDat() {
        Rectangle rect = new Rectangle(x, y, width,height);
        return rect;
    }
}
