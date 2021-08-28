package Chat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {
    @FXML
    private Button btnOK;

    public void handleBtnOK(ActionEvent actionEvent) {
        System.out.print("Hello Word JavaFX!");
    }
}
