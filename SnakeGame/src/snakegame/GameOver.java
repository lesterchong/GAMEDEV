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

/**
 *
 * @author Lester Chong
 */
public class GameOver extends GameObject{

    private Block gameOver;
    private Block yes;
    private Block no;
    private Block arrow;
    private int position = 1;
    
    public GameOver(GameEngine ge) {
        super(ge);
    }

    @Override
    public void initResources() {
        gameOver = new Block(getImage("resources/Retry.png"),168,281);
        yes = new Block(getImage("resources/Yes.png"),236,337);
        no = new Block(getImage("resources/No.png"),333,337);
        arrow = new Block(getImage("resources/arrow.png"),190,337);
    }

    @Override
    public void update(long l) {
        if(keyPressed(KeyEvent.VK_LEFT) && position != 1){
            arrow.setX(arrow.getX()-102);
            position = 1;
        }else if(keyPressed(KeyEvent.VK_RIGHT) && position != 0){
            arrow.setX(arrow.getX()+102);
            position = 0;
        }else if(keyPressed(KeyEvent.VK_ENTER)){
            switch(position){
                case 0: parent.nextGameID = 0; finish(); break;
                case 1: parent.nextGameID = 1; finish(); break;
                default: break;    
            }
        }
        
        arrow.update(l);
    }

    @Override
    public void render(Graphics2D gd) {
        gd.setColor(Color.white);
        gd.fillRect(0, 0, getWidth(), getHeight());
        
        gameOver.render(gd);
        arrow.render(gd);
        yes.render(gd);
        no.render(gd);
    }
    
}
