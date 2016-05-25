package com.mygdx.screens;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Dylan on 8/25/2015.
 */
public class NodeJumper extends Node {
    private boolean horizontal;
    private int  num;

    public NodeJumper(String color, Level level, boolean horizontal, int num)
    {
        super(color, level);
        this.horizontal = horizontal;
        this.num = num;
    }

    public void tapped() {
        int newC = col, newR = row;

        if (level.currentNode.isNeighborTo(this)) {
            if (horizontal) {
                newC += num;
                if (newC >= level.board.get(row).size()) {
                    newC += level.board.get(row).size();
                } else if (newC < 0) {
                    newC = 0;
                }
            } else {
                newR += num;
                if (newR >= level.board.size()) {
                    newR += level.board.size();
                } else if (newR < 0) {
                    newR = 0;
                }
            }
            level.currentNode = level.board.get(newR).get(newC);
            level.score++;
        }
    }

    public Node clone() {
        return new NodeJumper(color, this.level, horizontal, num);
    }
}
