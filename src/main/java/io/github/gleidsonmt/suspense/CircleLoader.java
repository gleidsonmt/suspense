package io.github.gleidsonmt.suspense;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.beans.property.StringProperty;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  17/09/2024
 */
public abstract class CircleLoader extends VBox implements SuspenseLoader {

    protected final Label title;
    protected final Label legend;

    public CircleLoader() {
        this("Loading...", "Loading tasks...");
    }

    public CircleLoader(String _title, String _legend) {
        title = new Label(_title);
        legend = new Label(_legend);

        title.getStyleClass().add("title");
        legend.getStyleClass().add("legend");

        // setting a margin between sections
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
        this.getStyleClass().add(0, "circle-loader");
        //
        Pane circleContainer = createCircleContainer();
        circleContainer.getStyleClass().add("container-circle");
        circleContainer.getChildren().add(title);
        this.getChildren().setAll(circleContainer, legend);

        this.getStylesheets().add(HelloApplication.class.getResource("master.css").toExternalForm());
    }

    protected abstract Pane createCircleContainer(); //

    protected void rotate(Circle circle, int angle, int duration, boolean autoReverse, Point3D point) {
        RotateTransition rotate = new RotateTransition(Duration.seconds(duration), circle);

        if (point != Point3D.ZERO) {
            rotate.setAxis(point);
        }
        rotate.setAutoReverse(autoReverse);

        rotate.setByAngle(angle);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setCycleCount(Timeline.INDEFINITE);
        rotate.play();

    }

    protected void rotate(Circle circle, int angle, int duration) {
        rotate(circle, angle, duration, true, Point3D.ZERO);
    }

    protected void rotate(Circle circle, int angle, int duration, boolean autoReverse) {
        rotate(circle, angle, duration, autoReverse, Point3D.ZERO);
    }

    protected void rotate(Circle circle, int angle, int duration, Point3D point3D) {
        rotate(circle, angle, duration, true, point3D);
    }

    @Override
    public StringProperty titleProperty() {
        return title.textProperty();
    }

    @Override
    public StringProperty legendProperty() {
        return legend.textProperty();
    }

    @Override
    public void setTitle(String _title) {
        title.setText(_title);
    }

    @Override
    public void setLegend(String _legend) {
        legend.setText(_legend);
    }
}
