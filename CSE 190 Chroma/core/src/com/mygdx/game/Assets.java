package com.mygdx.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.ArrayList;

/**
 * Created by Dylan on 8/31/2015.
 */
public class Assets {
    public static final AssetManager manager = new AssetManager();
    public static final int star3[] = new int[46];
    public static final int star2[] = new int[46];

    public static void load() {
        // Asset Loading
        manager.load("menubuttons.pack", TextureAtlas.class);
        manager.load("mainmenu.json", Skin.class, new SkinLoader.SkinParameter("menubuttons.pack"));
        manager.load("soft blue bg.jpg", Texture.class);
        manager.load("logoname.png", Texture.class);
        manager.load("UI start.png", Texture.class);
        manager.load("UI levelselect.png", Texture.class);
        manager.load("UI settings.png", Texture.class);
        manager.load("UI store.png", Texture.class);

        manager.load("uiskin.pack", TextureAtlas.class);
        manager.load("uiskin.json", Skin.class, new SkinLoader.SkinParameter("uiskin.pack"));
        manager.load("settingsText.png", Texture.class);
        manager.load("easyText.png", Texture.class);
        manager.load("mediumText.png", Texture.class);
        manager.load("hardText.png", Texture.class);
        manager.load("clearedText.png", Texture.class);
        manager.load("score0.png", Texture.class);
        manager.load("score1.png", Texture.class);
        manager.load("score2.png", Texture.class);
        manager.load("score3.png", Texture.class);

        manager.load("uistore.pack", TextureAtlas.class);
        manager.load("uistore.json", Skin.class, new SkinLoader.SkinParameter("uistore.pack"));
        manager.load("UI purchased.png", Texture.class);

        manager.load("current.png", Texture.class);
        manager.load("entrance.png", Texture.class);
        manager.load("exit.png", Texture.class);
        manager.load("red.png", Texture.class);
        manager.load("green.png", Texture.class);
        manager.load("blue.png", Texture.class);
        manager.load("faded.png", Texture.class);
        manager.load("grayed.png", Texture.class);
        manager.load("pausebutton.png", Texture.class);
        manager.load("black.png", Texture.class);
        manager.load("shift1 right.png", Texture.class);
        manager.load("shift2 right.png", Texture.class);
        manager.load("shift3 right.png", Texture.class);
        manager.load("shift1 up.png", Texture.class);
        manager.load("shift2 up.png", Texture.class);
        manager.load("shift3 up.png", Texture.class);

        manager.load("blank.png", Texture.class);
        manager.load("coin-sprite-animation.jpg", Texture.class);
        manager.load("Star.png", Texture.class);
        manager.load("Star_black.png", Texture.class);
        manager.load("triangle.png", Texture.class);
        manager.load("circlesheet.png", Texture.class);
        manager.load("portalBlue.png", Texture.class);

        manager.load("matchthiscolor1.png", Texture.class);
        manager.load("matchthiscolor2.png", Texture.class);
        manager.load("matchthiscolor3.png", Texture.class);
        manager.load("gettohere.png", Texture.class);

        manager.load("congrats.ogg", Music.class);

        manager.load("UI pauseBG.png", Texture.class);

        // Level Thresholds
        addThresholds(1, 100, 101);
        addThresholds(2, 8, 15);
        addThresholds(3, 10, 16);
        addThresholds(4, 16, 20);
        addThresholds(5, 11, 15);
        addThresholds(6, 100, 101);
        addThresholds(7, 8, 10);
        addThresholds(8, 0, 0);
        addThresholds(9, 0, 0);
        addThresholds(10, 0, 0);
        addThresholds(11, 0, 0);
        addThresholds(12, 0, 0);
        addThresholds(13, 0, 0);
        addThresholds(14, 0, 0);
        addThresholds(15, 0, 0);
        addThresholds(16, 6, 8);
        addThresholds(17, 100, 101);
        addThresholds(18, 4, 6);
        addThresholds(19, 4, 5);
        addThresholds(20, 9, 11);
        addThresholds(21, 0, 0);
        addThresholds(22, 0, 0);
        addThresholds(23, 0, 0);
        addThresholds(24, 0, 0);
        addThresholds(25, 0, 0);
        addThresholds(26, 0, 0);
        addThresholds(27, 0, 0);
        addThresholds(28, 0, 0);
        addThresholds(29, 0, 0);
        addThresholds(30, 0, 0);
        addThresholds(31, 17, 19);
        addThresholds(32, 19, 21);
        addThresholds(33, 15, 20);
        addThresholds(34, 12, 16);
        addThresholds(35, 11, 14);
        addThresholds(36, 0, 0);
        addThresholds(37, 0, 0);
        addThresholds(38, 0, 0);
        addThresholds(39, 0, 0);
        addThresholds(40, 0, 0);
        addThresholds(41, 0, 0);
        addThresholds(42, 0, 0);
        addThresholds(43, 0, 0);
        addThresholds(44, 0, 0);
        addThresholds(45, 0, 0);
    }

    public static void addThresholds(int level, int star3T, int star2T) {
        star3[level] = star3T;
        star2[level] = star2T;
    }
}
