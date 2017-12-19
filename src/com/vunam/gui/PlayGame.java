package com.vunam.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.BitSet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.vunam.chuyendulieu.ActionNewGameAndExit;
import com.vunam.chuyendulieu.ActionShowScore;
import com.vunam.manager.Manager;

public class PlayGame extends BaseContainer implements ActionShowScore {

    /**
     * 
     */
    private static final long    serialVersionUID = 1L;
    private Manager              manager;
    private JLabel               lbScore, lbGetScore, lbHiSroce,
            lbGetHightScore;
    public static final Font     font             = new Font("Tahoma",
                                                          Font.BOLD, 20);

    public static boolean        IS_RUNNING;
    public static boolean        GAME_START;
    private int                  time             = 0;

    private String               path             = "score.txt";

    private ActionNewGameAndExit actionNewGameAndExit;

    private int                  readScore        = 0;
    private String               readName;

    private BitSet               mKesyValue;

    public void setActionNewGame(ActionNewGameAndExit actionNewGameAndExit) {
        this.actionNewGameAndExit = actionNewGameAndExit;
    }

    @Override
    public void initContainer() {

        this.setBackground(Color.WHITE);
        this.setLayout(null);

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                mKesyValue.set(e.getKeyCode());

            }

            @Override
            public void keyReleased(KeyEvent e) {
                mKesyValue.clear(e.getKeyCode());

            }
        });
        setFocusable(true);
        setRequestFocusEnabled(true);
    }

    public void initGame() {
        mKesyValue = new BitSet(256);

        IS_RUNNING = true;
        GAME_START = false;
        manager = new Manager();
        Thread th = new Thread(run);
        th.start();
    }

    @Override
    public void intiComps() {

        FontMetrics fontMetrics = getFontMetrics(font);

        String titleDiem = "Score:";
        lbScore = new JLabel(titleDiem);
        int lbScoreW = fontMetrics.stringWidth(titleDiem);
        int lbScoreH = fontMetrics.getHeight();
        lbScore.setBounds(10, 5, lbScoreW, lbScoreH);
        lbScore.setFont(font);
        lbScore.setForeground(Color.YELLOW);

        lbGetScore = new JLabel("0");
        lbGetScore.setBounds(lbScore.getLocation().x + lbScore.getWidth() + 10,
                6, 100, lbScoreH);
        lbGetScore.setFont(font);
        lbGetScore.setForeground(Color.RED);

        String hightScore = "Hight Score:";
        lbHiSroce = new JLabel(hightScore);
        int hightScoreW = fontMetrics.stringWidth(hightScore);
        int hightScoreH = fontMetrics.getHeight();
        lbHiSroce.setBounds(10, lbScore.getHeight() + 10, hightScoreW,
                hightScoreH);
        lbHiSroce.setFont(font);
        lbHiSroce.setForeground(Color.PINK);

        lbGetHightScore = new JLabel();
        lbGetHightScore.setBounds(
                lbHiSroce.getLocation().x + lbHiSroce.getWidth() + 10, 35, 100,
                lbScoreH);
        lbGetHightScore.setFont(font);
        lbGetHightScore.setForeground(Color.RED);
    }

    @Override
    public void addComps() {
        add(lbScore);
        add(lbGetScore);
        add(lbHiSroce);
        add(lbGetHightScore);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
     //   this.setDoubleBuffered(true);
        manager.drawObject(g2d);

    }

    Runnable run = new Runnable() {
                     int score;

                     @Override
                     public void run() {

                         while (IS_RUNNING) {
                             actionKey();
                             readHightScore(path);
                             score = readScore;
                             lbGetHightScore.setText("" + score);
                             try {
                                 Thread.sleep(10);

                             } catch (InterruptedException e) {
                                 e.printStackTrace();
                             }
                             if (GAME_START) {
                                 manager.birdFalling();
                                 manager.moveBackground(time);
                                 manager.moveGround(time);

                                 manager.moveColums(time);
                                 manager.moveItems(time);
                                 manager.moveItemFly(time);
                                 manager.eatItem();
                                 manager.eatItemFly();
                                 lbGetScore.setText("" + manager.getDiem());

                             }
                             if (manager.testCollision() == true) {
                                 if (manager.getDiem() > score) {
                                     String name = JOptionPane.showInputDialog(
                                             PlayGame.this, "Lưu Thông Tin",
                                             "Save", 2);
                                     if (name!= null) {
                                         writeHightScore(path, name,
                                                 manager.getDiem());

                                     }
                                     IS_RUNNING = false;

                                 }

                                 int resuilt = JOptionPane.showConfirmDialog(
                                         null, "Ban co muon choi tiep khong",
                                         "Thong bao!",
                                         JOptionPane.YES_NO_OPTION);
                                 mKesyValue.clear();
                                 if (resuilt == JOptionPane.YES_OPTION) {

                                     IS_RUNNING = true;
                                     manager = new Manager();
                                 } else if (resuilt == JOptionPane.NO_OPTION) {
                                     IS_RUNNING = false;
                                     actionNewGameAndExit.showMenuGame();

                                 }

                             }
                             time++;
                             repaint();
                         }

                     }
                 };

    public void diemCao() {
        readHightScore(path);
        String name = readName;
        int socre = readScore;
        JOptionPane.showMessageDialog(this, "Name:" + name + "\n"
                + " Hight Score:" + socre);
    }

    private void actionKey() {

        if (mKesyValue.get(KeyEvent.VK_SPACE)) {
            manager.birdFly();
        }

    }

    public void writeHightScore(String path, String name, int score) {
        File file = new File(path);
        if (file.exists() == false) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // mo file de ghi
        try {
            FileOutputStream output = new FileOutputStream(file);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    output));
            String sco = name + "_" + score;
            bw.write(sco);
            bw.newLine();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readHightScore(String path) {

        String[] hightScore;
        File file = new File(path);
        if (file.exists()) {
            FileReader read;
            try {
                read = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(read);
                String line = bufferedReader.readLine();
                while (line != null) {
                    hightScore = line.split("_");
                    readName = hightScore[0];
                    readScore = Integer.parseInt(hightScore[1]);
                    line = bufferedReader.readLine();
                }
                bufferedReader.close();
                read.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void showScore() {
        diemCao();

    }

}
