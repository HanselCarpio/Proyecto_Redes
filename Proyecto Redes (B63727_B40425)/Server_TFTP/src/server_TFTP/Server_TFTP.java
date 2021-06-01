package server_TFTP;

import ServerLogic.MultiServer;
import java.io.IOException;

public class Server_TFTP {

    private static MultiServer server;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        server = new MultiServer();
        server.start(5555);

    }

    public static MultiServer getServer() {
        return server;
    }

    public static void setServer(MultiServer server) {
        Server_TFTP.server = server;
    }

}
