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
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
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
    private Random rand;
    
    
    public MainGame(GameEngine ge) {
        super(ge);
    }

    @Override
    public void initResources() {
        dimension = 100;
        main = new Block(getImage("resources/Main.png"),245,245);
        rand = new Random();
    }

    @Override
    public void update(long l) {
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
        if(System.currentTimeMillis() % 23 == 3){ //Legit timer. Needs a bit of TAE testing to make it work though
            spawnValue = rand.nextInt()%4;
        
            if(spawnValue == 0){
                up.add(new Block(getImage("resources/Up.png"),270,740));
            }else if(spawnValue == 1){
                down.add(new Block(getImage("resources/Down.png"),270,-100));
            }else if(spawnValue == 2){
                right.add(new Block(getImage("resources/Right.png"),-100,270));
            }else if(spawnValue == 3){
                left.add(new Block(getImage("resources/Left.png"),740,270));
            }
        }
    }
    
    public void Movement(){
        for(int ctr = 0; ctr < up.size(); ctr++){
            up.get(ctr).setY(up.get(ctr).getY() - 0.1*dimension);
        }
        for(int ctr = 0; ctr < down.size(); ctr++){
            down.get(ctr).setY(down.get(ctr).getY() + 0.1*dimension);
        }
        for(int ctr = 0; ctr < left.size(); ctr++){
            left.get(ctr).setX(left.get(ctr).getX() - 0.1*dimension);
        }
        for(int ctr = 0; ctr < right.size(); ctr++){
            right.get(ctr).setX(right.get(ctr).getX() + 0.1*dimension);
        }
    }
    
    public void checkUserInput(){
        if (keyPressed(KeyEvent.VK_UP)){
            ;
        }else if(keyPressed(KeyEvent.VK_DOWN)){
            
        }else if(keyPressed(KeyEvent.VK_LEFT)){
            
        }else if(keyPressed(KeyEvent.VK_RIGHT)){
            
        }
    }
    
    private void removeInstance(){
        
    }
    
    private void checkBoundary(){
        
    }
}
