package GUI;

import Client_TFTP.Client_TFTP;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MainWindow extends JFrame implements ActionListener {

    //Instances
    //Label
    private JLabel jlabel_IP;
    private JLabel jLabel_Welcome;
    private JLabel jLabel_Message;
    //TextField
    private JTextField jtextField_IP;
    //Button
    private JButton jbtn_ConnectIP;

    JDesktopPane jdesktop_pane_principal;

    public MainWindow() {
        this.setTitle("Proyecto de Redes y Comunicación de Datos (Cliente TFTP)");
        this.setResizable(false);
        this.setSize(490, 400);
    }

    public void init() {
        this.setLayout(null);

        //Instances
        //Label 
        this.jLabel_Welcome = new JLabel("CLIENTE / SERVIDOR TFTP");
        this.jLabel_Message = new JLabel("Envío y Recepción de Imágenes");
        this.jlabel_IP = new JLabel("Para continuar ingrese la dirección IP del servidor: ");

        //Text Field
        this.jtextField_IP = new JTextField();

        //Buttons
        this.jbtn_ConnectIP = new JButton("Conectar");

        //Desktop Pane
        jdesktop_pane_principal = new JDesktopPane();

        //Placing
        this.jLabel_Welcome.setBounds(162, 40, 200, 50);
        this.jLabel_Message.setBounds(142, 70, 200, 50);
        this.jlabel_IP.setBounds(95, 105, 300, 50);
        this.jtextField_IP.setBounds(168, 170, 120, 20);
        this.jbtn_ConnectIP.setBounds(182, 220, 90, 30);

        //Adding to window
        this.jbtn_ConnectIP.addActionListener(this);
        this.add(this.jlabel_IP);
        this.add(this.jtextField_IP);
        this.add(this.jbtn_ConnectIP);
        this.add(this.jLabel_Welcome);
        this.add(this.jLabel_Message);
        this.add(this.jLabel_Welcome);

        //Atributes
        this.jtextField_IP.setText("192.168.");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.jdesktop_pane_principal.setVisible(false);
        this.jdesktop_pane_principal.setSize(490, 400);
        this.add(this.jdesktop_pane_principal);
        this.jdesktop_pane_principal.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        if (arg0.getSource() == this.jbtn_ConnectIP) {

            if (!jtextField_IP.getText().equalsIgnoreCase("") && (Client_TFTP.getClient().startConnection(jtextField_IP.getText(), 5555))) {
                Client_TFTP.getClient().getOut().println("Hello");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error - Dirección IP Inválida");
            }
        }
    }
}
