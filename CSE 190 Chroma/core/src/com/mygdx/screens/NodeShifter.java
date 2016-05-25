package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Assets;

import java.util.ArrayList;
import java.util.Collections;

public class NodeShifter extends Node {

    private boolean horizontal;
    private int num;

    public NodeShifter(String color, Level level, boolean horizontal, int num)
    {
        super(color, level);
        this.horizontal = horizontal;
        this.num = num;

        if (horizontal)
        {
            if (num > 4)
                img = Assets.manager.get("shift3 right.png", Texture.class);
            else if (num > 2)
                img = Assets.manager.get("shift2 right.png", Texture.class);
            else
                img = Assets.manager.get("shift1 right.png", Texture.class);
        }
        else
        {
            if (num > 4)
                img = Assets.manager.get("shift3 up.png", Texture.class);
            else if (num > 2)
                img = Assets.manager.get("shift2 up.png", Texture.class);
            else
                img = Assets.manager.get("shift1 up.png", Texture.class);
        }
    }

    public void tapped() {
        if (level.currentNode.isNeighborTo(this)) {
            if (level.currentNode == this) {
                return;
            }
            if (horizontal) {
                Collections.rotate(level.board.get(row), num);

                for (int i = 0; i < level.board.get(row).size(); i++) {
                    level.board.get(row).get(i).col = i;
                    level.board.get(row).get(i).anim = num;
                    level.board.get(row).get(i).horiz = true;
                }
            } else {
                ArrayList<Node> list = new ArrayList<Node>();
                int colToShift = col;
                for (int i = 0; i < level.board.size(); i++) {
                    list.add(level.board.get(i).get(colToShift));
                }

                Collections.rotate(list, num);

                for (int i = 0; i < level.board.size(); i++) {
                    list.get(i).row = i;
                    list.get(i).anim = num;
                    list.get(i).horiz = false;
                    level.board.get(i).set(colToShift, list.get(i));
                }
            }
            level.currentNode = this;
            level.score++;
        }
    }

    public Node clone() {
        return new NodeShifter(color, this.level, horizontal, num);
    }
}
