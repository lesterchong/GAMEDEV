/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snakegame;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

/**
 *
 * @author Lester Chong
 */
public class Hard extends GameObject{

    public Hard(GameEngine ge) {
        super(ge);
    }

    Block Food;
    LinkedList<Block> Snake = new LinkedList<>();
    double snakeX, snakeY; // snake
    double foodX, foodY; // food
    
    int snakeDirection; // snake direction
    
    final double dimension = 16; // 16 x 16

    @Override
    public void initResources() {
        snakeX = 10;
        snakeY = 12;
        snakeDirection = 3;
        
        foodX = 20;
        foodY = 20;
        
        Snake.add(new Block(getImage("resources/snakeblock.png"),snakeX*dimension,snakeY*dimension));
        Food = new Block(getImage("resources/foodblock.png"),foodX*dimension,foodY*dimension);
    }
    
    @Override
    public void update(long l) {
        if (Math.abs(Snake.get(0).getX() - Food.getX()) < dimension && Math.abs(Snake.get(0).getY() - Food.getY()) < dimension){
            resetFood();
            Snake.addLast(new Block(getImage("resources/snakeblock.png"),Snake.getFirst().getX()*dimension,Snake.getFirst().getY()*dimension));
        }
        
        if(checkYBorder() && checkXBorder()){
            readInput();
            moveSnake();
            Snake.get(0).update(l);
            Food.update(l);
        }else{
            parent.nextGameID=5;
            finish();
        }
        for(int ctr=0; ctr<Snake.size(); ctr++){
            Snake.get(ctr).update(l);
        }
    }  

    @Override
    public void render(Graphics2D gd) {
        gd.setColor(Color.gray);
        gd.fillRect(0, 0, getWidth(), getHeight());
        
        Food.render(gd);
        
        for(int ctr=0; ctr<Snake.size(); ctr++){
            Snake.get(ctr).render(gd);
        }
        
        for(int ctr=Snake.size()-1; ctr>=0; ctr--){
            if(ctr >= 1){
                Snake.get(ctr).setX(Snake.get(ctr-1).getX());
                Snake.get(ctr).setY(Snake.get(ctr-1).getY());
            }
        }
    }
    
    public void moveSnake(){
        if (snakeDirection == 1)
            snakeY -= .75;
        else if (snakeDirection == 2)
            snakeX += .75;
        else if (snakeDirection == 3)
            snakeY += .75;
        else if (snakeDirection == 4)
            snakeX -= .75;
        
        Snake.get(0).setX(snakeX*dimension);
        Snake.get(0).setY(snakeY*dimension);
    }
    
    public void readInput(){
        if (keyPressed(KeyEvent.VK_UP) && snakeDirection != 3) //
               snakeDirection = 1;
        if (keyPressed(KeyEvent.VK_RIGHT) && snakeDirection != 4)
                snakeDirection = 2;
        if (keyPressed(KeyEvent.VK_DOWN) && snakeDirection != 1)
                snakeDirection = 3;
        if (keyPressed(KeyEvent.VK_LEFT) && snakeDirection != 2)
                snakeDirection = 4;
    }
    
    public void resetFood(){
        int newX, newY;
        newX = ((int)(Math.random() * 100)) % 40;
        newY = ((int)(Math.random() * 100)) % 40;
        
        Food.setX((double)newX * dimension);
        Food.setY((double)newY * dimension);
    }
    
    //returns true if snake is at allowed space
    public boolean checkXBorder(){
        if(Snake.get(0).getX()>0 && Snake.get(0).getX()<624)
            return true;
        return false;
    }
    
    public boolean checkYBorder(){
        if(Snake.get(0).getY()>0 && Snake.get(0).getY()<624)
            return true;
        return false;
    }
    
    //Return if collision is detected
    public boolean checkCollision(){
        for(int ctr=1; ctr<Snake.size(); ctr++){
            if(Snake.getFirst().getX()==Snake.get(ctr).getX() || Snake.getFirst().getY()==Snake.get(ctr).getY())
                return true;
        }
        return false;
    }
    
}
