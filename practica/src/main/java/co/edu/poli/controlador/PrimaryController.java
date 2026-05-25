package co.edu.poli.controlador;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import co.edu.poli.modelo.Camion;
import co.edu.poli.modelo.Encargado;
import co.edu.poli.modelo.Mediodetransporte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController {

    @FXML private TableView<Camion> tablaTransporte;
    @FXML private Button btnCrear, btnListarTodos, btnListarUno;
    @FXML private Button btnModificar, btnEliminar, btnGuardar, btnLeer, btnSalir;

    private List<Camion> lista = new ArrayList<>();
    private static final String ARCHIVO = "camiones.dat";

    @FXML
    public void initialize() {
        // Columnas de la tabla
        TableColumn<Camion, String> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Camion, String> colMarca = new TableColumn<>("Marca");
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));

        TableColumn<Camion, String> colModelo = new TableColumn<>("Modelo");
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));

        TableColumn<Camion, String> colCapacidad = new TableColumn<>("Capacidad");
        colCapacidad.setCellValueFactory(new PropertyValueFactory<>("capacidad"));

        TableColumn<Camion, String> colMercancia = new TableColumn<>("Mercancía");
        colMercancia.setCellValueFactory(new PropertyValueFactory<>("mercancia"));

        tablaTransporte.getColumns().clear();
        tablaTransporte.getColumns().addAll(colId, colMarca, colModelo, colCapacidad, colMercancia);

        // Eventos de botones
        btnCrear.setOnAction(e -> crearCamion());
        btnListarTodos.setOnAction(e -> listarTodos());
        btnListarUno.setOnAction(e -> listarUno());
        btnModificar.setOnAction(e -> modificar());
        btnEliminar.setOnAction(e -> eliminar());
        btnGuardar.setOnAction(e -> guardar());
        btnLeer.setOnAction(e -> leer());
        btnSalir.setOnAction(e -> salir());
    }

    private void crearCamion() {
        String id = pedir("ID del camión:");
        if (id == null) return;
        String marca = pedir("Marca:");
        if (marca == null) return;
        String modelo = pedir("Modelo:");
        if (modelo == null) return;
        String capacidad = pedir("Capacidad:");
        if (capacidad == null) return;
        String mercancia = pedir("Mercancía:");
        if (mercancia == null) return;

        Camion c = new Camion(id, marca, modelo, capacidad, null, mercancia);
        lista.add(c);
        alerta("Camión creado correctamente.");
    }

    private void listarTodos() {
        if (lista.isEmpty()) {
            tablaTransporte.getItems().clear(); // ← limpia la tabla si está vacía
            alerta("No hay camiones registrados.");
            return;
        }
        ObservableList<Camion> datos = FXCollections.observableArrayList(lista);
        tablaTransporte.setItems(datos);
    }

    private void listarUno() {
        String id = pedir("Ingresa el ID del camión a buscar:");
        if (id == null) return;

        Camion encontrado = lista.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst().orElse(null);

        if (encontrado == null) {
            alerta("No se encontró un camión con ese ID.");
            return;
        }
        ObservableList<Camion> datos = FXCollections.observableArrayList(encontrado);
        tablaTransporte.setItems(datos);
    }

    private void modificar() {
        String id = pedir("Ingresa el ID del camión a modificar:");
        if (id == null) return;

        Camion c = lista.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst().orElse(null);

        if (c == null) {
            alerta("No se encontró un camión con ese ID.");
            return;
        }

        String marca = pedir("Nueva marca (actual: " + c.getMarca() + "):");
        if (marca != null) c.setMarca(marca);
        String modelo = pedir("Nuevo modelo (actual: " + c.getModelo() + "):");
        if (modelo != null) c.setModelo(modelo);
        String capacidad = pedir("Nueva capacidad (actual: " + c.getCapacidad() + "):");
        if (capacidad != null) c.setCapacidad(capacidad);
        String mercancia = pedir("Nueva mercancía (actual: " + c.getMercancia() + "):");
        if (mercancia != null) c.setMercancia(mercancia);

        alerta("Camión modificado correctamente.");
        listarTodos();
    }

    private void eliminar() {
        String id = pedir("Ingresa el ID del camión a eliminar:");
        if (id == null) return;

        boolean eliminado = lista.removeIf(c -> c.getId().equals(id));
        if (eliminado) {
            alerta("Camión eliminado correctamente.");
            listarTodos();
        } else {
            alerta("No se encontró un camión con ese ID.");
        }
    }

    private void guardar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(lista);
            alerta("Datos guardados correctamente en " + ARCHIVO);
        } catch (IOException e) {
            alerta("Error al guardar: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void leer() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            lista = (List<Camion>) ois.readObject();
            alerta("Datos cargados correctamente. Camiones: " + lista.size());
            listarTodos();
        } catch (IOException | ClassNotFoundException e) {
            alerta("Error al leer: " + e.getMessage());
        }
    }

    private void salir() {
        System.exit(0);
    }

    // Métodos auxiliares
    private String pedir(String mensaje) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(mensaje);
        dialog.setTitle("Entrada de datos");
        Optional<String> resultado = dialog.showAndWait();
        return resultado.orElse(null);
    }

    private void alerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}