/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import com.golden.gamedev.GameLoader;
import java.awt.Dimension;

/**
 *
 * @author Administrator
 */
public class SnakeGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameFrame gf = new GameFrame();
        GameLoader game = new GameLoader();
        
        game.setup(gf,new Dimension(640,640),false);
        game.start();
        // TODO code application logic here
    }
}
