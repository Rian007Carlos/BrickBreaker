package com.mygdx.game;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class CheckCollision {


    public static boolean collisionChecker(Circle circle, Rectangle rectangle) {


        return Intersector.overlaps(circle, rectangle);
    }

    public static boolean brickCollisionChecker(Circle circle, Rectangle rect) {
        return Intersector.overlaps(circle, rect);
    }

}
