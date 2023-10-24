package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;


public class MyGdxGame extends ApplicationAdapter {
    ShapeRenderer shape;
    Ball ball;
    Circle ballCircle;
    Paddle paddle;
    Rectangle paddleRect;


    @Override
    public void create() {
        shape = new ShapeRenderer();

        // ball y = ballY + size / 2
        ball = new Ball(Gdx.graphics.getWidth() / 2, 100,
                10, 2, 3);

        shape = new ShapeRenderer();
        paddle = new Paddle(270, 20, 100, 10);

        ballCircle = ball.circle;
        paddleRect = paddle.rectangle;

    }

    @Override
    public void render() {
        super.render();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        ball.update();
        shape.begin(ShapeRenderer.ShapeType.Filled);
        ball.draw(shape);
        shape.end();

        paddle.update();
        shape.begin(ShapeRenderer.ShapeType.Filled);
        paddle.draw(shape);
        shape.end();

        if (CheckCollision.collisionChecker(ballCircle, paddleRect)) {

            // Reverse the ball's vertical direction
            ball.ySpeed = -ball.ySpeed;
            ball.circleYSpeed = -ball.circleYSpeed;

            // Verifique a posição da bola em relação ao centro do paddle
            if (ball.ballX > paddle.paddleX + paddle.paddleWidth / 2) {
                // Bola à direita do centro do paddle
                ball.xSpeed = Math.abs(ball.xSpeed); // Movimento para a direita
                ball.circleXSpeed = Math.abs(ball.circleXSpeed);
            } else if (ball.ballX < paddle.paddleX + paddle.paddleWidth / 2) {
                // Bola à esquerda do centro do paddle
                ball.xSpeed = -Math.abs(ball.xSpeed); // Movimento para a esquerda
                ball.circleXSpeed = -Math.abs(ball.circleXSpeed);
            }
        }

    }
}