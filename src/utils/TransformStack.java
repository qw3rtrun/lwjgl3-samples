package utils;

import javafx.geometry.Point3D;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.*;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Stack;

import static javafx.geometry.Point3D.ZERO;
import static javafx.scene.transform.Rotate.Y_AXIS;

/**
 * Created by strunov on 9/3/2015.
 */
public class TransformStack {

    private Deque<Transform> stack = new ArrayDeque<>();

    {
        stack.add(new Affine());
    }

    public Transform get() {
        return stack.getLast();
    }

    public Transform transform(Transform transform) {
        Transform a = transform.createConcatenation(stack.getLast());
        stack.add(a);
        return a;
    }

    public Transform translate(double x, double y, double z) {
        return transform(new Translate(x, y, z));
    }

    public Transform rotate(double angle, Point3D axis) {
        return transform(new Rotate(angle, axis));
    }

    public Transform scale(double x, double y, double z) {return transform(new Scale(x, y, z));}

    public static void main(String[] args) {
        TransformStack stack = new TransformStack();

        Point3D p = new Point3D(2, 0, 0);

        stack.scale(1.5, 0, 0);
        stack.rotate(90, Rotate.Y_AXIS);
        stack.translate(0, -1, 0);

        Point3D point = stack.get().transform(p);
        System.out.println(point);
    }

}
