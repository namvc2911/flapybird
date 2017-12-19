package com.vunam.bacground;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import com.vunam.gui.GUI;

public class BackGround {
    private int   x;
    private int   y;
    private int   width, height;
    private Image imgBackground;

    private float speed = 3;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImgBackground() {
        return imgBackground;
    }

    public BackGround(int x, int y, int width, int height, Image imgBackground) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.imgBackground = imgBackground;
    }

    public void drawBackground(Graphics2D g) {
        g.drawImage(imgBackground, x, y, width, height, null);
    }

    public void moveBackground(int time) {
        if (time % speed != 0) {
            return;
        }
        x -= 1;
        if (x == -GUI.WIDTH_FRAME) {
            x = GUI.WIDTH_FRAME;
        }
    }

    public Rectangle getRectNen() {
        Rectangle rect = new Rectangle(x, y, width, height);
        return rect;
    }
}
