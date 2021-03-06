package GUI;

import Client_TFTP.Client_TFTP;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

//MainClientWindow Class 
public class MainClientWindow extends JFrame implements ActionListener {

    //Instances
    //Label 
    private JLabel label_pic;
    //Image Icon
    private ImageIcon image;
    //Buttons
    private JButton jbtn_Send;
    private JButton jbtn_Receive;
    //Table
    public static JTable jTable_FilesTable;
    private TablePanel jpanel_Table;
    //Array
    private String[] directories;
    //Boolean Instances
    public boolean flag;

    public MainClientWindow() {
        this.setTitle("Cliente TFTP - Envío y Recepción de Imágenes");
        this.setResizable(false);
        this.setSize(600, 310);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public void init() {
        this.setLayout(null);

        //Instances
        //Label
        label_pic = new JLabel("");
        //Image Icon
        image = new ImageIcon("C:\\Users\\bryan\\Documents\\NetBeansProjects\\Proyecto Redes (B63727_B40425)\\Client_TFTP\\src\\img\\Send Wallpaper.png");
        //Buffered Image
        //Buttons
        this.jbtn_Send = new JButton("Enviar");
        this.jbtn_Receive = new JButton("Recibir");
        //Table
        this.jpanel_Table = new TablePanel();
        //Placing
        this.jbtn_Send.setBounds(380, 220, 110, 30);
        this.jbtn_Receive.setBounds(80, 220, 110, 30);
        this.label_pic.setBounds(330, 9, 200, 200);
        //Adding to window
        this.add(this.jbtn_Receive);
        this.add(this.jbtn_Send);
        this.add(this.label_pic);
        this.add(jpanel_Table);

        //Attributes
        this.jbtn_Receive.addActionListener(this);
        this.jbtn_Send.addActionListener(this);
        setVisible(true);
        label_pic.setIcon(image);
        label_pic.setVisible(true);
    }

    //Getters & Setters
    public JButton getJbtn_Send() {
        return jbtn_Send;
    }

    public void setJbtn_Send(JButton jbtn_Send) {
        this.jbtn_Send = jbtn_Send;
    }

    public JButton getJbtn_Receive() {
        return jbtn_Receive;
    }

    public void setJbtn_Receive(JButton jbtn_Receive) {
        this.jbtn_Receive = jbtn_Receive;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == this.jbtn_Send) { //Button action Send
            try {
                Client_TFTP.getClient().getOut().println("recibir");

                if (sendMessage()) {

                }

            } catch (IOException ex) {
                Logger.getLogger(MainClientWindow.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (arg0.getSource() == this.jbtn_Receive) { //Button Action Download
            if (Client_TFTP.getWindowClientTFTP().jTable_FilesTable.getSelectedRow() != -1) {
                Client_TFTP.getClient().getOut().println("descargar");
            } else {
                System.err.println("Error - No se ha seleccionado un archivo.");

            }
        }
    }

    //sendMessage Method
    public boolean sendMessage() throws IOException {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this directory: "
                    + chooser.getSelectedFile().getAbsolutePath());

            //Once we send the image we can visualize it in the window
            BufferedImage img = ImageIO.read(new File(chooser.getSelectedFile().getAbsolutePath()));
            Image dimg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            image = new ImageIcon(dimg);
            label_pic.setIcon(image);
            label_pic.setVisible(true);
        }

        //First we need to cut the image in pieces
        cuttingImage(chooser.getSelectedFile().getAbsolutePath());

        if (sendFile(chooser.getSelectedFile().getAbsolutePath())) {
            return true;
        } else {
            return false;
        }
    }//End sendMessage Method

    //SendFile Method
    public boolean sendFile(String fileName) throws FileNotFoundException, IOException {

        try {

            System.out.println("Nombre del Archivo: " + fileName);
            File file = new File(fileName);

            // Capturing the file length
            int fileLength = (int) file.length();

            System.out.println("Tam cliente: " + fileLength);

            System.out.println("Enviando Archivo: " + file.getName());
            // Sending the file name
            Client_TFTP.getClient().getDos().writeUTF(file.getName());

            // Sending file length
            Client_TFTP.getClient().getDos().writeInt(fileLength);

            FileInputStream fis = new FileInputStream(fileName);
            BufferedInputStream bis = new BufferedInputStream(fis);

            // Array Byte for file length
            byte[] buffer = new byte[fileLength];

            bis.read(buffer);

            // We send the array full of bytes
            for (int i = 0; i < buffer.length; i++) {
                Client_TFTP.getClient().getBos().write(buffer[i]);
            }

            System.out.println("Archivo Enviado: " + file.getName());
            bis.close();
            Client_TFTP.getClient().getBos().flush();
            Client_TFTP.getClient().getDos().flush();

        } catch (Exception e) {
            System.err.println("Error - Archivo no enviado." + e);
            return false;
        }

        return true;

    }//endSendFile

    //Getters & Setters
    public String[] getFiles() {
        return directories;
    }

    public void setDirectories(String[] directories) {
        this.directories = directories;

    }

    //Function in charge of cutting images in small pieces
    public void cuttingImage(String fileURL) throws IOException {

        // read in the big picture
        File file = new File(fileURL);
        FileInputStream fis = new FileInputStream(file);
        BufferedImage image = ImageIO.read(fis);

        //Split into 4 * 4 (16) small map
        int rows = 4;
        int cols = 4;
        int chunks = rows * cols;

        // Calculate the width and height of each thumbnail
        int chunkWidth = image.getWidth() / cols;
        int chunkHeight = image.getHeight() / rows;

        int count = 0;
        BufferedImage imgs[] = new BufferedImage[chunks];
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {

                //Set the size and type of the thumbnail 
                imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());

                //Write image content
                Graphics2D gr = imgs[count++].createGraphics();
                gr.drawImage(image, 0, 0,
                        chunkWidth, chunkHeight,
                        chunkWidth * y, chunkHeight * x,
                        chunkWidth * y + chunkWidth,
                        chunkHeight * x + chunkHeight, null);
                gr.dispose();
            }
        }

        String[] split = fileURL.split("\\.");

        // output thumbnail
        for (int i = 0; i < imgs.length; i++) {
            File file1 = new File("src/img/" + i + "." + split[1]);
            ImageIO.write(imgs[i], "jpg", file1);
        }

        //Cutting image Confirmation Message
        System.out.print("La imagen encontrada en la ruta: " + fileURL + " ha sido cortada con éxito.");
    }//End cuttingImage

    //downloadFile
    public boolean downloadFile() {

        while (true) {

            try {
                DataInputStream dis = new DataInputStream(Client_TFTP.getClient().getServerSocket().getInputStream());

                // Getting the file name
                String nombreArchivo = dis.readUTF().toString();

                // Getting file length
                int tam = (int) dis.readInt();

                System.out.println("Tam: " + tam);
                System.out.println("Recibiendo archivo: " + nombreArchivo);

                // Letting know where to save the file
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
                int returnVal = chooser.showOpenDialog(this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("You chose to open this directory: "
                            + chooser.getSelectedFile().getAbsolutePath());
                }

                FileOutputStream fos = new FileOutputStream(chooser.getSelectedFile().getAbsolutePath() + "\\" + nombreArchivo);
                BufferedOutputStream out = new BufferedOutputStream(fos);
                BufferedInputStream in = new BufferedInputStream(Client_TFTP.getClient().getServerSocket().getInputStream());

                // Creating byte array
                byte[] buffer = new byte[tam];

                // We get the file reading the array full of bytes
                for (int i = 0; i < buffer.length; i++) {
                    buffer[i] = (byte) in.read();
                }

                // Writting the file
                out.write(buffer);
                out.flush();
                fos.close();

                System.out.println("Archivo Recibido: " + nombreArchivo);
                return true;
            } catch (Exception e) {
                System.err.println("Error - " + e.toString());
                return false;

            }
        }
    }//End downloadFile

    //Table Panel
    class TablePanel extends JPanel {

        JScrollPane scroll;
        JTable jTable_table;
        int init = 1;
        String[] direcRep;

        public TablePanel() {
            this.setLayout(null);
            this.setBounds(10, 10, 300, 200);

            initJtable();
            this.setVisible(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (getFiles() != null && getFiles() != direcRep) {
                setUpTableData(directories);
                direcRep = getFiles();
            }
            repaint();
        }

        public void initJtable() {

            String[] colName = {"Nombre archivo"};
            if (jTable_table == null) {
                jTable_table = new JTable() {
                    @Override
                    public boolean isCellEditable(int nRow, int nCol) {
                        return false;
                    }
                };
            }

            DefaultTableModel contactTableModel = (DefaultTableModel) jTable_table
                    .getModel();
            contactTableModel.setColumnIdentifiers(colName);

            jTable_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jTable_table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

            jTable_table.setBounds(0, 0, 259, 200);

            scroll = new JScrollPane(jTable_table);
            scroll.setBounds(0, 0, 259, 200);
            scroll.setVisible(true);
            this.add(scroll);

        }

        public void setUpTableData(String[] directorio) {
            DefaultTableModel tableModel = (DefaultTableModel) jTable_table.getModel();
            delete();

            for (int i = 0; i < directorio.length; i++) {
                String[] data = new String[1];

                data[0] = directorio[i];

                tableModel.addRow(data);

            }
            tableModel.fireTableDataChanged();
            jTable_table.setModel(tableModel);
            jTable_FilesTable = jTable_table;
            /**/

        }

        public void delete() {
            DefaultTableModel tb = (DefaultTableModel) jTable_table.getModel();
            int a = jTable_table.getRowCount() - 1;
            for (int i = a; i >= 0; i--) {
                tb.removeRow(tb.getRowCount() - 1);
            }
        }

        public JTable getjTable_table() {
            return jTable_table;
        }

        public void setjTable_table(JTable jTable_table) {
            this.jTable_table = jTable_table;
        }

    }

}
