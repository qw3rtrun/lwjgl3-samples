package utils;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;

import static utils.VBindings.add;
import static utils.VBindings.linear;
import static utils.VBindings.multiply;
import static utils.Vector3fConstant.CONST_X;
import static utils.Vector3fConstant.CONST_Y;
import static utils.Vector3fConstant.CONST_Z;

/**
 * Created by strunov on 9/8/2015.
 */
public class Test {

    public static void main(String[] args) {

        FloatProperty ap = new SimpleFloatProperty(1);
        FloatProperty bp = new SimpleFloatProperty(1);
        FloatProperty gp = new SimpleFloatProperty(1);

        Vector3fProperty v = new Vector3fProperty();
        v.bind(linear(ap, bp, gp));

        /*v.addListener((m, o, n) -> {
            System.out.println(o + " -> " + n);
        });*/

        System.out.println(v.getValue());

        ap.setValue(5);
        bp.setValue(4);
        gp.setValue(3);

        System.out.println(v.getValue());

    }
}

