package io.github.gleidsonmt.suspense;

import javafx.animation.*;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  15/08/2024
 */
public class SuspenseCircle extends StackPane implements Loader {

    private final VBox root = new VBox();
    private final StackPane body = new StackPane();
    private final Label title = new Label();
    private final Label legend = new Label();

    private final Circle one = new Circle();
    private final Circle two = new Circle();

    private final Timeline animation = new Timeline();

    private Object oldRoot;

    public SuspenseCircle() {
        this(null, null);
    }

    public SuspenseCircle(String _title, String _legend) {
        this.getStyleClass().add("layout");
        one.setStrokeDashOffset(540);
        one.setStyle(" -fx-stroke-dash-array : 550; -fx-stroke : -fx-color;");
        two.setStyle(" -fx-stroke : -light-gray;");

        one.setStrokeType(StrokeType.OUTSIDE);
        one.setStrokeType(StrokeType.CENTERED);

        config(one);
        config(two);

        root.setAlignment(Pos.CENTER);
        this.setAlignment(Pos.CENTER);

        this.getChildren().add(root);
        root.getChildren().setAll(body);
        body.getChildren().setAll(two, one);
        root.setSpacing(20);

        title.setStyle("-fx-font-size : 20; -fx-text-fill : -dark-gray;");
        legend.setStyle("-fx-font-size : 14pt");
//
        title.textProperty().addListener(this::changed);
//
        if (_legend != null) legend.setText(_legend);
        if (_title != null) title.setText(_title);

        rotate(one, 360, 5);
        root.getChildren().add(legend);

        one.setStrokeLineCap(StrokeLineCap.ROUND);
    }

    private void config(Circle... circles) {
        for (Circle circle : circles) {
            circle.setStrokeWidth(4);
            circle.setRadius(120);
            circle.setFill(Color.TRANSPARENT);
        }
    }

    private void rotate(Circle circle, int angle, int duration) {
        RotateTransition rotate = new RotateTransition(Duration.seconds(duration), circle);

//        rotate.setAxis(new Point3D(10,10,10));
//        rotate.setAutoReverse(true);

        rotate.setByAngle(angle);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setCycleCount(Timeline.INDEFINITE);
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
