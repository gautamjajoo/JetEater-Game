package com.codingblocks.jeteater.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.codingblocks.jeteater.EaterGame;

/**
 * Created by sridhar123 on 30/9/17.
 */

public class Coin extends Sprite{

    public Coin()
    {
        super(new Texture( "coin.png"));
        relocate();
    }

    public void relocate() {

        int x= (int) (Math.random()* EaterGame.WIDTH);
        int y= (int) (Math.random()* EaterGame.HEIGHT);
        setPosition(x,y);
    }

    public boolean checkHit(Jet jet){
        Rectangle jRect=jet.getBoundingRectangle();
        Rectangle coinRect = this.getBoundingRectangle();

        if(jRect.overlaps(coinRect)){
            relocate();
            return true;
        } else {
            return false;
        }
    }
}
