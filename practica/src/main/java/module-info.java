module co.edu.poli.practica {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.poli.controlador to javafx.fxml;
    exports co.edu.poli.vista;
    
    opens co.edu.poli.modelo to javafx.base;
}
