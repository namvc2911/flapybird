package com.vunam.object;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Item {
    protected int             x;
    protected int             y;

    public static final int ITEM0      = 0;
    public static final int ITEM1      = 1;
    public static final int ITEM2      = 2;
    public static final int ITEM3      = 3;


    protected int             size;

    protected int           chose;

    public static final int SPEED_MOVE = 3;

    private Image[]         imageItem  = new Image[] {
            new ImageIcon(getClass().getResource("/images/vatpham_2.png"))
                    .getImage(),
            new ImageIcon(getClass().getResource("/images/vatpham_3.png"))
                    .getImage(),
            new ImageIcon(getClass().getResource("/images/vatpham_4.png"))
                    .getImage(),
            new ImageIcon(getClass().getResource("/images/vatpham_4.png"))
                    .getImage()       };

    public Item(int x, int y, int size, int chose) {
        super();
        this.x = x;
        this.y = y;
        this.size = size;
        this.chose = chose;
    }

    public void moveItem(int time) {
        if (time % SPEED_MOVE != 0) {
            return;
        }
        x -= 1;
    }

    public void drawItems(Graphics2D g2d) {
        switch (chose) {
            case ITEM0:
                g2d.drawImage(imageItem[ITEM0], x, y, size, size, null);
                break;
            case ITEM1:
                g2d.drawImage(imageItem[ITEM1], x, y, size, size, null);
                break;
            case ITEM2:
                g2d.drawImage(imageItem[ITEM2], x, y, size, size, null);
                break;
            case ITEM3:
                g2d.drawImage(imageItem[ITEM3], x, y, size, size, null);
                break;
            default:
                break;
        }
    }

    public Rectangle getRectItems() {
        Rectangle rectVatPham = new Rectangle(x, y, size, size);
        return rectVatPham;
    }

    public int getX() {
        return x;
    }

    public Image[] getImageItem() {
        return imageItem;
    }

    public int getChose() {
        return chose;
    }
    
    
}
