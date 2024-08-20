package io.github.gleidsonmt.suspense;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        SuspenseTechCircle suspenseTechCircle = new SuspenseTechCircle("TechCircle", "Circle");
        SuspenseCircle suspenseCircle = new SuspenseCircle("Circle", "Circle");
        Suspense3DCircle suspense3DCircle = new Suspense3DCircle("3DCircle", "Circle");

        SuspenseRect suspenseRect = new SuspenseRect();

        FlowPane flowPane = new FlowPane(suspenseCircle, suspenseTechCircle, suspense3DCircle, suspenseRect);
//        FlowPane flowPane = new FlowPane( suspenseRect);
        flowPane.setHgap(20);
        flowPane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(flowPane, 1200, 800);
        scene.getStylesheets().add(HelloApplication.class.getResource("master.css").toExternalForm());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}