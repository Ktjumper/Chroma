package com.mygdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Assets;

public class NodeAuto extends Node {
    Texture starNode;

    public NodeAuto(String color, Level level)
    {
        super(color, level);
        starNode = Assets.manager.get("Star.png", Texture.class);
        this.img = starNode;
    }

    @ Override
    public void tapped() {
        ((Game)Gdx.app.getApplicationListener()).setScreen(new ScreenLevelSelect());

        //valid move
        if (level.currentNode.isNeighborTo(this)) {
            Gdx.app.log("asdf", "you win!");
            level.score++;
            ((Game) Gdx.app.getApplicationListener()).setScreen(new ScreenWin(level));
        }
    }


    @ Override
    public Node clone()
    {
        return new NodeAuto(color, this.level);
    }
    public boolean isExit()     {   return true;    }

}
