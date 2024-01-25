package screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

import helpers.AssetManager;
import com.mygdx.game.ScrollHandler;
import helpers.InputHandler;
import objects.Asteroid;
import objects.Spacecraft;
import utils.Settings;

public class GameScreen implements Screen {
    Boolean gameOver = false;

    private Stage stage;
    private Spacecraft spacecraft;
    private ScrollHandler scrollHandler;
    // Representació de figures geomètriques
    private ShapeRenderer shapeRenderer;
    // Per obtenir el batch de l'stage
    private Batch batch;
    private float explosionTime = 0;



    public GameScreen() {
        AssetManager.music.play();
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
        spacecraft.setName("spacecraft");
        Gdx.input.setInputProcessor(new InputHandler(this));
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
        stage.draw();
        stage.act(delta);

        if (!gameOver) {
            if (scrollHandler.collides(spacecraft)) {
                AssetManager.explosionSound.play();
                stage.getRoot().findActor("spacecraft").remove();
                gameOver = true;
            }
        } else {
            batch.begin();
            TextureRegion explosionFrame = (TextureRegion) AssetManager.explosionAnim.getKeyFrame(explosionTime, false);
            batch.draw(explosionFrame, (spacecraft.getX() + spacecraft.getWidth() / 2) - 32, spacecraft.getY() + spacecraft.getHeight() / 2 - 32, 64, 64);
            BitmapFont font = new BitmapFont(true);
            font.draw(batch, "GameOver", 10, 10);
            batch.end();

            explosionTime += delta;
        }

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
