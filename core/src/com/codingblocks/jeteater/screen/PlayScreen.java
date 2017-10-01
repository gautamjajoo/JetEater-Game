package com.codingblocks.jeteater.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.codingblocks.jeteater.EaterGame;
import com.codingblocks.jeteater.scene.HUD;
import com.codingblocks.jeteater.sprite.Bomb;
import com.codingblocks.jeteater.sprite.Coin;
import com.codingblocks.jeteater.sprite.Jet;

import java.util.LinkedList;

import sun.font.CreatedFontTracker;

import static com.codingblocks.jeteater.EaterGame.batch;

/**
 * Created by sridhar123 on 30/9/17.
 */

//screen is an interface so we are implementing it
public class PlayScreen implements Screen {

    private Jet jet;
    private HUD hud;
    private float time=0;

    private LinkedList<Coin> coins = new LinkedList<Coin>();
    private LinkedList<Bomb> bombs= new LinkedList<Bomb>();

    public PlayScreen() {
      jet = new Jet();
      hud = new HUD();

      for (int i=0;i<4;i++)
          coins.add(new Coin());

      for (int i=0;i<4;i++)
            bombs.add(new Bomb());

    }

    //for using screen we have to use constructor
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update();//first the thing gets updated then it is shown
        time= time+delta;
        if(time>=1){
            hud.timer();
            time=0;
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);//(R,G,B,ALPHA)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        EaterGame.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        EaterGame.batch.begin();//batach is like in memeory canvas
        for(Bomb bomb :bombs){
            bomb.draw(EaterGame.batch);
        }

        for(Coin coin :coins){
            coin.draw(EaterGame.batch);
        }

        //batch.draw(img, 0, 0);//batch is like a graph paper
        //earlier we gave info to batch how to draw...now info is given
        jet.draw(EaterGame.batch);

        batch.end();
    }

    private void update() {
        handleInput();
        handleEvent();
    }

    private void handleEvent() {
        for(Bomb bomb :bombs){
            if(bomb.checkHit(jet)) {
                hud.kill();
                for(Bomb bomb1:bombs){
                    bomb1.relocate();
                }

            }
        }

        for(Coin coin :coins){
            if(coin.checkHit(jet)){
                hud.addScore(10);
                for(Coin coin1:coins){
                    coin1.relocate();
                }

            }
        }
    }

    private void handleInput() {
            int x = (int) Gdx.input.getX();
            int y = (int) (Gdx.graphics.getHeight()-Gdx.input.getY());
            jet.setGoal(x,y);
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
    //sprites are never disposed...only texture and images are disposed
    }
}