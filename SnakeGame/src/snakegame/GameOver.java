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
    private Block mainmenu;
    private Block arrow;
    private int position = 1;
    
    public GameOver(GameEngine ge) {
        super(ge);
    }

    @Override
    public void initResources() {
        gameOver = new Block(getImage("resources/GameOver.png"),168,281);
        mainmenu = new Block(getImage("resources/MainMenu.png"),168,337);
        arrow = new Block(getImage("resources/arrow.png"),136,340);
    }

    @Override
    public void update(long l) {
        if(keyPressed(KeyEvent.VK_ENTER)){
            parent.nextGameID = 0;
            finish();
        }
    }

    @Override
    public void render(Graphics2D gd) {
        gd.setColor(Color.white);
        gd.fillRect(0, 0, getWidth(), getHeight());
        
        gameOver.render(gd);
        mainmenu.render(gd);
        arrow.render(gd);
        
    }
    
}
