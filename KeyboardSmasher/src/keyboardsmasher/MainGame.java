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

    private Block main;
    int spawnValue;
    Block up;
    Block down;
    Block left;
    Block right;
    
    public MainGame(GameEngine ge) {
        super(ge);
    }

    @Override
    public void initResources() {
        up = new Block(getImage("resources/Up.png"),245,740);;
        down = new Block(getImage("resources/Down.png"),270,-100);;
        right = new Block(getImage("resources/Right.png"),-100,245);;
        left = new Block(getImage("resources/Left.png"),740,245);;
        main = new Block(getImage("resources/Main.png"),245,245);
    }

    @Override
    public void update(long l) {
        
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
        spawnValue = (int)(Math.random()*4);
        
        if(spawnValue == 0){
            down.render(gd);
            Movement(spawnValue);
        }
        else if(spawnValue == 1){
            up.render(gd);
            Movement(spawnValue);
        }
        else if(spawnValue == 2){
            left.render(gd);
            Movement(spawnValue);
        }
        else if(spawnValue == 3){
            right.render(gd);
            Movement(spawnValue);
        }
    }
    
    public void Movement(int num){
        if(num ==0){
            down.setY(down.getY() + 5);
        }
        else if(num ==1){
            up.setY(up.getY() - 5);
        }
        else if(num ==2){
            left.setY(left.getX() - 5);
        }
        else if(num ==3){
            right.setY(right.getY() + 5);
        }
        
    }
    
    public void Spawn(){
        
    }
    
}
