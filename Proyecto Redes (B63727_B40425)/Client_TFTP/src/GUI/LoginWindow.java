package GUI;

import Client_TFTP.Client_TFTP;
import Objects.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginWindow extends JFrame implements ActionListener {

    //Instances
    //Label
    private JLabel jLabel_Menu;
    private JLabel jlabel_LogName;
    private JLabel jlabel_LogPassword;
    private JLabel jlabel_RegisterUser;
    private JLabel jlabel_RegisterPassword;
    //TextField
    private JTextField jTextField_LogName;
    private JPasswordField jtextField_LogPassword;
    private JTextField jTextField_RegisterUser;
    private JPasswordField jTextField_RegisterPassword;
    //Button
    private JButton jButton_LogIn;
    private JButton jButton_Register;

    public LoginWindow() {
        this.setTitle("Inicio de Sesión / Registro de Usuarios");
        this.setResizable(false);
        this.setSize(490, 400);
    }

    public void init() {
        this.setLayout(null);

        //Instances
        //Label
        this.jLabel_Menu = new JLabel("Inicio de Sesión Usuarios                                    Registro de Usuarios");
        this.jlabel_LogName = new JLabel("Usuario:");
        this.jlabel_LogPassword = new JLabel("Contraseña:");
        this.jlabel_RegisterPassword = new JLabel("Contraseña:");
        this.jlabel_RegisterUser = new JLabel("Usuario:");

        //TextField
        this.jTextField_LogName = new JTextField();
        this.jtextField_LogPassword = new JPasswordField();
        this.jTextField_RegisterUser = new JTextField();
        this.jTextField_RegisterPassword = new JPasswordField();

        //Buttons
        this.jButton_LogIn = new JButton("Iniciar Sesión");
        this.jButton_Register = new JButton("Registrarse");

        //Placing
        this.jLabel_Menu.setBounds(50, 30, 400, 50);
        this.jlabel_LogName.setBounds(50, 100, 100, 20);
        this.jlabel_LogPassword.setBounds(50, 160, 120, 20);
        this.jTextField_LogName.setBounds(50, 130, 120, 20);
        this.jtextField_LogPassword.setBounds(50, 190, 120, 20);
        this.jButton_LogIn.setBounds(50, 225, 120, 30);
        this.jlabel_RegisterUser.setBounds(303, 100, 100, 20);
        this.jlabel_RegisterPassword.setBounds(303, 160, 120, 20);
        this.jTextField_RegisterUser.setBounds(303, 130, 120, 20);
        this.jTextField_RegisterPassword.setBounds(303, 190, 120, 20);
        this.jButton_Register.setBounds(303, 225, 120, 30);

        //Adding to Window
        this.add(this.jLabel_Menu);
        this.add(this.jlabel_LogName);
        this.add(this.jlabel_LogPassword);
        this.add(this.jTextField_LogName);
        this.add(this.jtextField_LogPassword);
        this.add(this.jButton_LogIn);
        this.add(this.jlabel_RegisterUser);
        this.add(this.jlabel_RegisterPassword);
        this.add(this.jTextField_RegisterUser);
        this.add(this.jTextField_RegisterPassword);
        this.add(this.jButton_Register);
        this.jButton_Register.addActionListener(this);
        this.jButton_LogIn.addActionListener(this);

        //Atributes
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.jTextField_RegisterPassword.setEchoChar('*');
        this.jtextField_LogPassword.setEchoChar('*');
    }

    @Override
    public void actionPerformed(ActionEvent arg0) { //Button action Log In

        if (arg0.getSource() == this.jButton_LogIn) {
            Client_TFTP.getClient().getOut().println("iniciar sesion");
            Client_TFTP.getClient().getOut().println(jTextField_LogName.getText());
            Client_TFTP.getClient().getOut().println(String.valueOf(jtextField_LogPassword.getPassword()));
        }

        if (arg0.getSource() == this.jButton_Register) { //Button action register

            try {
                User usuario = new User();
                usuario.addUser(jTextField_RegisterUser.getText(), String.valueOf(this.jTextField_RegisterPassword.getPassword()));

                //Mensaje de confirmacion que se registro exitosamente
                JOptionPane.showMessageDialog(null, "Usted se ha registrado de manera exitosa!");
                //Se setean en blanco los textfields
                this.jTextField_RegisterUser.setText("");
                this.jTextField_RegisterPassword.setText("");
                this.jTextField_LogName.setText("");
                this.jtextField_LogPassword.setText("");
                

            } catch (Exception ae) {
            }

        }

    }

}
