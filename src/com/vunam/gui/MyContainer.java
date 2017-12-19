package com.vunam.gui;

import java.awt.CardLayout;

import com.vunam.chuyendulieu.ActionNewGameAndExit;
import com.vunam.chuyendulieu.GUIExit;

public class MyContainer extends BaseContainer implements ActionNewGameAndExit {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private CardLayout        mCard;
    private MenuGame          menuGame;
    private PlayGame          playGame;
    private BacgroundMainGame bacgroundMainGame;
    public static String      MENU_ID         = "0";
    public static String      BACKGROUND_GAME = "1";
    public static String      PLAYGAME_ID     = "2";

    private GUIExit           guiExit;

    @Override
    public void initContainer() {
        mCard = new CardLayout();
        this.setLayout(mCard);

    }

    @Override
    public void intiComps() {
        menuGame = new MenuGame();
        bacgroundMainGame = new BacgroundMainGame();
        playGame = new PlayGame();
        menuGame.setActionNewGameAndExit(this);
        bacgroundMainGame.setShowPlayGame(this);
        playGame.setActionNewGame(this);
        menuGame.setActionShowScore(playGame);
        
    }

    @Override
    public void addComps() {
        add(menuGame, MENU_ID);
        add(bacgroundMainGame,BACKGROUND_GAME);
        add(playGame, PLAYGAME_ID);
    }

    @Override
    public void showPlayGame(){
        playGame.initGame();
        mCard.show(MyContainer.this,PLAYGAME_ID);
       
        playGame.setFocusable(true);
        playGame.requestFocus(true);
    }

    @Override
    public void exitGame() {
        guiExit.exit();
    }

    public void setExitGame(GUIExit guiExit) {
        this.guiExit = guiExit;
    }

    @Override
    public void showBacGroundMainGame() {
       mCard.show(MyContainer.this,BACKGROUND_GAME);
        
    }

    @Override
    public void showMenuGame() {    
       mCard.show(MyContainer.this,MENU_ID);
       
    }
}
