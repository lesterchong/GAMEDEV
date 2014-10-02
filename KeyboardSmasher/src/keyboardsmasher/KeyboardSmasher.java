/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package keyboardsmasher;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.GameObject;
import java.awt.Dimension;

/**
 *
 * @author Lester Chong
 */
public class KeyboardSmasher extends GameEngine{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        
        game.setup(new KeyboardSmasher(), new Dimension(640,640),false);
        game.start();
    }

    @Override
    public GameObject getGame(int i) {
        switch(i){
            case 0: return new MainGame(this);
            default: break;    
        }
        return null;
    }
    
}
