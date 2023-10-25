package com.mygdx.game;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Brick {
    int x;
    int y;
    int width;
    int height;
    boolean destroyed = false;
    Rectangle rect;

    public Brick(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rect = new Rectangle(x, y, width, height);

    }

    public void draw(ShapeRenderer shape) {
        shape.rect(x, y, width, height);
    }


}