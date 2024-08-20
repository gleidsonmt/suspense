package io.github.gleidsonmt.suspense;

import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.List;
import java.util.Stack;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  19/08/2024
 */
public class SuspenseRect extends HBox implements Loader{

    private final StackPane container = new StackPane();
    private final StackPane rectContainer = new StackPane();
    private final Label title = new Label("Loading...");
    private final Text legend = new Text("Loading...");

    private final List<Rectangle> rectangles;

    public SuspenseRect() {
        rectangles = List.of(new Rectangle(), new Rectangle(), new Rectangle(), new Rectangle());
        rectangles.forEach(this::config);
        container.getChildren().setAll(rectangles);
        this.setFillHeight(false);

        title.setStyle("-fx-font-size: 22pt;");

        container.getChildren().setAll(rectangles);
        rectContainer.getChildren().setAll(container);

        VBox infoContainer = new VBox(title, legend);
        infoContainer.setPrefWidth(150);

        container.setAlignment(Pos.CENTER);
        this.getChildren().addAll(rectContainer, infoContainer);

        rectContainer.setPadding(new Insets(10));
        rectContainer.setMinSize(160, 160);

//        this.setSpacing(30);

        this.setAlignment(Pos.CENTER);

        StackPane.setAlignment(this, Pos.CENTER);

        StackPane.setAlignment(rectangles.get(0), Pos.TOP_LEFT);
        StackPane.setAlignment(rectangles.get(1), Pos.TOP_RIGHT);
        StackPane.setAlignment(rectangles.get(2), Pos.BOTTOM_LEFT);
        StackPane.setAlignment(rectangles.get(3), Pos.BOTTOM_RIGHT);

//        StackPane.setAlignment(container, Pos.CENTER_LEFT);
//        StackPane.setAlignment(title, Pos.CENTER_RIGHT);

//        root.setMaxHeight(60);
//        root.setMaxWidth(60);
//
//        root.setPrefSize(90, 90);
        this.setPrefSize(210, 90);
        starterAnimation();
    }

    private void config(Rectangle rectangle) {
        rectangle.setStyle("-fx-fill: -fx-color;");
//        rectangle.setPrefSize(50, 50);
        rectangle.setWidth(30);
        rectangle.setHeight(30);
    }

    private void starterAnimation() {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(
                        container.maxHeightProperty(), 30
                )),
                new KeyFrame(Duration.millis(1000), new KeyValue(
                        container.maxHeightProperty(), 70
                )),
                new KeyFrame(Duration.ZERO, new KeyValue(
                        container.maxWidthProperty(), 30
                )),
                new KeyFrame(Duration.millis(1000), new KeyValue(
                        container.maxWidthProperty(), 70
                ))

        );
        timeline.setOnFinished(e -> {
            System.out.println("wow");
        });
        timeline.setCycleCount(-1);
        timeline.setAutoReverse(true);
        timeline.play();

        RotateTransition rotate = new RotateTransition(Duration.seconds(2), container);

        rotate.setAutoReverse(true);

        rotate.setByAngle(90);
        rotate.setInterpolator(Interpolator.EASE_OUT);
        rotate.setCycleCount(Timeline.INDEFINITE);
        rotate.play();
    }

    @Override
    public void updateTitle(String title) {
        this.title.setText(title);
    }

    @Override
    public void updateLegend(String legend) {
        this.legend.setText(legend);
    }

}
