module com.example.demojava {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
   // opens hellofx to javafx.graphics;
    opens com.example.demojava to javafx.fxml;
    exports com.example.demojava;
}