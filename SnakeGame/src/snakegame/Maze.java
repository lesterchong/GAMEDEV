package snakegame;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class Maze extends GameObject{
    
    Block Food;
    LinkedList<Block> Snake = new LinkedList<>();
    LinkedList<Block> Obstacle = new LinkedList<>();
    double snakeX, snakeY; // snake
    double foodX, foodY; // food
    
    int snakeDirection; // snake direction
    
    final double dimension = 16; // 16 x 16

    public Maze(GameEngine ge) {
        super(ge);
    }

    @Override
    public void initResources() {
        snakeX = 10;
        snakeY = 12;
        snakeDirection = 3;
        
        foodX = 20;
        foodY = 20;
        
        Snake.add(new Block(getImage("resources/snakeblock.png"),snakeX*dimension,snakeY*dimension));
        
        Obstacle.add(new Block(getImage("resources/block1.png"),75,100));
        Obstacle.add(new Block(getImage("resources/block1.png"),75,320));
        Obstacle.add(new Block(getImage("resources/block1.png"),75,540));
        
        Obstacle.add(new Block(getImage("resources/block1.png"),364,100));
        Obstacle.add(new Block(getImage("resources/block1.png"),364,320));
        Obstacle.add(new Block(getImage("resources/block1.png"),364,540));
        
        Food = new Block(getImage("resources/foodblock.png"),foodX*dimension,foodY*dimension);
    }
    
    @Override
    public void update(long l) {
        if (Math.abs(Snake.get(0).getX() - Food.getX()) < dimension && Math.abs(Snake.get(0).getY() - Food.getY()) < dimension){
            resetFood();
            Snake.addLast(new Block(getImage("resources/snakeblock.png"),999,999));
        }
        
        if(checkYBorder() && checkXBorder() && checkMazeCollision()){
            readInput();
            moveSnake();
            Snake.get(0).update(l);
            Food.update(l);
        }else{
            parent.nextGameID=3;
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
        
        for(int ctr=0; ctr<Obstacle.size(); ctr++){
            Obstacle.get(ctr).render(gd);
        }
        
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
            snakeY -= .25;
        else if (snakeDirection == 2)
            snakeX += .25;
        else if (snakeDirection == 3)
            snakeY += .25;
        else if (snakeDirection == 4)
            snakeX -= .25;
        
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
        int newX, newY, check;
        

        do{
        check =0;
        newX = ((int)(Math.random() * 100)) % 40;
        newY = ((int)(Math.random() * 100)) % 40;
        
        Food.setX((double)newX * dimension);
        Food.setY((double)newY * dimension);

        for(int ctr=0; ctr< Obstacle.size();ctr++){
            if((Food.getX() >= Obstacle.get(ctr).getX() && Food.getX() <= (Obstacle.get(ctr).getX() + Obstacle.get(ctr).getWidth())) && (Food.getY() >= Obstacle.get(ctr).getY() && Food.getY() <= (Obstacle.get(ctr).getY() + Obstacle.get(ctr).getHeight())))
                check++;
        }
        }while(check > 0);
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
                return false;
        }
        
        return true;
    }
    
    /*public boolean checkMazeCollision(){
        
        for(int ctr=0; ctr< Obstacle.size(); ctr++){
            if((Snake.getFirst().getX()>= Obstacle.get(ctr).getX() 
                && snakeX <= Obstacle.get(ctr).getX() + Obstacle.get(ctr).getWidth())
                && (Snake.getFirst().getY()>= Obstacle.get(ctr).getY() 
                && Snake.getFirst().getY() <= Obstacle.get(ctr).getY() + Obstacle.get(ctr).getHeight()))
                
                    return false;   
        }
        return true;
    }*/
    
    public boolean checkMazeCollision(){

        for(int ctr=0; ctr< Obstacle.size(); ctr++){
            if((Snake.getFirst().getX() >= Obstacle.get(ctr).getX() && Snake.getFirst().getX() <= (Obstacle.get(ctr).getX() + Obstacle.get(ctr).getWidth())) && (Snake.getFirst().getY() >= Obstacle.get(ctr).getY() && Snake.getFirst().getY() <= (Obstacle.get(ctr).getY() + Obstacle.get(ctr).getHeight())))
                return false;
        }

        return true;
    }
    
}
