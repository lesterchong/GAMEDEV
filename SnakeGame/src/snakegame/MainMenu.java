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
public class MainMenu extends GameObject{

    private Block normal;
    private Block hard;
    private Block maze;
    private Block maze2;
    private Block arrow;
    private int imageHeight;
    private int position=1;
    
    public MainMenu(GameEngine ge) {
        super(ge);
    }

    @Override
    public void initResources() {
        imageHeight = 59;
        normal = new Block(getImage("resources/Normal.png"),150,231);
        hard = new Block(getImage("resources/hard.png"),146,290);
        maze = new Block(getImage("resources/maze1.png"),146,349);
        maze2 = new Block(getImage("resources/maze2.png"),146,408);
        arrow = new Block(getImage("resources/arrow.png"),104,240);
    }

    @Override
    public void update(long l) {
        if(keyPressed(KeyEvent.VK_DOWN) && position != 4){ 
            arrow.setY(arrow.getY()+imageHeight);
            position++;
            System.out.println(position);
        }else if(keyPressed(KeyEvent.VK_UP) && position !=1){
            arrow.setY(arrow.getY()-imageHeight);
            position--;
            System.out.println(position);
        }else if(keyPressed(KeyEvent.VK_ENTER)){
            switch(position){
                case 1: parent.nextGameID=1; finish(); break;
                case 2: parent.nextGameID=2; finish(); break;
                case 3: parent.nextGameID=3; finish(); break;
                case 4: parent.nextGameID=4; finish(); break;
                default: break;
            }
        }
        arrow.update(l);
    }

    @Override
    public void render(Graphics2D gd) {
        gd.setColor(Color.white);
        gd.fillRect(0, 0, getWidth(), getHeight());
        
        normal.render(gd);
        hard.render(gd);
        maze.render(gd);
        maze2.render(gd);
        arrow.render(gd);
    }
    
}
