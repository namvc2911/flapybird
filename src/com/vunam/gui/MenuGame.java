package com.vunam.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.vunam.chuyendulieu.ActionNewGameAndExit;
import com.vunam.chuyendulieu.ActionShowScore;

public class MenuGame extends BaseContainer implements ActionListener {

    /**
     * 
     */
    private static final long    serialVersionUID = 1L;
    private ImageIcon            iconBackGround;
    private Image                backgroundMenuGame;
    private JButton              btNewGame, btHightSocre, btInstruction,
            btAuthor, btExit;

    private ActionNewGameAndExit actionNewGameAndExit;

    private ActionShowScore      showScore;

    public void setActionShowScore(ActionShowScore actionShowScore) {
        this.showScore = actionShowScore;
    }

    public void setActionNewGameAndExit(
            ActionNewGameAndExit actionNewGameAndExit) {
        this.actionNewGameAndExit = actionNewGameAndExit;
    }

    @Override
    public void initContainer() {
        iconBackGround = new ImageIcon(getClass().getResource(
                "/images/menu.jpg"));
        backgroundMenuGame = iconBackGround.getImage();
        this.setLayout(null);

    }

    @Override
    public void intiComps() {
        // khoi tao button New Game
        ImageIcon imgNewGame, imgHightScore, imgInstruction, imgAuthor, imgExit;

        imgNewGame = new ImageIcon(getClass()
                .getResource("/images/newgame.png"));
        btNewGame = new JButton();
        btNewGame.setIcon(imgNewGame);
        btNewGame.setBounds(GUI.WIDTH_FRAME / 2 - 80,
                GUI.HEIGHT_FRAME / 2 - 100, imgNewGame.getIconWidth(),
                imgNewGame.getIconHeight());
        btNewGame.setContentAreaFilled(false);
        btNewGame.setBorderPainted(false);

        // khoi tao button Hight Score
        imgHightScore = new ImageIcon(getClass().getResource(
                "/images/hightscore.png"));
        btHightSocre = new JButton();
        btHightSocre.setIcon(imgHightScore);
        btHightSocre.setBounds(GUI.WIDTH_FRAME / 2 - 80,
                btNewGame.getLocation().y + btNewGame.getHeight() + 15,
                imgHightScore.getIconWidth(), imgHightScore.getIconHeight());
        btHightSocre.setContentAreaFilled(false);
        btHightSocre.setBorderPainted(false);

        // khoi tao button Instruction
        imgInstruction = new ImageIcon(getClass().getResource(
                "/images/instruction.png"));
        btInstruction = new JButton();
        btInstruction.setIcon(imgInstruction);
        btInstruction.setBounds(GUI.WIDTH_FRAME / 2 - 80,
                btHightSocre.getLocation().y + btHightSocre.getHeight() + 15,
                imgInstruction.getIconWidth(), imgInstruction.getIconHeight());
        btInstruction.setContentAreaFilled(false);
        btInstruction.setBorderPainted(false);

        // khoi tao button Author
        imgAuthor = new ImageIcon(getClass().getResource("/images/author.png"));
        btAuthor = new JButton();
        btAuthor.setIcon(imgAuthor);
        btAuthor.setBounds(GUI.WIDTH_FRAME / 2 - 80,
                btInstruction.getLocation().y + btInstruction.getHeight() + 15,
                imgAuthor.getIconWidth(), imgAuthor.getIconHeight());
        btAuthor.setContentAreaFilled(false);
        btAuthor.setBorderPainted(false);

        // khoi tao button Exit
        imgExit = new ImageIcon(getClass().getResource("/images/exitgame.png"));
        btExit = new JButton();
        btExit.setIcon(imgExit);
        btExit.setBounds(GUI.WIDTH_FRAME / 2 - 80, btAuthor.getLocation().y
                + btAuthor.getHeight() + 15, imgExit.getIconWidth(),
                imgExit.getIconHeight());
        btExit.setContentAreaFilled(false);
        btExit.setBorderPainted(false);

        btNewGame.addActionListener(this);
        btHightSocre.addActionListener(this);
        btInstruction.addActionListener(this);
        btAuthor.addActionListener(this);
        btExit.addActionListener(this);
    }

    @Override
    public void addComps() {
        this.add(btNewGame);
        this.add(btHightSocre);
        this.add(btInstruction);
        this.add(btAuthor);
        this.add(btExit);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(backgroundMenuGame, 0, 0, GUI.WIDTH_FRAME,
                GUI.HEIGHT_FRAME, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btNewGame)) {
            actionNewGameAndExit.showBacGroundMainGame();

        } else if (e.getSource().equals(btHightSocre)) {
            showScore.showScore();
        } else if (e.getSource().equals(btInstruction)) {
            JOptionPane
                    .showMessageDialog(
                            this,
                            "<html>Luáº­t chÆ¡i:Báº¡n báº¥m phÃ­m Space trÃªn bÃ n phÃ¬m Ä‘á»ƒ di chuyá»ƒn chÃº chim.<br/>TrÃ¡nh cÃ¡c Ã´ng nÆ°á»›c vÃ  Äƒn cÃ¡c váº­t pháº©m .<br/> Báº¡n pháº£i Äƒn váº­t pháº©m Ä‘á»ƒ giÃ nh Ä‘Æ°á»£c Ä‘iá»ƒm sá»‘,Ä‘iá»ƒm sá»‘ tÃ¹y thuá»™c vÃ o loáº¡i váº­t pháº©m,cÃ³ nhá»¯ng váº­t pháº©m sáº½ lÃ m báº¡n bá»‹ máº¥t Ä‘iá»ƒm</html>");
        } else if (e.getSource().equals(btAuthor)) {
            JOptionPane
                    .showMessageDialog(
                            this,

                            "<html>Cover By:Nguyá»…n ThÃ nh Nam <br/> Lá»›p:LAND1511E <br/>Email:thanhnamcntt1955@gmail.com <br/>Má»�i thÃ´ng tin chi tiáº¿t xin vui long liÃªn há»‡ Ä‘á»ƒ Ä‘Æ°á»£c giáº£i Ä‘Ã¡p </html>");
        } else if (e.getSource().equals(btExit)) {
            actionNewGameAndExit.exitGame();
        }

    }

}
