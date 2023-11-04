/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.Controller;
import javax.swing.JFrame;
import model.Model;
import java.awt.*;
import java.awt.event.KeyEvent;
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

/**
 *
 * @author tokyo
 */
public class Game extends JFrame {
    private static Game instance;
    private JPanel jPanel1, jPanel2, jPanel3, jPanel4, jPanel5, jPanel6, jPanel7, jPanel8, jPanel9, livesPanel;
    private JLabel score, heart1, heart2, heart3;
    private JLabel countdownLabel;
    private final int countdownValue = 3;// Etiqueta para mostrar la cuenta regresiva
    private Controller controller;
    private Model model;private Map<Integer, Integer> panelImageIndices;

    private Game(Controller gameController, Model gameModel) {
        System.out.println("Game: Constructor llamado.");
        
        this.controller = gameController;
        this.model = gameModel;
        initComponents();
        initLives();
        setLayoutComponents();
        //Timer countdownCheckTimer = new Timer(1000, e -> updateCountdownLabel());
        //countdownCheckTimer.start();
        // setupWindow(); 
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(812, 650);  // Ajusta el tamaño de la ventana directamente aquí.
        setResizable(false);
        setLocationRelativeTo(null);
        startCountdown();
        panelImageIndices = new HashMap<>();

        // Evento de tecla para el JFrame
        this.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                        handleAction();
                }
            }

            
        });
        this.setFocusable(true);
        this.requestFocusInWindow();

    }
    
    
    public static Game getInstance(Controller gameController, Model gameModel) {
        if (instance == null) {
            instance = new Game(gameController,gameModel);
            System.out.println("Game: instancia creada!");
        }
        return instance;
    }

    private void initComponents() {
        if (controller == null) {
            throw new RuntimeException("El controlador no ha sido inicializado.");
        }
        score = new JLabel("Score");
        score.setFont(new java.awt.Font("Fira Code Retina", 0, 24));
        jPanel1 = createPanel(controller.getnullIcon());
        jPanel2 = createPanel(controller.getnullIcon());
        jPanel3 = createPanel(controller.getnullIcon());
        jPanel4 = createPanel(controller.getnullIcon());
        jPanel5 = createPanel(controller.getnullIcon());
        jPanel6 = createPanel(controller.getnullIcon());
        jPanel7 = createPanel(controller.getnullIcon());
        jPanel8 = createPanel(controller.getnullIcon());

        jPanel9 = new JPanel();
        jPanel9.setBackground(Color.WHITE);
        jPanel9.setPreferredSize(new Dimension(110, 95));
        jPanel9.setBorder(new LineBorder(Color.GREEN));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                controller.panel9Clicked();
                  System.out.println("Model: Acción del panel 9 ejecutada!");
                  model.setButtonClicked(true);
            }
        });

//        countdownLabel = new JLabel("3");  // Inicia con el valor "3"
//        countdownLabel.setFont(new Font("Fira Code Retina", Font.BOLD, 36));
//        countdownLabel.setHorizontalAlignment(JLabel.CENTER);
//        countdownLabel.setPreferredSize(new Dimension(100, 100)); // Establece un tamaño preferido
        countdownLabel = new JLabel(String.valueOf(countdownValue));
        countdownLabel.setFont(new Font("Fira Code Retina", Font.BOLD, 36));
        countdownLabel.setHorizontalAlignment(JLabel.CENTER);

    }

    private void setLayoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        //gbc.fill = GridBagConstraints.BOTH; // Para que los componentes llenen las celdas
        gbc.weightx = 1; // Asigna un peso en x
        gbc.weighty = 1; // Asigna un peso en y

        // Posicionamiento del Score en la esquina superior izquierda
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST; // Alinea el componente hacia el noroeste
        add(score, gbc);

        // Restablecemos las restricciones para los paneles
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER; // Alinea el componente al centro

        // Posicionamiento de los paneles
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(jPanel1, gbc);

        gbc.gridx = 1;
        add(jPanel2, gbc);

        gbc.gridx = 2;
        add(jPanel3, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        add(jPanel4, gbc);

        gbc.gridx = 1;
        add(jPanel5, gbc);

        gbc.gridx = 2;
        add(jPanel6, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        add(jPanel7, gbc);

        gbc.gridx = 1;
        add(jPanel8, gbc);

        gbc.gridy = 4;
        gbc.gridx = 2;
        add(jPanel9, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(countdownLabel, gbc);
        
        // Posicionamiento del contador de vidas en la esquina superior derecha
        GridBagConstraints gbcLives = new GridBagConstraints();
        gbcLives.gridx = 2;
        gbcLives.gridy = 0;
        gbcLives.anchor = GridBagConstraints.NORTHEAST; // Alinea el componente hacia el noreste
        add(livesPanel, gbcLives);

    }

//    private void setupWindow() {
//
//    }
    
    private void handleAction() {
         System.out.println("Accion ejecutada!");
    }
    private void initLives() {
        ImageIcon heartIconOriginal = controller.getHeartIcon();
        if (heartIconOriginal != null) {
            Image scaledHeart = heartIconOriginal.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            ImageIcon heartIcon = new ImageIcon(scaledHeart);

            // Inicializamos los JLabels con la imagen del corazón
            heart1 = new JLabel(heartIcon);
            heart2 = new JLabel(heartIcon);
            heart3 = new JLabel(heartIcon);
        }

        livesPanel = new JPanel();
        livesPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        livesPanel.add(heart1);
        livesPanel.add(heart2);
        livesPanel.add(heart3);
    }
    
//    private void updateLifeIcons(int livesRemaining) {
//    // Suponiendo que heart1 corresponde a la primera vida, heart2 a la segunda, etc.
//    heart1.setIcon(livesRemaining >= 1 ? getHeartIcon() : getEmptyHeartIcon());
//    heart2.setIcon(livesRemaining >= 2 ? getHeartIcon() : getEmptyHeartIcon());
//    heart3.setIcon(livesRemaining >= 3 ? getHeartIcon() : getEmptyHeartIcon());
//    }
//    
     public void updateLifeIcon(int lifeIndex, ImageIcon icon) {
        ImageIcon scaledIcon = scaleIcon(icon, 30, 30);
        switch (lifeIndex) {
            case 1:
                heart1.setIcon(scaledIcon);
                break;
            case 2:
                heart2.setIcon(scaledIcon);
                break;
            case 3:
                heart3.setIcon(scaledIcon);
                break;
            default:
                throw new IllegalArgumentException("Índice de vida no válido: " + lifeIndex);
        }
        // Después de cambiar el ícono, actualiza el componente para reflejar el cambio
        repaint();
        revalidate();
    }

    private ImageIcon scaleIcon(ImageIcon icon, int width, int height) {
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
    
    

    private JPanel createPanel(ImageIcon image) {
        JPanel panel = new JPanel(new BorderLayout());
        if (image != null) {
            Image scaledImage = image.getImage().getScaledInstance(120, 100, Image.SCALE_SMOOTH);
            JLabel label = new JLabel(new ImageIcon(scaledImage));
            panel.add(label, BorderLayout.CENTER);
        }
        panel.setPreferredSize(new Dimension(120, 100)); // Define un tamaño para los paneles
        return panel;
    }

    
    
   private void updateCountdownLabel(Timer countdownTimer) {
    System.out.println("Game: Actualizando etiqueta de cuenta regresiva...");
    int currentValue = Integer.parseInt(countdownLabel.getText());
    if (currentValue > 1) {
        countdownLabel.setText(String.valueOf(currentValue - 1));
    } else {
        countdownLabel.setText("¡Ya!");
        countdownTimer.stop(); // Detiene el temporizador de la cuenta regresiva
        // Inicia un nuevo temporizador que espera un segundo antes de quitar la etiqueta y empezar el juego
        new Timer(1000, e -> {
            System.out.println("Game: Cuenta regresiva finalizada.");
            remove(countdownLabel); // Quita la etiqueta "¡Ya!"
            revalidate();
            repaint();
            ((Timer) e.getSource()).stop(); // Detiene el temporizador que muestra "¡Ya!"
            // Aquí se inicia el juego después de que el mensaje "¡Ya!" ha sido mostrado durante un segundo
            model.initializeGame(); 
        }).start();
    }
}
 
//   private void updatePanelImage(JPanel panel, ImageIcon newImage) {
//        if(newImage != null) {
//            Image scaledImage = newImage.getImage().getScaledInstance(120, 100, Image.SCALE_SMOOTH);
//            JLabel label = new JLabel(new ImageIcon(scaledImage));
//            panel.removeAll();
//            panel.add(label, BorderLayout.CENTER);
//            panel.revalidate();
//            panel.repaint();
//        }  
//    }

// Método para actualizar los paneles con índices específicos
    public void updatePanels(int[] panelIndex) {
     Set<Integer> usedImageIndices = new HashSet<>();
       
    for (int index : panelIndex) {
        JPanel panelToUpdate = getPanelByIndex(index);
        if (panelToUpdate != null) {
            ImageIcon newImage = controller.getImageForPanel(index, usedImageIndices);
            updatePanelWithImage(panelToUpdate, newImage);
        }
    }
}
 
    public void sigPdatePanels(int panelIndex) {    
   JPanel panelToUpdate = getPanelByIndex(panelIndex);
    if (panelToUpdate != null) {
        ImageIcon newImage = controller.getImageForPanelSingle(panelIndex); 
        updatePanelWithImage(panelToUpdate, newImage);
        
    }
}
  
   
   // Método para obtener el panel basado en el índice
    private JPanel getPanelByIndex(int index) {
    // Implementación de ejemplo, necesitará ajustarse basado en la lógica real
    switch (index) {
        case 1: return jPanel1;
        case 2: return jPanel2;
        case 3: return jPanel3;
        case 4:return jPanel4;
        case 5: return jPanel5;
        case 6: return jPanel6;
        case 7: return jPanel7;
        case 8: return jPanel8;
        default: return null;
    }
}
    private void updatePanelWithImage(JPanel panel, ImageIcon image) {
    // Implementación de ejemplo, necesitará ajustarse basado en la lógica real
    if(image != null){
    Image scaledImage = image.getImage().getScaledInstance(120, 100, Image.SCALE_SMOOTH);
    JLabel label = new JLabel(new ImageIcon(scaledImage));
    panel.removeAll();
    panel.add(label);
    panel.revalidate();
    panel.repaint();
    
    }
}
   
   
   
  // Método para iniciar el juego
    public void startGame() {
    controller.initializeGameLogic();
}

// Método para aumentar la dificultad del juego



   
    public void startCountdown() {
    countdownLabel.setText("3"); // Inicializa la etiqueta con el valor inicial
    Timer countdownTimer = new Timer(1000, e -> updateCountdownLabel((Timer) e.getSource()));
    countdownTimer.start();
    }    
    
    private ImageIcon getHeartIcon() {
    // Retorna el ícono del corazón (vida llena)
    ImageIcon heartIconOriginal = controller.getHeartIcon();
    Image scaledHeart = heartIconOriginal.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    return new ImageIcon(scaledHeart);
    }   
    private ImageIcon getEmptyHeartIcon() {
    // Retorna el ícono de corazón vacío (vida perdida)
    // Aquí deberías cargar la imagen correspondiente al corazón vacío y escalarla igual que la del corazón lleno
    ImageIcon emptyHeartIconOriginal = controller.getHeartIconOff(); // Suponiendo que tienes este método en tu controlador
    Image scaledEmptyHeart = emptyHeartIconOriginal.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    return new ImageIcon(scaledEmptyHeart);
}
   
 
}
    