/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetrismania;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author Lester Chong
 */
public class MainMenu extends GameObject{

    private Block block;
    public MainMenu(GameEngine ge) {
        super(ge);
    }

    @Override
    public void initResources() {
        block = new Block(getImage("resources/square.png"), 320, 0);
    }

    @Override
    public void update(long l) {
        blockMovement(block);
        block.update(l);
    }

    @Override
    public void render(Graphics2D gd) {
        gd.setColor(Color.gray);
        gd.fillRect(0, 0, getWidth(), getHeight());
        
        block.render(gd);
    }
    
    public void blockMovement(Block block){
        if(keyPressed(KeyEvent.VK_LEFT) && checkLeftBoundary(block)){
            block.setX(block.getX()-40);
        }else if(keyPressed(KeyEvent.VK_RIGHT) && checkRightBoundary(block)){
            block.setX(block.getX()+40);
            System.out.println(block.getX());
        }
        block.setY(block.getY()+.5);
    }
    
    public boolean checkLeftBoundary(Block block){
        if(block.getX()>0)
            return true;
        return false;
    }
    
    public boolean checkRightBoundary(Block block){
        if(block.getX()<600)
            return true;
        return false;
    }
    
}
