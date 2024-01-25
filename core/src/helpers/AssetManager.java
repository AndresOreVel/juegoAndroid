package helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetManager {
    public static Sound explosionSound;
    public static Music music;
    // Sprite Sheet
    public static Texture sheet;

    // Nau i fons
    public static TextureRegion spacecraft, spacecraftDown, spacecraftUp, background;

    // Asteroide
    public static TextureRegion[] asteroid;
    public static Animation asteroidAnim;

    // Explosió
    public static TextureRegion[] explosion;
    public static Animation explosionAnim;
    public static void load() {
        sheet = new Texture(Gdx.files.internal("sheet.png"));
        sheet.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        spacecraft = new TextureRegion(sheet, 0, 0, 36, 15);
        spacecraft.flip(false, true);

        spacecraftUp = new TextureRegion(sheet, 36, 0, 36, 15);
        spacecraftUp.flip(false, true);

        spacecraftDown = new TextureRegion(sheet, 72, 0, 36, 15);
        spacecraftDown.flip(false, true);

        asteroid = new TextureRegion[16];
        for(int i = 0; i< asteroid.length; i++) {
            asteroid[i] = new TextureRegion(sheet, 34, 15, 34, 34);
            asteroid[i].flip(false, true);
        }
        asteroidAnim = new Animation(0.05f, (Object[]) asteroid);
        asteroidAnim.setPlayMode(Animation.PlayMode.LOOP_REVERSED);

        explosion = new TextureRegion[16];

        int index = 0;
        for(int i = 0; i<2; i++){
            for(int j = 0; j<8; j++){
                explosion[index++] = new TextureRegion(sheet, j * 64, i * 64 + 49, 64, 64);
                explosion[index-1].flip(false, true);
            }
        }

        explosionAnim = new Animation(0.04f, (Object[]) explosion);
        explosionAnim.setPlayMode(Animation.PlayMode.NORMAL);

        background = new TextureRegion(sheet, 0, 177, 480, 135);
        background.flip(false, true);
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.wav"));
        music = Gdx.audio.newMusic(Gdx.files.internal("Afterburner.ogg"));
        music.setVolume(0.2f);
        music.setLooping(true);
    }

    public static void dispose() {
        sheet.dispose();
        explosionSound.dispose();
        music.dispose();
    }
}
