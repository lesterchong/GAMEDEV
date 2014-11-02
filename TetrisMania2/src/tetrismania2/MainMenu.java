/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetrismania2;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.GameObject;
import java.awt.Dimension;

/**
 *
 * @author Lester Chong
 */
public class MainMenu extends GameEngine{

    public static void main(String[] args){
        GameLoader game = new GameLoader();
        
        game.setup(new MainMenu(), new Dimension(200,400),false);
        game.start();
    }
    
    @Override
    public GameObject getGame(int i) {
        switch(i){
            case 0: return new ClassicTetris(this);
            default: ;
        }
        return null;
    }
    
}
