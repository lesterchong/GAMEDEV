/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package keyboardsmasher;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import java.awt.Color;
import java.awt.Graphics2D;
import snakegame.Block;

/**
 *
 * @author Lester Chong
 */
public class MainGame extends GameObject{

    private int spawnValue;
    private Block main;
    private Block up;
    private Block down;
    private Block left;
    private Block right;
    private double dimension;
    
    
    public MainGame(GameEngine ge) {
        super(ge);
    }

    @Override
    public void initResources() {
        dimension = 100;
        
        up = new Block(getImage("resources/Up.png"),270,740);
        down = new Block(getImage("resources/Down.png"),270,-100);
        right = new Block(getImage("resources/Right.png"),-100,270);
        left = new Block(getImage("resources/Left.png"),740,270);
        main = new Block(getImage("resources/Main.png"),245,245);
    }

    @Override
    public void update(long l) {
        if(System.currentTimeMillis() % 20 == 0) //Determines how fast the arrows show
            spawnValue = (int)(Math.random()*4);
        
        movement(spawnValue);
        
        down.update(l);
        up.update(l);
        left.update(l);
        right.update(l);
        
    }

    @Override
    public void render(Graphics2D gd) {
        gd.setColor(Color.white);
        gd.fillRect(0, 0, getWidth(), getHeight());
        
        main.render(gd);
        up.render(gd);
        down.render(gd);
        left.render(gd);
        right.render(gd);
    }
    
    public void movement(int num){
        if(num == 0 && down.getY()!= 640){
            down.setY(down.getY() + .05*dimension);
        }else if(num ==1 && up.getY()!= 0){
            up.setY(up.getY() - .05*dimension);
        }else if(num ==2 && left.getX()!= 0){
            left.setX(left.getX() - .05*dimension);
        }else if(num ==3 && right.getX()!= 640){
            right.setY(right.getX() + .05*dimension);
        }
    }    
}
