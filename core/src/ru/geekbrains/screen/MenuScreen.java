package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Base2DScreen;

public class MenuScreen extends Base2DScreen {

    private static final float V_LEN = 2.5f;

    Texture img;
    Texture background;

    Vector2 pos;
    Vector2 v;
    Vector2 lastTouched;
    Vector2 bufferedLastTouched;

    @Override
    public void show() {
        super.show();
        background = new Texture("bg.png");
        img = new Texture("badlogic.jpg");
        pos = new Vector2(-0.5f, -0.5f);
        v = new Vector2(0,0);
        lastTouched = new Vector2(0,0);
        bufferedLastTouched = new Vector2(0,0);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.5f, 0.2f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        bufferedLastTouched.set(lastTouched);

        if(bufferedLastTouched.sub(pos).len() > 0.01f) {
            pos.add(v);
        } else {
            pos.set(lastTouched);
        }

        batch.begin();
        batch.draw(background, -0.5f, -0.5f, 1f, 1f);
        batch.draw(img, pos.x, pos.y, 0.5f, 0.5f);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        img.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        lastTouched.set(touch.x, touch.y);
        v.set(lastTouched.cpy().sub(pos).setLength(0.01f));
        return super.touchDown(touch, pointer);
    }
}
