package server_TFTP;

import ServerLogic.EchoMultiServer;
import java.io.IOException;

public class Server_TFTP {

    private static EchoMultiServer server;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        server = new EchoMultiServer();
        server.start(5555);

    }

    public static EchoMultiServer getServer() {
        return server;
    }

    public static void setServer(EchoMultiServer server) {
        Server_TFTP.server = server;
    }

}
