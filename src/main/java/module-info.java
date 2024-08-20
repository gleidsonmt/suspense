module io.github.gleidsonmt.suspense {
    requires javafx.controls;
    requires javafx.fxml;

//    requires io.github.gleidsonmt.core;

    opens io.github.gleidsonmt.suspense to javafx.fxml;
    exports io.github.gleidsonmt.suspense;
}