package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Paddle {

    int paddleX;
    int paddleY;
    int paddleWidth;
    int paddleHeight;
    Rectangle rectangle;


    public Paddle(int x, int y, int width, int height) {

        this.paddleX = x;
        this.paddleY = y;
        this.paddleWidth = width;
        this.paddleHeight = height;
        this.rectangle = new Rectangle(0, paddleY, paddleWidth, paddleHeight);
    }

    public void update() {
        paddleX = Gdx.input.getX() - (paddleWidth / 2); // get cursor's x position
        paddleY = 15;
        rectangle.x = paddleX;
        rectangle.y =  paddleY;


        // Verifique se o paddle não ultrapassa a borda esquerda
        if (paddleX < 0) {
            paddleX = 0;
        }

        // Verifique se o paddle não ultrapassa a borda direita
        if (paddleX + paddleWidth > Gdx.graphics.getWidth()) {
            paddleX = Gdx.graphics.getWidth() - paddleWidth;
        }

        // check if the paddle is above or below the cursor
        if (paddleY < 0) {
            paddleY = 0;
        }
        if (paddleY + paddleHeight > Gdx.graphics.getHeight()) {
            paddleY = Gdx.graphics.getHeight() - paddleHeight;
        }


    }

    public void draw(ShapeRenderer shape) {
        shape.rect(paddleX, paddleY, paddleWidth, paddleHeight);
    }

}
