package utils;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Point3D;
import javafx.scene.transform.*;

import java.util.ArrayList;
import java.util.Iterator;

import static javafx.collections.FXCollections.observableList;
import static javafx.scene.transform.Rotate.Y_AXIS;
import static javafx.scene.transform.Transform.scale;
import static utils.Vector3f.XYZ;
import static utils.Vector3f.Z;
import static utils.Vector3f.ZERO;

/**
 * Created by strunov on 9/4/2015.
 */
public class Node {

    private final Vector3fProperty scale = new Vector3fProperty(XYZ) {
        @Override
        public Object getBean() {
            return Node.this;
        }

        @Override
        public String getName() {
            return "Node Transformation: Scale";
        }
    };
    private final Vector3fProperty rotation = new Vector3fProperty(ZERO) {
        @Override
        public Object getBean() {
            return Node.this;
        }

        @Override
        public String getName() {
            return "Node Transformation: Rotation";
        }
    };
    private final Vector3fProperty translation = new Vector3fProperty(ZERO) {
        @Override
        public String getName() {
            return "Node Transformation: Translation";
        }

        @Override
        public Object getBean() {
            return Node.this;
        }
    };

    private ObjectProperty<Transform> local = new SimpleObjectProperty<>();
    private ObjectProperty<Transform> absolute = new SimpleObjectProperty<>();

    private ObjectProperty<Node> parent = new SimpleObjectProperty<>();

    private ListProperty<Node> childs = new SimpleListProperty<>(observableList(new ArrayList<>()));

}

