package screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.ScrollHandler;

import java.util.ArrayList;

import helpers.AssetManager;
import objects.Asteroid;
import objects.Spacecraft;
import utils.Settings;

public class GameScreen implements Screen {

    private Stage stage;
    private Spacecraft spacecraft;
    private ScrollHandler scrollHandler;
    // Representació de figures geomètriques
    private ShapeRenderer shapeRenderer;
    // Per obtenir el batch de l'stage
    private Batch batch;

    public GameScreen() {
        shapeRenderer = new ShapeRenderer();
        OrthographicCamera camera = new OrthographicCamera(Settings.GAME_WIDTH, Settings.GAME_HEIGHT);
        camera.setToOrtho(true);
        StretchViewport viewport = new StretchViewport(Settings.GAME_WIDTH, Settings.GAME_HEIGHT , camera);
        stage = new Stage(viewport);
        batch = stage.getBatch();
        spacecraft = new Spacecraft(Settings.SPACECRAFT_STARTX, Settings.SPACECRAFT_STARTY, Settings.SPACECRAFT_WIDTH, Settings.SPACECRAFT_HEIGHT);
        scrollHandler = new ScrollHandler();
        stage.addActor(scrollHandler);
        stage.addActor(spacecraft);
    }

    private void drawElements() {
        //Gdx.gl20.glClearColor(0, 0, 0, 1);
        //Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        shapeRenderer.setColor(new Color(0,1,0,1));
        shapeRenderer.rect(spacecraft.getX(), spacecraft.getY(), spacecraft.getWidth(), spacecraft.getHeight());

        ArrayList<Asteroid> asteroids = scrollHandler.getAsteroids();
        Asteroid asteroid;

        for(int i = 0; i< asteroids.size(); i++) {
            asteroid = asteroids.get(i);
            switch(i){
                case 0:
                    shapeRenderer.setColor(new Color(1,0,0,1));
                    break;
                case 1:
                    shapeRenderer.setColor(new Color(0,0,1,1));
                    break;
                case 2:
                    shapeRenderer.setColor(new Color(1,1,0,1));
                    break;
                default:
                    shapeRenderer.setColor(new Color(1,1,1,1));
                    break;
            }
            shapeRenderer.circle(asteroid.getX() + asteroid.getWidth()/2, asteroid.getY()+asteroid.getWidth()/2, asteroid.getWidth()/2);
        }
        shapeRenderer.end();
    }

    public Stage getStage() {
        return stage;
    }

    public Spacecraft getSpacecraft() {
        return spacecraft;
    }

    public ScrollHandler getScrollHandler() {
        return scrollHandler;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
       // Gdx.gl.glClearColor(1, 0, 0, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        stage.act(delta);
        //drawElements();
        AssetManager.music.play();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
