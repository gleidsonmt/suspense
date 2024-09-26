package io.github.gleidsonmt.suspense;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  17/09/2024
 */
public class TechCircle extends CircleLoader implements SuspenseLoader {

    public TechCircle() {
        super();
        this.getStyleClass().set(0, "tech-circle");
    }

    public TechCircle(String _title, String _legend) {
        super(_title, _legend);
        this.getStyleClass().set(0, "tech-circle");
    }

    @Override
    protected StackPane createCircleContainer() {
        StackPane circleContainer = new StackPane();
        ObservableList<Circle> circles = FXCollections.observableArrayList();

        for (int i = 0; i < 3; i++) {
            Circle circle = new Circle();
            circle.getStyleClass().add("track-circle");
            circle.setStrokeWidth(4);
            circle.setFill(Color.TRANSPARENT);
            circles.add(circle);
        }

        // updating the radius
        circles.get(0).setRadius(120);
        circles.get(1).setRadius(105);
        circles.get(2).setRadius(85);

        // start with diferent angles per circle and speed
        rotate(circles.get(0), 180, 18 );
        rotate(circles.get(1), 360, 10);
        rotate(circles.get(2), 60, 22);

        circles.get(0).setStyle("-fx-stroke-dash-offset : 5; -fx-stroke-dash-array : 200;");
        circles.get(1).setStyle("-fx-stroke-dash-array : 150;");
        circles.get(2).setStyle("-fx-stroke-dash-array : 20;");

        circleContainer.getChildren().setAll(circles);
        return circleContainer;
    }
}
