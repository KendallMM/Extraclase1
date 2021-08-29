package Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ClientRunnable implements Runnable {

    private final BufferedReader input;

    public ClientRunnable(InputStream socketISR) throws IOException {
        this.input = new BufferedReader(new InputStreamReader(socketISR));
    }

    @Override
    public void run() {
        try{
            while(true) {
                String msg = input.readLine();
                if(msg.equalsIgnoreCase("EXIT")) {
                    input.close();
                    break;
                }
                System.out.println(msg);
                System.out.print(">> ");

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}