/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetrismania;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.GameObject;
import java.awt.Dimension;

/**
 *
 * @author Lester Chong
 */
public class TetrisMania extends GameEngine{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        
        game.setup(new TetrisMania(), new Dimension(640,640),false);
        game.start();
    }

    @Override
    public GameObject getGame(int i) {
        switch(i){
            case 0: return new MainMenu(this);
            case 1: break;
            case 2: break;
            default: ;
        }
        return null;
    }
    
}
