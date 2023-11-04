
package view;
import controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import controller.Controller;
public class Other extends JFrame {
    // Existing variables...
     private JLabel titleLabel;
    private JTextArea instructionTextArea;
    private JButton startButton;
    private JTextField playerNameField;
    private Controller controller;

    public Other(Controller controller) {
        super("Adosados - Instrucciones del juego");
        setSize(750, 600); // Ajuste del tamaño para acomodar el texto más largo
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Container paneIntr = getContentPane();
        paneIntr.setLayout(new BorderLayout());

        // Crear un panel para centrar el contenido
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Márgenes

        titleLabel = new JLabel("INSTRUCCIONES");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        centerPanel.add(titleLabel, createGridBagConstraints(0, 0, 2, 1, GridBagConstraints.CENTER));

        // Configuración del JTextArea para las instrucciones
        instructionTextArea = new JTextArea(5, 20); // Establece un tamaño inicial
        instructionTextArea.setText(
            "Este juego pone en acción la habilidad para comparar patrones visuales, entrenando además la " +
            "atención a los detalles y la velocidad perceptiva. Estas capacidades son relevantes cuando hay que " +
            "decidir entre estímulos semejantes y hay que hacerlo de forma rápida, por ejemplo al reconocer fotografías, " +
            "caras, objetos cotidianos o palabras escritas."
        );
        instructionTextArea.setWrapStyleWord(true);
        instructionTextArea.setLineWrap(true);
        instructionTextArea.setEditable(false);
        instructionTextArea.setFocusable(false);
        instructionTextArea.setBackground(getBackground());
        instructionTextArea.setFont(new Font("Arial", Font.PLAIN, 14));

        // Añadir el JTextArea a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(instructionTextArea);
        scrollPane.setPreferredSize(new Dimension(700, 110)); // Ajustar el tamaño preferido del scrollPane
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY), // Borde exterior
            BorderFactory.createEmptyBorder(10, 10, 10, 10) // Padding interior
        ));

        centerPanel.add(scrollPane, createGridBagConstraints(0, 1, 2, 1, GridBagConstraints.CENTER));

        // Botón para volver al menú principal
        JButton backButton = new JButton("Menú Principal");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para volver al menú principal
                // Deberás implementar esto de acuerdo a tu aplicación
                Other.this.setVisible(false);
                // Ejemplo: mainMenu.setVisible(true); // Suponiendo que tienes la instancia de mainMenu
            }
        });
        centerPanel.add(backButton, createGridBagConstraints(0, 2, 2, 1, GridBagConstraints.CENTER));

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

    // Rest of the class code...
    // No olvides implementar el método main si es necesario, o la lógica para mostrar esta ventana desde otra parte de tu aplicación.
}