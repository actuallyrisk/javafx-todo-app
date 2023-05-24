module gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires java.sql;
    requires com.jthemedetector;

    opens de.todoapp.controller to javafx.fxml;
    exports de.todoapp.core;
    exports de.todoapp.controller;
    exports de.todoapp.config;
}