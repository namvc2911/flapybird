package com.vunam.manager;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import com.vunam.bacground.BackGround;
import com.vunam.bacground.Ground;
import com.vunam.gui.GUI;
import com.vunam.object.Bird;
import com.vunam.object.Colums;
import com.vunam.object.Item;
import com.vunam.object.ItemFly;

public class Manager {

    private ArrayList<BackGround> listBackGround;
    private ArrayList<Ground>     listGround;
    private ArrayList<Colums>     listColums;
    private ArrayList<Item>       listItem;
    private ArrayList<ItemFly>    listItemFly;

    private Bird                  mBird;
    private BackGround            backGround;
    private Ground                ground;
    private int                   x, y;

    private Image                 imageGamePanel;
    private Image                 imageGround;
    private Image                 imageColumTop;
    private Image                 imageColumBoth;

    private ManagerSounds         managerSounds;

    private Colums                colum;

    private Item                  item;

    private Random                rd = new Random();

    private int                   score;

    public int getDiem() {
        return score;
    }

    public Manager() {
        listBackGround = new ArrayList<>();
        listGround = new ArrayList<>();
        listColums = new ArrayList<>();
        listItem = new ArrayList<>();
        listItemFly = new ArrayList<>();

        managerSounds = new ManagerSounds();

        initImage();

        initBackGround();

        initGround();

        initColumAndItem();
        initBird();
    }

    private void initImage() {

        imageGamePanel = new ImageIcon(getClass().getResource(
                "/images/gamePanel.png")).getImage();

        imageGround = new ImageIcon(getClass()
                .getResource("/images/matdat.png")).getImage();

        imageColumBoth = new ImageIcon(getClass().getResource(
                "/images/OngDuoi.png")).getImage();
        imageColumTop = new ImageIcon(getClass().getResource(
                "/images/OngTren.png")).getImage();
    }

    public void initBird() {
        mBird = new Bird(GUI.WIDTH_FRAME / 2, GUI.HEIGHT_FRAME / 2, 30);
    }

    private void initBackGround() {
        for (int i = 0; i < 3; i++) {
            y = 0;
            x = i * GUI.WIDTH_FRAME;
            backGround = new BackGround(x, y, GUI.WIDTH_FRAME,
                    GUI.HEIGHT_FRAME, imageGamePanel);
            listBackGround.add(backGround);

        }
    }

    private void initGround() {
        for (int j = 0; j < 3; j++) {
            y = GUI.HEIGHT_FRAME - 90;
            x = j * GUI.WIDTH_FRAME;
            ground = new Ground(x, y, GUI.WIDTH_FRAME, 100, imageGround);
            listGround.add(ground);
        }
    }

    private void initColumAndItem() {
        int space = 80;
        int yUp, yDown, width, heightUp, heightDown, choseItem, choseItemFly;
        for (int i = 0; i < 8; i++) {
            heightUp = rd.nextInt(150) + 150;
            width = 60;
            heightDown = GUI.HEIGHT_FRAME - heightUp - space;
            yUp = 0;
            yDown = heightUp + space;
            x = GUI.WIDTH_FRAME + (width + 150) * i;
            colum = new Colums(x, yUp, yDown, width, heightUp, heightDown,
                    imageColumTop, imageColumBoth);
            listColums.add(colum);

            // khoi tao vat pham
            choseItem = rd.nextInt(4);
            item = new Item(x + 2, heightUp + 20, 15, choseItem);
            listItem.add(item);

            choseItemFly = rd.nextInt(3);
            
            // vat pham bay
            item = new ItemFly(x, yDown, 15, choseItemFly);
            listItemFly.add((ItemFly) item);
        }

    }

    public void drawObject(Graphics2D g2d) {

        // ve mat nen bau troi
        for (int i = 0; i < listBackGround.size(); i++) {
            listBackGround.get(i).drawBackground(g2d);

        }
        // ve vat pham
        for (int i = 0; i < listItem.size(); i++) {
            listItem.get(i).drawItems(g2d);
        }
        // ve vat pham bay
        for (int i = 0; i < listItemFly.size(); i++) {
            listItemFly.get(i).drawItems(g2d);
        }
        // ve ong nuoc
        for (int i = 0; i < listColums.size(); i++) {
            listColums.get(i).drawColums(g2d);

        }

        // ve mat dat
        for (int j = 0; j < listGround.size(); j++) {
            listGround.get(j).drawMatDat(g2d);
        }

        mBird.drawBird(g2d);
    }

    public void birdFly() {
        mBird.fly();
        managerSounds.getFly().play();
    }

    public void birdFalling() {
        mBird.falling();
    }

    // di chuyen nen
    public void moveBackground(int time) {
        for (int i = 0; i < listBackGround.size(); i++) {
            listBackGround.get(i).moveBackground(time);
        }
    }

    // di chuyen dat
    public void moveGround(int time) {
        for (int i = 0; i < listGround.size(); i++) {
            listGround.get(i).moveMatDat(time);
        }
    }

    // di chuyen cot
    public void moveColums(int time) {
        for (int i = 0; i < listColums.size(); i++) {
            listColums.get(i).moveColums(time);
            if (listColums.get(i).getX() == -70) {
                listColums.remove(i);
            }
            if (listColums.size() == 3) {
                initColumAndItem();
            }
        }
    }

    // di chuyen vat pham dung yen
    public void moveItems(int time) {

        for (int i = 0; i < listItem.size(); i++) {
            listItem.get(i).moveItem(time);
            if (listItem.get(i).getX() == -GUI.WIDTH_FRAME) {
                listItem.remove(i);
            }
        }

    }

    // di chuyen nhưng vat pham bay
    public void moveItemFly(int time) {
        for (int i = 0; i < listItemFly.size(); i++) {
            listItemFly.get(i).moveItem(time);
            if (listItemFly.get(i).getX() == -GUI.WIDTH_FRAME) {
                listItemFly.remove(i);
            }
        }
    }

    // kiem tra va cham
    public boolean testCollision() {
        for (int i = 0; i < listColums.size(); i++) {
            if (mBird.getRectBird().intersects(
                    listColums.get(i).getRectColumTop())
                    || mBird.getRectBird().intersects(
                            listColums.get(i).getRectBoth())) {
                managerSounds.getDie1().play();

                return true;
            }
            for (int j = 0; j < listGround.size(); j++) {
                if (mBird.getRectBird().intersects(
                        listGround.get(j).getRectMatDat())) {
                    managerSounds.getDie1().play();
                    return true;
                }
            }
        }
        if (mBird.getY() <= 0) {
            managerSounds.getDie1().play();
            return true;
        }
        return false;
    }

    // an vat pham
    public void eatItem() {

        for (int i = 0; i < listItem.size(); i++) {
            if (mBird.getRectBird().intersects(listItem.get(i).getRectItems())) {
                managerSounds.getEatItem().play();
                score += 2;
                listItem.remove(i);

            }

        }

    }

    // an nhưng vat pham bay
    public void eatItemFly() {
        for (int i = 0; i < listItemFly.size(); i++) {
            int chose = rd.nextInt(2);
            if (mBird.getRectBird().intersects(
                    listItemFly.get(i).getRectItems())) {
                if (listItemFly.get(i).getChose() == ItemFly.DOI_CHIM) {
                    managerSounds.getSimle().play();
                    mBird.changeBird(chose);
                }
                if (listItemFly.get(i).getChose() == ItemFly.CONG_DIEM) {
                    managerSounds.getGiftBox().play();
                    score += 10;
                }
                if (listItemFly.get(i).getChose() == ItemFly.TRU_DIEM) {
                    if (score > 20) {
                        score -= 20;
                    } else {
                        score = 0;
                    }
                }
                listItemFly.remove(i);
            }

        }

    }

}
