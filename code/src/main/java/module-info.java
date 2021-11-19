module com.futuremctests {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.futuremctests to javafx.fxml;
    exports com.futuremctests;
        }