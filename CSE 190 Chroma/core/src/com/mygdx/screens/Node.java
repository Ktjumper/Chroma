package com.mygdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Assets;

import java.util.ArrayList;
import java.util.Random;

public class Node {
    Level level;
    String color;
    Texture img, img2;
    int renderedX, renderedY;
    int size, row, col;

    int anim = 0;
    boolean horiz = false;
    int frame = 0;
    double animOffset = 0;

    Texture redNode, blueNode, greenNode, coin;

    Music congrats;

    public Node(String color, Level level)
    {
        this.level = level;
        this.color = color;

        redNode = Assets.manager.get("red.png", Texture.class);
        blueNode = Assets.manager.get("blue.png", Texture.class);
        greenNode = Assets.manager.get("green.png", Texture.class);
        coin = Assets.manager.get("coin-sprite-animation.jpg", Texture.class);

        redNode.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        greenNode.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        blueNode.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        congrats = Assets.manager.get("congrats.ogg", Music.class);

        if (color.equals("r"))
            img = redNode;
        else if (color.equals("g"))
            img = greenNode;
        else if (color.equals("b"))
            img = blueNode;
        else    //shouldn't happen
            img = coin;
    }

    public boolean isNeighborTo(Node n)
    {
        if (this == n)
            return false;

        if (Math.abs(this.row - n.row) <= 1)
            if (Math.abs(this.col - n.col) <= 1)
                return true;

        return false;
    }

    //basically a second constructor.
    public Node clone()
    {
        return new Node(this.color, this.level);
    }

    public void tapped()
    {
        //valid move
        if (level.currentNode.isNeighborTo(this) && this.color == "e")
        {
            level.currentNode = this;
            level.score++;
            congrats.play();
            Gdx.app.log("asdf", "you win!");
            level.screenGame.endGame();
        }

        else if (level.currentNode.isNeighborTo(this) && this.color == level.sequence.get(level.seqI))
        {
            if (++level.seqI >= level.sequence.size())
                level.seqI = 0;

            level.currentNode = this;
            level.score++;

            level.blackedOut--;
            //Gdx.app.log("asdf", "moved currentNode to node at " + n.row + ", " + n.col);
        }
        else if (level.currentNode.isNeighborTo(this))
        {
            level.seqI = 0;
            level.currentNode = level.startNode;

            level.resetBoard();
            level.blackedOut = 0;

            level.score = 0;

            Gdx.app.log("asdf", "resetting to start node");
        }
    }

    public String getColor()
    {
        return this.color;
    }

    public void render(SpriteBatch sb)
    {
        if (anim != 0) {
            if (frame == 0) {
                animOffset = -1 * anim * size;
            }
            else if (frame < 60) {
                animOffset += anim * size / 60;
            }
            else if (frame == 60)
                anim = 0;

            frame++;
        }
        else {
            animOffset = 0;
            frame = 0;
        }

        //don't render this node's gfx if it's the currentNode.
        if (level.currentNode != this) {
            if (horiz)
                sb.draw(img, (int) (renderedX + animOffset + Gdx.graphics.getWidth()) % Gdx.graphics.getWidth(), renderedY, size, size, 0, 0, 200, 200, false, false);
            else {
                if (animOffset == 0)
                    sb.draw(img, renderedX, renderedY, size, size, 0, 0, 200, 200, false, false);
                else {
                    int maxHeight = level.board.get(level.board.size() - 1).get(0).renderedY;
                    int minHeight = (int) (Gdx.graphics.getHeight() * 0.1);
                    double finalY = renderedY + animOffset;

                    if (finalY > maxHeight)
                        finalY = finalY % maxHeight + (int) (Gdx.graphics.getHeight() * 0.1);
                    if (finalY < minHeight)
                        finalY = (finalY - maxHeight) % maxHeight - (Gdx.graphics.getHeight() - maxHeight);

                    sb.draw(img, renderedX, (int) finalY, size, size, 0, 0, 200, 200, false, false);
                }
            }
        }
    }

    public boolean isStart()    {        return false;    }
    public boolean isExit()     {        return false;    }
    public boolean isPortal(String tag){ return false;    }
}
