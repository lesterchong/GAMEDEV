/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetrismania2;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Lester Chong
 */
public class ClassicTetris extends GameObject{
    private LinkedList<Block> stackList;
    private static LinkedList<Block> blockList;
    
    public ClassicTetris(GameEngine ge) {
        super(ge);
    }

    @Override
    public void initResources() {
        stackList = new LinkedList<>();
        blockList = new LinkedList<>();
        
    }

    @Override
    public void update(long l) {
        if(blockList.isEmpty()){
            generatePiece();
        }else{
            while(!pieceIsAtBottomBoundary() && !pieceHitsStack()){
                pieceMovement(blockList);
                pieceHorizontalMovement(blockList);
            }
            blockList.clear();
        }
        
        for(int ctr=0; ctr<blockList.size(); ctr++)
            blockList.get(ctr).update(l);
    }

    @Override
    public void render(Graphics2D gd) {
        gd.setColor(Color.gray);
        gd.fillRect(0, 0, getWidth(), getHeight());
        
        for(int ctr =0; ctr<blockList.size(); ctr++){
            blockList.get(ctr).render(gd);
        }
    }
    
    private void generatePiece(){
        int piece;
        Random rand = new Random();
        piece = rand.nextInt()%7;
        
        switch(piece){
            case 0: //Straight line
                    blockList.add(new Block(getImage("resources/ssquare.png"), 100, 0));
                    blockList.add(new Block(getImage("resources/ssquare.png"), 100, 20));
                    blockList.add(new Block(getImage("resources/ssquare.png"), 100, 30));
                    blockList.add(new Block(getImage("resources/ssquare.png"), 100, 40));
                    break;
            case 1: //Inverted L
                    blockList.add(new Block(getImage("resources/ssquare.png"), 100, 0));
                    blockList.add(new Block(getImage("resources/ssquare.png"), 100, 20));
                    blockList.add(new Block(getImage("resources/ssquare.png"), 120, 20));
                    blockList.add(new Block(getImage("resources/ssquare.png"), 140, 20));
                    break;
            case 2: //Proper L
                    blockList.add(new Block(getImage("resources/ssquare.png"), 100, 0));
                    blockList.add(new Block(getImage("resources/ssquare.png"), 100, 20));
                    blockList.add(new Block(getImage("resources/ssquare.png"), 80, 20));
                    blockList.add(new Block(getImage("resources/ssquare.png"), 60, 20));
                    break;
            case 3: //Square
                    blockList.add(new Block(getImage("resources/ssquare.png"), 100, 0));
                    blockList.add(new Block(getImage("resources/ssquare.png"), 100, 20));
                    blockList.add(new Block(getImage("resources/ssquare.png"), 120, 0));
                    blockList.add(new Block(getImage("resources/ssquare.png"), 120, 20));
                    break;
            case 4: //Proper Z
                    blockList.add(new Block(getImage("resources/ssquare.png"), 100, 0));
                    blockList.add(new Block(getImage("resources/ssquare.png"), 100, 20));
                    blockList.add(new Block(getImage("resources/ssquare.png"), 80, 0));
                    blockList.add(new Block(getImage("resources/ssquare.png"), 120, 20));
                    break;
            case 5: //inverted T
                    blockList.add(new Block(getImage("resources/ssquare.png"), 100, 0));
                    blockList.add(new Block(getImage("resources/ssquare.png"), 100, 20));
                    blockList.add(new Block(getImage("resources/ssquare.png"), 80, 0));
                    blockList.add(new Block(getImage("resources/ssquare.png"), 120, 20));
                    break;
            case 6: //Inverted Z
                    blockList.add(new Block(getImage("resources/ssquare.png"), 100, 0));
                    blockList.add(new Block(getImage("resources/ssquare.png"), 100, 20));
                    blockList.add(new Block(getImage("resources/ssquare.png"), 120, 0));
                    blockList.add(new Block(getImage("resources/ssquare.png"), 80, 20));
                    break;
        }
    }
    
    private boolean pieceHitsStack(){
        for(int ctr=0; ctr<4; ctr++){
            for(int ctr2=0; ctr2<stackList.size(); ctr2++){
                if(blockList.get(ctr).getY()-20==stackList.get(ctr2).getY() && blockList.get(ctr).getX()==stackList.get(ctr2).getX())
                    return true;
            }
        }
        return false;
    }
    
    private boolean pieceIsAtBottomBoundary(){
        for(int ctr =0; ctr<blockList.size(); ctr++){
            if(blockList.get(ctr).getY()>=400)
                return true;
        }
        return false;
    }
    
    private boolean isOutsideLeftBoundary(){
        for(int ctr=0; ctr<4; ctr++){
            if(blockList.get(ctr).getX()<=0)
                return true;
        }
        return false;
    }
    
    private boolean isOutsideRightBoundary(){
        for(int ctr=0; ctr<4; ctr++){
            if(blockList.get(ctr).getX()>=200)
                return true;
        }
        return false;
    }
    
    private void pieceMovement(LinkedList<Block> blockList){        
        for(int ctr=0; ctr<blockList.size(); ctr++){
            blockList.get(ctr).setY(blockList.get(ctr).getY() + 0.4);
        }
    }
    
    private void pieceHorizontalMovement(LinkedList<Block> blockList){
        if(keyPressed(KeyEvent.VK_LEFT) && !isOutsideLeftBoundary()){
            for(int ctr=0; ctr<4; ctr++){
                blockList.get(ctr).setX(blockList.get(ctr).getX() - 20);
            }
        }else if(keyPressed(KeyEvent.VK_RIGHT) && !isOutsideRightBoundary()){
            for(int ctr=0; ctr<4; ctr++){
                blockList.get(ctr).setX(blockList.get(ctr).getX() + 20);
            }
        }else if(keyPressed(KeyEvent.VK_SPACE)){
            //Snap to bottom
        }else if(keyPressed(KeyEvent.VK_UP)){
            //Rotate Clockwise
        }else if(keyPressed(KeyEvent.VK_DOWN)){
            //Rotate CounterClockwise
        }
    }
}