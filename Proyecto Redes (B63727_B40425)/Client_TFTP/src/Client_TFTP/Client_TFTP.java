package Client_TFTP;

import GUI.MainWindow;
import GUI.LoginWindow;
import GUI.MainClientWindow;
import ClientLogic.EchoClient;

public class Client_TFTP {

    //Class Instances
    private static EchoClient client;
    private static MainClientWindow mainClientWindow;
    private static LoginWindow loginWindow;
    private static MainWindow mainWindow;

    //Main
    public static void main(String[] args) throws InterruptedException {
        //Instances are made
        client = new EchoClient(); 
        mainClientWindow = new MainClientWindow();
        loginWindow = new LoginWindow();
        //Init MainWindow
        mainWindow = new MainWindow();
        mainWindow.init();
    }

    public static EchoClient getClient() {
        return client;
    }

    public static MainClientWindow getWindowClientTFTP() {
        return mainClientWindow;
    }

    public static LoginWindow getLoginWindow() {
        return loginWindow;
    }

}
