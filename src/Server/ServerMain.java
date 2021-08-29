package Server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ServerMain extends Application {

    private ServerSocket serverSocket;
    private final ArrayList<ClientHandler> clientsList;

    public ServerMain() {
        clientsList = new ArrayList<ClientHandler>();
    }

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server listening \uD83D\uDE80 on port: " + port + " ...");

        //noinspection InfiniteLoopStatement
        while (true) {
            Socket clientSocket = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(clientSocket, clientsList);
            clientsList.add(clientHandler);
            clientHandler.start(); // ⚠️start is the runnable method for threads!
            System.out.println("New client connected! ...");
        }
    }

    public void stop() throws IOException {
        serverSocket.close();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Chat.fxml"));
        primaryStage.setTitle("ServerChat");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
