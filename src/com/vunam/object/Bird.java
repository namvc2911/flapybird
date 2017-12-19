package com.vunam.object;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Bird {
    public static final int BIRD_ORANGE       = 0;
    public static final int BIRD_VIOLET       = 1;
    public static final int BIRD_BLUE         = 2;
    public static final int GRAVITY           =2;
    public static final int TERMINAL_VELOCITY =2;
    public static final int       SPEED_DEFAULT    = 2;

    private int             x;
    private int             y;
    private int             size;
    private Image           imageBird;

    private Image[]         image             = new Image[] {
            new ImageIcon(getClass().getResource("/images/bird_orange.gif"))
                    .getImage(),
            new ImageIcon(getClass().getResource("/images/bird_violet.gif"))
                    .getImage(),
            new ImageIcon(getClass().getResource("/images/bird_blue.gif"))
                    .getImage()              };

    public Image getImageBird() {
        return imageBird;
    }

    public Bird(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        imageBird = image[BIRD_BLUE];
    }

    public void changeBird(int chose) {
        switch (chose) {
            case BIRD_BLUE:
                imageBird = image[BIRD_BLUE];
                break;
            case BIRD_ORANGE:
                imageBird = image[BIRD_ORANGE];
                break;
            case BIRD_VIOLET:
                imageBird = image[BIRD_VIOLET];
                break;
            default:
                break;
        }
    }

    public void drawBird(Graphics2D g2d) {
        g2d.drawImage(imageBird, x, y, size, size, null);
    }

    public void falling() {
        if (y <=0) {
            return;
        }
        y += SPEED_DEFAULT;
    }

    public void fly() {
        y += -4;

    }

    public Rectangle getRectBird() {
        Rectangle rect = new Rectangle(x, y, size, size);
        return rect;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
