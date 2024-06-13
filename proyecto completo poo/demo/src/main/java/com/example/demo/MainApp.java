package com.example.demo;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Cargar el archivo FXML del formulario de inicio de sesión
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Parent loginRoot = loginLoader.load();

        // Configurar el controlador del formulario de inicio de sesión
        LoginController loginController = loginLoader.getController();

        // Crear la escena de inicio de sesión
        Scene loginScene = new Scene(loginRoot, 400, 300);

        // Crear y configurar la ventana de inicio de sesión
        Stage loginStage = new Stage();
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.initStyle(StageStyle.UTILITY);
        loginStage.setTitle("Inicio de Sesión");
        loginStage.setScene(loginScene);

        // Mostrar la ventana de inicio de sesión antes de la ventana principal
        loginStage.showAndWait();

        // Una vez que se cierre la ventana de inicio de sesión, puedes continuar con la ventana principal
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        MainController mainController = mainLoader.getController();
        Scene mainScene = new Scene(mainLoader.load(), 720, 740);

        primaryStage.setTitle("Gestión de Usuarios y Roles");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

}