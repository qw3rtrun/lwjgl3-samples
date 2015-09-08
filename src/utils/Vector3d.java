package utils;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValueBase;
import javafx.geometry.Point3D;

import static javafx.geometry.Point3D.ZERO;

/**
 * Created by strunov on 9/7/2015.
 */
public final class Vector3d extends ObservableValueBase<Point3D> implements Cloneable{

    private Point3D point = ZERO;

    public Vector3d(double x, double y, double z){
        point = new Point3D(x, y, z);
    }

    public Vector3d(Point3D point) {
        this(point.getX(), point.getY(), point.getZ());
    }

    @Override
    public Point3D getValue() {
        return point;
    }

    @SuppressWarnings("CloneDoesntCallSuperClone")
    @Override
    public Vector3d clone(){
        return new Vector3d(point);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector3d vector3d = (Vector3d) o;
        return !(point != null ? !point.equals(vector3d.point) : vector3d.point != null);
    }

    @Override
    public int hashCode() {
        return point != null ? point.hashCode() : 0;
    }

    @Override
    public String toString() {
        return point.toString();
    }
}
