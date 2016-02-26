package utils;

import java.util.function.Function;

import static utils.Vector3f.ZERO;

/**
 * Created by strunov on 9/8/2015.
 */
public class Test {

    public static void main(String[] args) {

        Function<Float, Vector3f> test = (f) -> {
            Vector3f v = new Vector3f(10f, 34.5f, -3.8f);
            Matrix4f m = new Matrix4f(12, f, 4, 6, 5.6f, 3.4f, 4.5f, 4, 10, -1, 0, -4, 0.5f, 2, 3, 4);
            return m.multiply(v);
        };

        for (int i = 0; i < 10000000; i++) {
            test.apply((float) i);
        }

        long start = System.currentTimeMillis();


        for (int i = 0; i < 1000000000; i++) {
//            Vector3f v = ZERO;
//            for (int j = 0; j < 10; j++) {
                test.apply((float) i);
//            }
        }

        System.out.println(System.currentTimeMillis() - start);

    }

}

