package objects;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.math.Vector2;

import utils.Settings;


public class Spacecraft extends Actor {

    public static final int SPACECRAFT_STRAIGHT = 0;
    public static final int SPACECRAFT_UP = 1;
    public static final int SPACECRAFT_DOWN = 2;

    private Vector2 position;
    private int width, height;
    private int direction;

    public Spacecraft(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);

        direction = SPACECRAFT_STRAIGHT;
    }

    public void act(float delta) {
        switch(direction){
            case SPACECRAFT_UP:
                if (this.position.y - Settings.SPACECRAFT_VELOCITY * delta >= 0) {
                    this.position.y -= Settings.SPACECRAFT_VELOCITY * delta;
                }
                break;
            case SPACECRAFT_DOWN:
                if (this.position.y + height + Settings.SPACECRAFT_VELOCITY * delta <= Settings.GAME_HEIGHT) {
                    this.position.y += Settings.SPACECRAFT_VELOCITY * delta;
                }
                break;
            case SPACECRAFT_STRAIGHT:
                break;
        }
    }
    // Getters dels atributs principals
    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    // Canviem la direcció de l'Spacecraft: Puja
    public void goUp() {
        direction = SPACECRAFT_UP;
    }

    // Canviem la direcció de l'Spacecraft: Baixa
    public void goDown() {
        direction = SPACECRAFT_DOWN;
    }

    // Posem l'Spacecraft al seu estat original
    public void goStraight() {
        direction = SPACECRAFT_STRAIGHT;
    }


}
