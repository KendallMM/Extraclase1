package Client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain extends Application {
    @FXML public TextField tfNombre;
    @FXML public TextArea msgArea;
    @FXML public TextField msgText;
    @FXML public Button msgSend;


    private Socket socket;
    private PrintWriter output;
    private Scanner scanner;

    public void init(String ipAddress, int port) throws IOException {
        socket = new Socket(ipAddress, port);
        output = new PrintWriter(socket.getOutputStream(), true);
    }
    public void kill() throws IOException{
        output.close();
        socket.close();
    }

    private String getUsername() {
        return tfNombre.getText();
    }

    public void start(String ipAddress, int port) throws IOException {
        init(ipAddress, port);
        ClientRunnable clientRun = new ClientRunnable(socket.getInputStream());
        new Thread(clientRun).start();

        String usrInput;
        String name = getUsername();
        String msg = String.format("[%s] : ", name);

        while (true) {
            usrInput = msgText.getText();
            output.println(msg + usrInput);

            if (usrInput.equalsIgnoreCase("EXIT")) {
                output.println(String.format("[%s] left chat ...", name));
                output.println("EXIT");
                break;
            }
        }

        kill();
    }
    public void send(ActionEvent actionEvent) {
        String nombre = tfNombre.getText();
        String msg = msgText.getText();
        msgArea.appendText("\n" + nombre + ": " + msg);
    }
        @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Chat_client.fxml"));
        primaryStage.setTitle("ClientChat");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        ClientMain client = new ClientMain();
        try {
            client.start("localhost",8080);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
