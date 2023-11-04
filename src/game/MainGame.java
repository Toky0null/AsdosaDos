
package game;

import controller.Controller;
import model.Model;
import view.Game;
import view.Menu;

/**
 *  
 * @author tokyo
 */
public class MainGame {

public static void main(String[] args) {
        Model model = new Model();
        Controller gameController = Controller.getInstance(model);
        Menu menuGame = new Menu(model);

        model.setController(gameController); // Establece el controlador en el modelo

        menuGame.setVisible(true);
        System.out.println("Menú principal mostrado");
    }
}
