package io.github.gleidsonmt.suspense;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  15/08/2024
 */
public class Suspense3DCircle extends StackPane implements Loader {

    private final StackPane body = new StackPane();
    private final Label title = new Label();
    private final Label legend = new Label();

    public Suspense3DCircle() {
        this(null, null);
    }

    public Suspense3DCircle(String _title, String _legend) {
        this.getStyleClass().add("layout");

        List<Circle> circles = List.of(new Circle(), new Circle(), new Circle(), new Circle());
        circles.forEach(el -> {
            el.setStyle(" -fx-stroke-dash-array : 10; -fx-stroke : -fx-color;");
            config(el);
        });

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        this.setAlignment(Pos.CENTER);

        this.getChildren().add(root);
        root.getChildren().setAll(body);
        body.getChildren().setAll(circles);
        root.setSpacing(20);

        title.setStyle("-fx-font-size : 20; -fx-text-fill : -dark-gray;");
        legend.setStyle("-fx-font-size : 14pt");
//
        title.textProperty().addListener(this::changed);

        if (_legend != null) legend.setText(_legend);
        if (_title != null) title.setText(_title);

        rotate(circles.get(0), 360, new Point3D(100, 0, 0));
        rotate(circles.get(1), 180, new Point3D(100,100,0));
        rotate(circles.get(2), 270, new Point3D(100,50,0));
        rotate(circles.get(3), 90, new Point3D(100,100,0));
        root.getChildren().add(legend);
    }

    private void config(Circle... circles) {
        for (Circle circle : circles) {
            circle.setStrokeWidth(2);
            circle.setRadius(120);
            circle.setFill(Color.TRANSPARENT);
        }
    }

    private void rotate(Circle circle, int angle, Point3D point) {
        RotateTransition rotate = new RotateTransition(Duration.seconds(5), circle);

        rotate.setAxis(point);
        rotate.setAutoReverse(true);

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
