package com.example.demo;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> roles = DatabaseConnector.buscarRol();
        for (String role : roles) {
            System.out.println(role);
            myComboBox.getItems().add(role);
        }
}

    @FXML
    private TextField idUsuarioTextField, nombreTextField, estadoUsuarioTextField, telefonoTextField, direccionTextField, emailTextField, cedulaTextField, passwordTextField;
    @FXML
    private ComboBox<String> myComboBox;
    @FXML
    private void guardarUsuario() {
        String idUsuario = idUsuarioTextField.getText();
        String nombre = nombreTextField.getText();
        String estado = estadoUsuarioTextField.getText();
        String telefono = telefonoTextField.getText();
        String direccion = direccionTextField.getText();
        String email = emailTextField.getText();
        String cedula = cedulaTextField.getText();
        String password = passwordTextField.getText();

        if(
                idUsuario.isEmpty()
                || nombre.isEmpty()
                || estado.isEmpty()
                || telefono.isEmpty()
                || direccion.isEmpty()
                || email.isEmpty()
                || cedula.isEmpty()
                || password.isEmpty()
        )
            return;

        System.out.println(idUsuario);
        System.out.println(nombre);
        System.out.println(estado);
        System.out.println(telefono);
        System.out.println(direccion);
        System.out.println(email);
        System.out.println(cedula);
        System.out.println(password);

        // Llamar al método para insertar el usuario en la base de datos
        String respuesta = DatabaseConnector.insertUsuario(idUsuario, nombre, estado, telefono, direccion, email, cedula, password);

        // Puedes agregar lógica adicional aquí, como mostrar un mensaje de éxito o actualizar la interfaz de usuario.
        Label responseLabel = new Label(respuesta);
        // Crear una nueva ventana emergente para mostrar la respuesta
        Stage responsePopupStage = new Stage();
        responsePopupStage.initModality(Modality.APPLICATION_MODAL);
        responsePopupStage.initStyle(StageStyle.UTILITY);
        responsePopupStage.setTitle("Respuesta de la inserción de usuario");
        // Crear el contenido de la ventana emergente con la respuesta
        Scene responsePopupScene = new Scene(responseLabel, 800, 600);

        // Establecer el contenido en la ventana emergente
        responsePopupStage.setScene(responsePopupScene);

        // Mostrar la ventana emergente con la respuesta
        responsePopupStage.showAndWait();
    }

    @FXML
    private void editarUsuario() {
        String idUsuario = idUsuarioTextField.getText();
        String nombre = nombreTextField.getText();
        String estado = estadoUsuarioTextField.getText();
        String telefono = telefonoTextField.getText();
        String direccion = direccionTextField.getText();
        String email = emailTextField.getText();
        String cedula = cedulaTextField.getText();
        String password = passwordTextField.getText();

        if(
                idUsuario.isEmpty()
                        || nombre.isEmpty()
                        || estado.isEmpty()
                        || telefono.isEmpty()
                        || direccion.isEmpty()
                        || email.isEmpty()
                        || cedula.isEmpty()
                        || password.isEmpty()
        )
            return;

        System.out.println(idUsuario);
        System.out.println(nombre);
        System.out.println(estado);
        System.out.println(telefono);
        System.out.println(direccion);
        System.out.println(email);
        System.out.println(cedula);
        System.out.println(password);

        // Llamar al método para insertar el usuario en la base de datos
        String respuesta = DatabaseConnector.editUsuario(idUsuario, nombre, estado, telefono, direccion, email, cedula, password);

        // Puedes agregar lógica adicional aquí, como mostrar un mensaje de éxito o actualizar la interfaz de usuario.
        Label responseLabel = new Label(respuesta);
        // Crear una nueva ventana emergente para mostrar la respuesta
        Stage responsePopupStage = new Stage();
        responsePopupStage.initModality(Modality.APPLICATION_MODAL);
        responsePopupStage.initStyle(StageStyle.UTILITY);
        responsePopupStage.setTitle("Respuesta de la inserción de usuario");
        // Crear el contenido de la ventana emergente con la respuesta
        Scene responsePopupScene = new Scene(responseLabel, 800, 600);

        // Establecer el contenido en la ventana emergente
        responsePopupStage.setScene(responsePopupScene);

        // Mostrar la ventana emergente con la respuesta
        responsePopupStage.showAndWait();
    }

    @FXML
    private void limpiarUsuario() {
        idUsuarioTextField.clear();
        nombreTextField.clear();
        estadoUsuarioTextField.clear();
        telefonoTextField.clear();
        direccionTextField.clear();
        emailTextField.clear();
        cedulaTextField.clear();
        passwordTextField.clear();
    }

    @FXML
    private void buscarUsuario() {
        // Llamar al método para buscar el usuario en la base de datos
        String idUsuario = idUsuarioTextField.getText();

        String[] usuario = DatabaseConnector.buscarUsuario(idUsuario);
        if (usuario != null) {
            idUsuarioTextField.setText(idUsuario);
            nombreTextField.setText(usuario[0]);

            // Crear un objeto UsuarioModel con los datos del usuario
            UsuarioModel usuarioModel = new UsuarioModel(idUsuario, usuario[0]);

            // Agregar el usuario a la lista
            usuarios.clear(); // Limpia la lista
            usuarios.add(usuarioModel);
        }
    }

    @FXML
    private void eliminarUsuario() {
        // Llamar al método para insertar el usuario en la base de datos
        String idUsuario = idUsuarioTextField.getText();

        if(!idUsuario.isEmpty())
        DatabaseConnector.eliminarUsuario(Integer.parseInt(idUsuario));
    }

}
