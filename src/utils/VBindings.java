package utils;

import javafx.beans.Observable;
import javafx.beans.value.ObservableFloatValue;
import javafx.beans.value.ObservableValue;
import sun.misc.FloatConsts;

import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.sun.javafx.binding.FloatConstant.valueOf;
import static java.util.Arrays.asList;
import static utils.Vector3f.*;

/**
 * Created by strunov on 9/8/2015.
 */
public class VBindings {
    private static Vector3fBinding multiply0(ObservableFloatValue k, ObservableValue<Vector3f> v, Observable... dependencies){
        return new Vector3fBinding() {{
            bind(() -> {return v.getValue().multiply(k.get());}, dependencies);
        }};
    }

    private static Vector3fBinding add0(ObservableValue<Vector3f> v1, ObservableValue<Vector3f> v2, Observable... dependencies){
        return new Vector3fBinding() {{
            bind(() -> {return v1.getValue().add(v2.getValue());}, dependencies);
        }};
    }

    public static Vector3fBinding multiply(ObservableFloatValue k, ObservableValue<Vector3f> v){
        return multiply0(k, v, k, v);
    }

    public static Vector3fBinding multiply(float k, ObservableValue<Vector3f> v){
        return multiply0(valueOf(k), v, v);
    }

    public static Vector3fBinding multiply(ObservableFloatValue k, Vector3f v){
        return multiply0(k, new Vector3fConstant(v), k);
    }

    public static Vector3fBinding add(ObservableValue<Vector3f> v1, ObservableValue<Vector3f> v2){
        return  add0(v1, v2, v1, v2);
    }

    public static Vector3fBinding add(ObservableValue<Vector3f> v1, Vector3f v2){
        return add0(v1, new Vector3fConstant(v2), v1);
    }

    @SafeVarargs
    public static Vector3fBinding add (ObservableValue<Vector3f>... vectors){
        switch (vectors.length) {
            case 0 : return new Vector3fBinding() {{bind(() -> {return ZERO; }); }};
            case 1 : return new Vector3fBinding() {{bind(vectors[0]::getValue, vectors[0]);}};
            case 2 : return add0(vectors[0], vectors[1], vectors);
            default: return new Vector3fBinding() {{
                bind(() -> {
                    return sum(asList(vectors).stream().map(ObservableValue::getValue).toArray(Vector3f[]::new));
                }, vectors);    
            }};
        }
    }

    public static Vector3fBinding linear(ObservableFloatValue x, ObservableFloatValue y, ObservableFloatValue z){
        return add(multiply(x, X), multiply(y, Y), multiply(z, Z));
    }
}
