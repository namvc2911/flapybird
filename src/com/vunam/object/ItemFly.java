package com.vunam.object;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class ItemFly extends Item {
    public static final int DOI_CHIM = 0;
    public static final int CONG_DIEM=1;
    public static final int TRU_DIEM=2;

    

    private Image[] imageItemFly  = new Image[] {
            new ImageIcon(getClass().getResource("/images/item2.png"))
                    .getImage(),
            new ImageIcon(getClass().getResource("/images/hopqua.png"))
                    .getImage(),
            new ImageIcon(getClass().getResource("/images/skull.png"))
                    .getImage(),
        };
    
    public ItemFly(int x, int y, int size, int chose) {
        super(x, y, size, chose);
 
    }

    @Override
    public void moveItem(int time) {
        if(time%2!=0){
            return;
        }
        x -= 2;
    }

    @Override
    public void drawItems(Graphics2D g2d) {
        switch (chose) {
            case DOI_CHIM:
                g2d.drawImage(imageItemFly[DOI_CHIM], x,y, size, size, null);
                break;
            case CONG_DIEM:
                g2d.drawImage(imageItemFly[CONG_DIEM], x,y, size, size, null);
                break;
            case TRU_DIEM:
                g2d.drawImage(imageItemFly[TRU_DIEM], x,y, size, size, null);
                break;
            default:
                break;
        }
    }

}
