package io.github.gleidsonmt.suspense;

import javafx.beans.property.StringProperty;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  13/09/2024
 */
public interface SuspenseLoader {

    StringProperty titleProperty();

    StringProperty legendProperty();

    void setTitle(String _title);

    void setLegend(String _legend);
}
