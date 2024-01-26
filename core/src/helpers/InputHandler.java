package helpers;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import objects.Spacecraft;
import screens.GameScreen;


public class InputHandler implements InputProcessor{
    private Spacecraft spacecraft;
    private GameScreen screen;

    int previousY = 0;

    public InputHandler(GameScreen screen) {
        this.screen = screen;
        spacecraft = screen.getSpacecraft();
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.DOWN){
            spacecraft.goDown();
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.UP){
            spacecraft.goUp();
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        previousY = screenY;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        spacecraft.goStraight();
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if(Math.abs(previousY - screenY) > 2) {
            if(previousY<screenY) {
                spacecraft.goDown();
            } else {
                spacecraft.goUp();
            }
            previousY = screenY;
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
