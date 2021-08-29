import Client.ClientMain;

import java.io.IOException;

public class App {

    public static void main(String[] args) {
        // write your code here
        ClientMain client = new ClientMain();
        try {
            client.start("localhost",8080);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
