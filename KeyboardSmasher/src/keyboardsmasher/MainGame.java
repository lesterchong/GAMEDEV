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
import java.util.ArrayList;
import snakegame.Block;

/**
 *
 * @author Lester Chong
 */
public class MainGame extends GameObject{

    private int spawnValue;
    private Block main;
    private ArrayList <Block> up = new ArrayList<>();
    private ArrayList <Block> down = new ArrayList<>();
    private ArrayList <Block> left = new ArrayList<>();
    private ArrayList <Block> right = new ArrayList<>();
    private double dimension;
    
    
    public MainGame(GameEngine ge) {
        super(ge);
    }

    @Override
    public void initResources() {
        dimension = 100;
        main = new Block(getImage("resources/Main.png"),245,245);
    }

    @Override
    public void update(long l) {
        //if(System.currentTimeMillis() % 20 == 0) //Determines how fast the arrows show
            Generate();
        
        Movement();
        
    }

    @Override
    public void render(Graphics2D gd) {
        gd.setColor(Color.white);
        gd.fillRect(0, 0, getWidth(), getHeight());
        
        main.render(gd);
        
        for (int ctr = 0; ctr < up.size();ctr++)
            up.get(ctr).render(gd);
        
        for (int ctr = 0; ctr < down.size();ctr++)
            down.get(ctr).render(gd);
        
        for (int ctr = 0; ctr < left.size();ctr++)
            left.get(ctr).render(gd);
        
        for (int ctr = 0; ctr < right.size();ctr++)
            right.get(ctr).render(gd);
        
    }
    
    public void Generate(){
        spawnValue = (int)(Math.random()*4);
        
        if(spawnValue == 0){
            up.add(new Block(getImage("resources/Up.png"),270,740));
        }
        
        else if(spawnValue == 1){
            down.add(new Block(getImage("resources/Down.png"),270,-100));
        }
        
        else if(spawnValue == 2){
            right.add(new Block(getImage("resources/Right.png"),-100,270));
        }
        
        else if(spawnValue == 3){
            left.add(new Block(getImage("resources/Left.png"),740,270));
        }
    }
    
    public void Movement(){
        for(int ctr = 0; ctr < up.size(); ctr++){
            up.get(ctr).setY(up.get(ctr).getY() - 0.3*dimension);
        }
        
        for(int ctr = 0; ctr < down.size(); ctr++){
            down.get(ctr).setY(down.get(ctr).getY() + 0.3*dimension);
        }
        
        for(int ctr = 0; ctr < left.size(); ctr++){
            left.get(ctr).setX(left.get(ctr).getX() - 0.3*dimension);
        }
        
        for(int ctr = 0; ctr < right.size(); ctr++){
            right.get(ctr).setX(right.get(ctr).getX() + 0.3*dimension);
        }
        
    }
    
    /*public void movement(int num){
        if(num == 0 && down.getY()!= 640){
            down.setY(down.getY() + .05*dimension);
        }else if(num ==1 && up.getY()!= 0){
            up.setY(up.getY() - .05*dimension);
        }else if(num ==2 && left.getX()!= 0){
            left.setX(left.getX() - .05*dimension);
        }else if(num ==3 && right.getX()!= 640){
            right.setY(right.getX() + .05*dimension);
        }
    }*/  
}
