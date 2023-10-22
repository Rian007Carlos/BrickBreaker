package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;



public class Ball {
    int ballX;
    int ballY;
    int size;
    int xSpeed;
    int ySpeed;
    Color color;


    public Ball(int ballX, int ballY, int size, int xSpeed, int ySpeed) {
        this.ballX = ballX;
        this.ballY = ballY + size / 2;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.color = Color.WHITE;
    }

    public void update() {

        int nextXRight = (ballX + size / 2) + xSpeed;
        int nextYUp = (ballY + size / 2) + ySpeed;
        int nextXLeft = (ballX - size / 2) + xSpeed;
        int nextYDown = (ballY - size / 2) + ySpeed;



        // Verifique se a bola atingirá as bordas da tela

        if (nextXLeft - (size / 2) < 0 || nextXRight + (size / 2) > Gdx.graphics.getWidth()) {
            xSpeed = -xSpeed;
        }
        if (nextYDown - (size / 2) < 0 || nextYUp + (size / 2) > Gdx.graphics.getHeight()) {
            ySpeed = -ySpeed;
        }

        // Atualize as posições da bola
        ballX += xSpeed;
        ballY += ySpeed;

    }


    public void draw(ShapeRenderer shape) {
        shape.setColor(color);
        shape.circle(ballX, ballY, size);
    }


}
