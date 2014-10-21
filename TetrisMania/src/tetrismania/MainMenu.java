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
import java.util.Random;

/**
 *
 * @author Lester Chong
 */
public class MainMenu extends GameObject{

    private Block block;
    private int[][] matrix;
    private int x, y;
    
    public MainMenu(GameEngine ge) {
        super(ge);
    }

    @Override
    public void initResources() {
        matrix = new int[64][64];
        
        for(int ctr = 0; ctr < 64; ctr ++){
            for(int ctr2 = 0; ctr2 < 64; ctr2++)
                matrix[ctr][ctr2] = 0;
        }
        
        block = new Block(getImage("resources/line.png"), 320, 0);
        
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
    
    public void rotate(Block block){
        
    }
    
    public int generateBlock(){
        int generated;
        Random rand = new Random();
        generated = rand.nextInt()%7;
        
        return generated;
    }
    
    public void miniRender(int piece){
        if(piece == 0){
            matrix[0][30]=1;
            matrix[0][31]=1;
            matrix[0][32]=1;
            matrix[0][33]=1;
        }else if(piece == 1){
            matrix[0][30]=1;
            matrix[1][30]=1;
            matrix[1][32]=1;
            matrix[1][31]=1;
        }else if(piece == 2){
            matrix[0][30]=1;
            matrix[1][30]=1;
            matrix[1][29]=1;
            matrix[1][28]=1;
        }else if(piece == 3){
            matrix[0][30]=1;
            matrix[0][31]=1;
            matrix[1][30]=1;
            matrix[1][31]=1;
        }else if(piece == 4){
            matrix[1][31]=1;
            matrix[1][32]=1;
            matrix[0][32]=1;
            matrix[0][33]=1;
        }else if(piece == 5){
            matrix[0][32]=1;
            matrix[1][32]=1;
            matrix[1][31]=1;
            matrix[1][33]=1;
        }else if(piece == 6){
            matrix[0][32]=1;
            matrix[0][31]=1;
            matrix[1][32]=1;
            matrix[1][33]=1;
        }
    }
    
    public void blockMovement(int piece){
        if(piece == 0 /*&& clicked left*/){
            
        }
    }
}
