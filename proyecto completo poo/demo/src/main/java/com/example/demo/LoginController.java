package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private TextField cedulaTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorMessageLabel;

    @FXML
    private void iniciarSesion() {
        String cedula = cedulaTextField.getText();
        String password = passwordField.getText();

        // Validar la autenticación utilizando la base de datos
        boolean autenticado = DatabaseConnector.autenticarUsuario(cedula, password);

        if (autenticado) {
            // Inicio de sesión exitoso, redirigir al usuario a la interfaz principal
            Stage stage = (Stage) cedulaTextField.getScene().getWindow();
            stage.close(); // Cerrar el formulario de inicio de sesión
            // Aquí puedes abrir la interfaz principal de la aplicación
        } else {
            errorMessageLabel.setText("Cédula o contraseña incorrecta");
        }
    }
}

