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
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Random;
import snakegame.Block;

/**
 *
 * @author Lester Chong
 */
public class MainGame extends GameObject{

    private int spawnValue, score;
    private Block main;
    private LinkedList <Block> up = new LinkedList<>();
    private LinkedList <Block> down = new LinkedList<>();
    private LinkedList <Block> left = new LinkedList<>();
    private LinkedList <Block> right = new LinkedList<>();
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
        checkUserInput();
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
            up.get(ctr).setY(up.get(ctr).getY() - 0.05*dimension);
        }
        for(int ctr = 0; ctr < down.size(); ctr++){
            down.get(ctr).setY(down.get(ctr).getY() + 0.05*dimension);
        }
        for(int ctr = 0; ctr < left.size(); ctr++){
            left.get(ctr).setX(left.get(ctr).getX() - 0.05*dimension);
        }
        for(int ctr = 0; ctr < right.size(); ctr++){
            right.get(ctr).setX(right.get(ctr).getX() + 0.05*dimension);
        }
    }
    
    public void checkUserInput(){
        if(keyPressed(KeyEvent.VK_UP) && checkBoundary("up")){
            removeInstance(up);
            score++;
            System.out.println(score);
        }else if(keyPressed(KeyEvent.VK_DOWN) && checkBoundary("down")){
            removeInstance(down);
            score++;
            System.out.println(score);
        }else if(keyPressed(KeyEvent.VK_LEFT) && checkBoundary("left")){
            removeInstance(left);
            score++;
            System.out.println(score);
        }else if(keyPressed(KeyEvent.VK_RIGHT) && checkBoundary("right")){
            removeInstance(right);
            score++;
            System.out.println(score);
        }
    }
    
    private void removeInstance(LinkedList<Block> list){
        list.removeFirst();
    }
    
    private boolean checkBoundary(String direction){
        Block temp;
        Iterator itr;
        
        if(direction.equals("up")){
            itr = up.iterator();
            while(itr.hasNext()){
                temp = (Block)itr.next();
                if(temp.getY()<=main.getY()+150 && temp.getY()>=main.getY()){
                    return true;
                }
            }
        }else if(direction.equals("down")){
            itr = down.iterator();
            while(itr.hasNext()){
                temp = (Block)itr.next();
                if(temp.getY()<=main.getY()+150 && temp.getY()>=main.getY()){
                    return true;
                }
            }
        }else if(direction.equals("left")){
            itr = left.iterator();
            while(itr.hasNext()){
                temp = (Block)itr.next();
                if(temp.getX()<=main.getX()+150 && temp.getX()>=main.getX()){
                    return true;
                }
            }
        }else if(direction.equals("right")){
            itr = right.iterator();
            while(itr.hasNext()){
                temp = (Block)itr.next();
                if(temp.getX()<=main.getX()+150 && temp.getX()>=main.getX()){
                    return true;
                }
            }
        }
        return false;
    }
}
