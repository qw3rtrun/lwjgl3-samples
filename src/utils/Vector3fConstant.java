package utils;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import static utils.Vector3f.X;
import static utils.Vector3f.Y;
import static utils.Vector3f.Z;

/**
 * Created by strunov on 9/8/2015.
 */
public class Vector3fConstant implements ObservableValue<Vector3f> {

    public static Vector3fConstant CONST_X = new Vector3fConstant(X);
    public static Vector3fConstant CONST_Y = new Vector3fConstant(Y);
    public static Vector3fConstant CONST_Z = new Vector3fConstant(Z);

    private final Vector3f v;

    public Vector3fConstant(Vector3f v) {
        this.v = v;
    }

    @Override
    public void addListener(ChangeListener<? super Vector3f> listener) {
    }

    @Override
    public void removeListener(ChangeListener<? super Vector3f> listener) {
    }

    @Override
    public Vector3f getValue() {
        return v;
    }

    @Override
    public void addListener(InvalidationListener listener) {
    }

    @Override
    public void removeListener(InvalidationListener listener) {
    }
}
