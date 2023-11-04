/**
 *
 * @author tokyo - Juna Sebastian Hurtado Batioja 2179288
 * @author kevin David Londoño Valencia-2224551
 */

package view;

import controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Instrucciones extends JFrame {
    private JLabel titleLabel;
    private JLabel instructionLabel;
    private JButton startButton;
    //private Player playerInstr;
    //private Music soundtrack;
    private JTextField playerNameField;
    private Controller controller;
   // private boolean soundstarted;
    //private float volume;
    //private Levels level1;
    //private InitialWindow star;

    public Instrucciones (Controller controller) {
        super("Geometric-Manual de instrucciones");
        setSize(750, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Container paneIntr = getContentPane();
        paneIntr.setLayout(new BorderLayout());
        playerNameField = new JTextField();
        // Crear un panel para centrar el contenido
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Márgenes

        titleLabel = new JLabel("INSTRUCCIONES");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        centerPanel.add(titleLabel, createGridBagConstraints(0, 0, 2, 1, GridBagConstraints.CENTER));

        instructionLabel = new JLabel("Observa la figura de arriba (lado izquierdo) y selecciona entre las tres figuras cuál es la correcta");
        instructionLabel.setHorizontalAlignment(JLabel.CENTER);
        centerPanel.add(instructionLabel, createGridBagConstraints(0, 1, 2, 1, GridBagConstraints.CENTER));

        startButton = new JButton("Iniciar");
          startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Botón 'Iniciar' clicado"); // Agrega este mensaje de depuración
                String playerName = playerNameField.getText();
               
                
            }
        });
          
        centerPanel.add(startButton, createGridBagConstraints(0, 2, 2, 1, GridBagConstraints.CENTER));

        paneIntr.add(centerPanel, BorderLayout.CENTER);
    }

    private GridBagConstraints createGridBagConstraints(int gridx, int gridy, int gridwidth, int gridheight, int anchor) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.anchor = anchor;
        gbc.insets = new Insets(5, 5, 5, 5); // Márgenes
        return gbc;
    }

    public void setController(Controller aThis) {
        this.controller = controller;
    }
}
