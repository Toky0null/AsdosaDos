package controller;

import java.util.Random;
import java.util.Set;
import javax.swing.ImageIcon;
import model.Model;
import view.Game;
import view.Stats;


/**
 * Clase Controller que maneja la interacción entre el modelo y la vista.
 */
public class Controller {
    private static Controller instance;
    private Model model;
    private Game view;
    private Random random = new Random();
    private Stats end;
    
    /**
     * Constructor de Controller.
     * @param gameModel el modelo del juego
     */
     private Controller(Model gameModel){
        this.model = gameModel;
        System.out.println("Controller: Controlador creado con el modelo.");
    }
    
    // Método estático que devuelve la única instancia del controlador
    public static Controller getInstance(Model gameModel) {
        if (instance == null) {
            instance = new Controller(gameModel);
            System.out.println("Controller: retorna instancia del ");
        }
        return instance;
    }
     
     
    public ImageIcon getImage(int index) {
        System.out.println("Controller: Obteniendo imagen con índice: " + index);
        return model.getImage(index);
    }
    
    /**
     * Asigna la vista al controlador.
     * @param view la vista del juego
     */
    public void setView(Game view) {
        this.view = view;
        System.out.println("Controller: Vista existente establecida en el controlador.");
    }

    /**
     * Obtiene el icono del corazón del modelo.
     * @return el icono del corazón
     */
        
    public ImageIcon getnullIcon() {
              System.out.println("Controller: Obteniendo icono null");
              return model.getnullIcon();
           }
    

    // Método para aumentar la dificultad del juego
 
    // Método para verificar si dos paneles tienen la misma imagen
    
  // Método para iniciar la lógica del juego
    public void initializeGameLogic() {
        // Implementar lógica de inicio del juego
        model.initializeGame();
   }
    // Método para obtener una imagen aleatoria para un panel
    public ImageIcon getImageForPanel(int panelIndex, Set<Integer> usedImageIndices) {
        // La lógica de obtención de la imagen aleatoria se implementará aquí
        return model.getRandomImage(panelIndex, usedImageIndices);
    }
     // Método para resetear el juego para la siguiente ronda
//    public void resetGameForNextRound() {
//        // Implementar reseteo del juego para la siguiente ronda
//        model.resetForNextRound();
//    }
    public void controllerupdatePanels(int[] panelIndices){ 
        view.updatePanels(panelIndices);
    }

    public ImageIcon getImageForPanelSingle(int index) {   
      return model.siglGetRandomImage(index);
    }

    public void controllerupdateSigPanels(int panelIndice) {
        view.sigPdatePanels(panelIndice);
    }
//    public void panel9Clicked() {
//        model.panel9Action();
//    }

    public ImageIcon getHeartIconOff() {
        System.out.println("Controller: Obteniendo icono de corazón off.");
        return model.getHeartIconOff();
    
    }
     public ImageIcon getHeartIcon() {
        System.out.println("Controller: Obteniendo icono de corazón.");
        return model.getHeartIcon();
    }
     public void updateLifeIcon(int lifeIndex, boolean isActive) {
        ImageIcon icon = isActive ? getHeartIcon() : getHeartIconOff();
        view.updateLifeIcon(lifeIndex, icon);
    }
    public void setEnd(int totalAttempts, int totalSuccesses, int totalFailures){
    
    
        end = new Stats(totalAttempts, totalSuccesses, totalFailures);
        end.setVisible(true);
    }

    public int getCurrentScore() {
        return model.getScore();
    }
            
    
}
