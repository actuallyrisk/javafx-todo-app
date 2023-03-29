module gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;

    opens de.todoapp to javafx.fxml;
    exports de.todoapp;
}