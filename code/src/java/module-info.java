module futuremctests {
    requires javafx.controls;
    requires javafx.fxml;

    opens futuremctests to javafx.fxml;
    exports futuremctests;
        }