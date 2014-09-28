/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snakegame;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.font.SystemFont;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author Lester Chong
 */
public class MainMenu extends GameObject{

    private Block startGame;
    private Block about;
    private Block arrow;
    private int imageHeight;
    private int position=1;
    
    public MainMenu(GameEngine ge) {
        super(ge);
    }

    @Override
    public void initResources() {
        imageHeight = 59;
        startGame = new Block(getImage("resources/StartGame.png"),146,290);
        about= new Block(getImage("resources/About.png"),146,350);
        arrow = new Block(getImage("resources/arrow.png"),100,300);
    }

    @Override
    public void update(long l) {
        if(keyPressed(KeyEvent.VK_DOWN) && position != 2){ 
            arrow.setY(arrow.getY()+imageHeight);
            position = 2;
            System.out.println(position);
        }else if(keyPressed(KeyEvent.VK_UP) && position !=1){
            arrow.setY(arrow.getY()-imageHeight);
            position = 1;
            System.out.println(position);
        }else if(keyPressed(KeyEvent.VK_ENTER)){
            switch(position){
                case 1: parent.nextGameID=1; finish(); break;
                case 2: parent.nextGameID=2; finish(); break;
                default: break;
            }
        }
        arrow.update(l);
    }

    @Override
    public void render(Graphics2D gd) {
        gd.setColor(Color.white);
        gd.fillRect(0, 0, getWidth(), getHeight());
        
        startGame.render(gd);
        about.render(gd);
        arrow.render(gd);
    }
    
}
