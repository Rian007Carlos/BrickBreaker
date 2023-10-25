package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;


public class MyGdxGame extends ApplicationAdapter {
    ShapeRenderer shape;
    Ball ball;
    Paddle paddle;
    Circle ballCircle;
    Rectangle paddleRect;
    ArrayList<Brick> bricks = new ArrayList<>();


    @Override
    public void create() {
        shape = new ShapeRenderer();
        int brickWidth = 63;
        int brickHeight = 20;

        for (int y = Gdx.graphics.getHeight() / 2; y < Gdx.graphics.getHeight(); y += brickHeight + 10) {
            for (int x = 0; x < Gdx.graphics.getWidth(); x += brickWidth + 10) {
                bricks.add(new Brick(x, y, brickWidth, brickHeight));
            }
        }

        // ball y = ballY + size / 2
        ball = new Ball(Gdx.graphics.getWidth() / 2, 20, 10, 2, 5);
        shape = new ShapeRenderer();
        paddle = new Paddle(270, 5, 100, 10);

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

        shape.begin(ShapeRenderer.ShapeType.Filled);

        // Draw bricks that are stored in the ArrayList
        for (Brick brick : bricks) {
            brick.draw(shape);

            // Check the collision between the ball and the bricks
            if(CheckCollision.brickCollisionChecker(ballCircle, brick.rect)) {
                    ball.ySpeed = -ball.ySpeed;
                    ball.circleYSpeed = -ball.circleYSpeed;
                    brick.destroyed = true;
            }
        }

        // check every brick if was hit, if so, then remove the brick. (not so optimal way to do this)
        for (int i = 0; i < bricks.size(); i++) {
            Brick b = bricks.get(i);
            if (b.destroyed) {
                bricks.remove(b);

                // we need to decrement i when a ball gets removed, otherwise we skip a ball!
                i--;
            }
        }
        shape.end();


        // Check the collision between the ball and the paddle
        if (!CheckCollision.collisionChecker(ballCircle, paddleRect)) {
            return;
        }

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
