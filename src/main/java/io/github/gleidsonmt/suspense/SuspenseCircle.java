package io.github.gleidsonmt.suspense;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  13/09/2024
 */
public class SuspenseCircle extends CircleLoader implements SuspenseLoader {

    public SuspenseCircle() {
        super();
        this.getStyleClass().set(0, "suspense-circle");
    }

    public SuspenseCircle(String _title, String _legend) {
        super(_title, _legend);
        this.getStyleClass().set(0, "suspense-circle");
    }

    @Override
    protected StackPane createCircleContainer() {
        StackPane circleContainer = new StackPane();

        Circle foreground = new Circle();
        foreground.getStyleClass().add("foreground-circle");
        foreground.setStrokeWidth(4);
        foreground.setRadius(120);
        foreground.setFill(Color.TRANSPARENT);
        foreground.setStroke(Color.GRAY);

        Circle trackCircle = new Circle();
        trackCircle.getStyleClass().add("track-circle");
        trackCircle.setStrokeWidth(4);
        trackCircle.setRadius(120);
        trackCircle.setFill(Color.TRANSPARENT);
        trackCircle.setStroke(Color.BLUE);

        trackCircle.setStyle(" -fx-stroke-dash-array : 600; -fx-stroke-dash-offset: 600");
        circleContainer.getChildren().setAll(foreground, trackCircle);

        rotate(trackCircle, 360, 2, false);

        return circleContainer;
    }


}
