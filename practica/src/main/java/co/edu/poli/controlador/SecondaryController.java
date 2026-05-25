package co.edu.poli.controlador;

import java.io.IOException;

import co.edu.poli.vista.App;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}