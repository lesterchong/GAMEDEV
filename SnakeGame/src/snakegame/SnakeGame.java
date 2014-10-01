/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.GameObject;
import java.awt.Dimension;

/**
 *
 * @author Administrator
 */
public class SnakeGame extends GameEngine{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        GameLoader game = new GameLoader();
        
        game.setup(new SnakeGame(), new Dimension(640,640),false);
        game.start();
        // TODO code application logic here
    }

    @Override
    public GameObject getGame(int i) {
        switch(i){
            case 0: return new MainMenu(this);
            case 1: return new GameFrame(this);
            case 2: return new Hard(this);
            case 3: return new Maze(this);
            case 4: return new Maze1(this);
            case 5: return new GameOver(this);
            default: ;    
        }
        return null;
    }
}
