package view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import model.Model;
//import model.TextPrompt;
import controller.Controller;

public class Menu extends JFrame {

    private JLabel adosaII;
    private JLabel creators;   
    private JButton getOut;
    private JButton init;
    private JButton instructions;
    private JButton purposeButton;  //botón "Para qué sirve"
    public JTextField TFname;
    private BackGround background;
    public String playerT;
    Controller gameController;
    Model gameModel;
    Game gameView;

    public Menu(Model existingModel) {
        super("Menú Principal");
        settWindow();
        initiaLabels();
        initButtons();
        //initTextFields();
        initialWindowGame();   
        this.gameModel = existingModel;
        gameController = Controller.getInstance(gameModel);
        

        //gameModel.setController(gameController);

    }

    public void settWindow(){
        background = new BackGround();
        
        this.setContentPane(background);
        this.setSize(380, 430);  // Incremento en el tamaño de la ventana
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }

    public void initiaLabels(){
        adosaII = new JLabel("ADOSADOS");
        adosaII.setOpaque(true);
        adosaII.setBounds(90, 30, 200, 40);
        adosaII.setFont(new Font("Arial Black", 1, 30));
        adosaII.setHorizontalAlignment(SwingConstants.CENTER);
        adosaII.setBackground(new Color(255, 255, 255, 0));
        this.add(adosaII);  

        creators = new JLabel("by:@Toky0null");
        creators.setOpaque(true);
        creators.setBounds(182, 365, 200, 30);  // Ajuste en la posición
        creators.setFont(new Font("Arial", 0, 11));
        creators.setHorizontalAlignment(SwingConstants.CENTER);
        creators.setBackground(new Color(255, 255, 255, 0));
        this.add(creators);
    }

    public void initButtons(){
        init = new JButton("Iniciar");
        init.setOpaque(true);
        init.setBounds(135, 145, 110, 35);  // Ajuste en la posición
        init.setFont(new Font("Arial Black", 0, 16));
        init.setHorizontalAlignment(SwingConstants.CENTER);
        init.setBackground(new Color(240, 240, 240));
        init.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.add(init);

        instructions = new JButton("Instrucciones");
        instructions.setOpaque(true);
        instructions.setBounds(135, 190, 110, 35);  // Ajuste en la posición
        instructions.setFont(new Font("Arial Black", 0, 11));
        instructions.setHorizontalAlignment(SwingConstants.CENTER);
        instructions.setBackground(new Color(240, 240, 240));
        instructions.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.add(instructions);

        purposeButton = new JButton("Para qué sirve");
        purposeButton.setOpaque(true);
        purposeButton.setBounds(135, 235, 110, 35);  // Posición del nuevo botón
        purposeButton.setFont(new Font("Arial Black", 0, 10));
        purposeButton.setHorizontalAlignment(SwingConstants.CENTER);
        purposeButton.setBackground(new Color(240, 240, 240));
        purposeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.add(purposeButton);

        getOut = new JButton("Salir");
        getOut.setOpaque(true);
        getOut.setBounds(135, 280, 110, 35);  // Ajuste en la posición
        getOut.setFont(new Font("Arial Black", 0, 16));
        getOut.setHorizontalAlignment(SwingConstants.CENTER);
        getOut.setBackground(new Color(240, 240, 240));
        getOut.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.add(getOut);
    }

    
    /**
  public void initTextFields() {
        TFname = new JTextField(10);
        TFname.setBounds(115, 85, 150, 35);  // Ajuste en la posición
        TextPrompt placeholder = new TextPrompt("Ingrese su nombre", TFname);
        placeholder.setFont(new Font("Arial", 2, 13));
        placeholder.setForeground(Color.lightGray);
        this.add(TFname);
    }
  
 */
   

public void initialWindowGame() {
        init.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameView == null) {
                    // Mostrar la cuenta regresivas
                    // ...
                    gameView = Game.getInstance(gameController, gameModel);
                    // Una vez que la cuenta regresiva termine, mostrar Game1
                    gameController.setView(gameView); // Establece la vista en el controlador
                    gameView.setVisible(true);
                    System.out.println("pantalla del juego se ha iniciado");
                    
                    //gameModel.startGame();
                } else {
                    System.out.println("La pantalla del juego ya está iniciada.");
                }
            }
        });
        
        
//     Listener para el botón "Para qué sirve"
    purposeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           // handlePurposeButton();
        }
    });
        
        // Listener para el botón Instrucciones
    instructions.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           // handleInstructionsButton();
        }
    });
    
    
     // Listener para el botón Salir
    getOut.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //handleGetOutButton();
        }
    });

    
        
    } 


}
