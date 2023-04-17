package de.todoapp.controller;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * This is an abstract base controller class that provides common functionality
 * for all controllers in the application.
 *
 * @author Tobias Metzger
 * @version 1.0
 */
public abstract class BaseController {
    /**
     * The primary stage of the application.
     */
    protected Stage primaryStage;

    /**
     * Initializes the controller. This method is called after the FXML file has been loaded.
     * The method can be overwritten by subclasses to provide custom initialization code.
     */
    public void initialize() {
        // Standard initialisations for all controllers
    }

    /**
     * Sets the primary stage to the given stage and attaches a handler for closing the application.
     *
     * @param primaryStage the primary stage of the application
     */
    private void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Handler for closing the application
        this.primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
    }

    /**
     * Loads the specified FXML file and switches to the corresponding scene.
     *
     * @param stage        the primary stage of the application
     * @param fxmlFileName the name of the FXML file to load
     * @param title        the title of the new scene
     * @throws IOException if an I/O error occurs while loading the FXML file
     */
    public void switchToScene(Stage stage, String fxmlFileName, String title) throws IOException {
        setPrimaryStage(stage);

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFileName)));
        Scene scene = new Scene(root);

        if (title != null) {
            primaryStage.setTitle(title);
        }

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
