package io.github.gleidsonmt.suspense;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  15/08/2024
 */
public class SuspenseTechCircle extends StackPane implements Loader {

    private final StackPane body = new StackPane();
    private final Label title = new Label();
    private final Label legend = new Label();

    private final Circle one = new Circle();
    private final Circle two = new Circle();
    private final Circle three = new Circle();

    public SuspenseTechCircle() {
        this(null, null);
    }

    public SuspenseTechCircle(String _title, String _legend) {
        this.getStyleClass().add("layout");
        one.setStyle("-fx-stroke-dash-offset : 5; -fx-stroke-dash-array : 200; -fx-stroke :  -fx-color;");
        two.setStyle("-fx-stroke-dash-array : 150; -fx-stroke : -fx-color;");
        three.setStyle("-fx-stroke-dash-array : 20; -fx-stroke : -fx-color;");

        config(one, two, three);

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        this.setAlignment(Pos.CENTER);

        this.getChildren().add(root);
        root.getChildren().setAll(body);
        body.getChildren().setAll(one, two, three);
        root.setSpacing(20);

        title.setStyle("-fx-font-size : 20; -fx-text-fill : -dark-gray;");
//
        title.textProperty().addListener(this::changed);

        if (_legend != null) legend.setText(_legend);
        if (_title != null) title.setText(_title);

        rotate(two, 180, 18);
        rotate(one, 360, 10);
        rotate(three, 60, 22);

        one.setRadius(120);
        two.setRadius(105);
        three.setRadius(85);

        root.getChildren().add(legend);

    }

    private void config(Circle... circles) {
        for (Circle circle : circles) {
            circle.setStrokeWidth(4);
            circle.setFill(Color.TRANSPARENT);
        }
    }

    private void rotate(Circle circle, int angle, int duration) {
        RotateTransition rotate = new RotateTransition(Duration.seconds(duration), circle);

        rotate.setAutoReverse(true);

        rotate.setByAngle(angle);
        rotate.setDelay(Duration.seconds(0));
        rotate.setRate(3);
        rotate.setCycleCount(-1);
        rotate.play();

    }

    private void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (newValue != null) {
            if (!body.getChildren().contains(title))
                body.getChildren().add(title);
        } else {
            body.getChildren().remove(title);
        }
    }

    @Override
    public StringProperty titleProperty() {
        return this.title.textProperty();
    }

    @Override
    public StringProperty legendProperty() {
        return this.legend.textProperty();
    }

    @Override
    public void setTitle(String _title) {
        this.title.setText(_title);
    }

    @Override
    public void setLegend(String _legend) {
        this.legend.setText(_legend);
    }
}
