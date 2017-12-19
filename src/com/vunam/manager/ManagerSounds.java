package com.vunam.manager;

import java.applet.Applet;
import java.applet.AudioClip;

public class ManagerSounds {
    private AudioClip fly, eatItem, die1, die2, simle, giftBox;

    public ManagerSounds() {
        initSound();
    }

    private void initSound() {

        fly = Applet.newAudioClip(getClass().getResource("/sounds/jump.wav"));
        eatItem = Applet.newAudioClip(getClass()
                .getResource("/sounds/point.wav"));
        die1 = Applet.newAudioClip(getClass().getResource("/sounds/hit.wav"));
        // die2 = Applet.newAudioClip(getClass().getResource("/Sound/die.wav"));

        simle = Applet.newAudioClip(getClass().getResource("/sounds/cuoi.wav"));

        giftBox = Applet
                .newAudioClip(getClass().getResource("/sounds/yeah.wav"));
    }

    public AudioClip getFly() {
        return fly;
    }

    public AudioClip getEatItem() {
        return eatItem;
    }

    public AudioClip getDie1() {
        return die1;
    }

    public AudioClip getDie2() {
        return die2;
    }

    public AudioClip getSimle() {
        return simle;
    }

    public AudioClip getGiftBox() {
        return giftBox;
    }
    
    
}
