package utils;

import java.io.Serializable;

import static java.util.Arrays.asList;

/**
 * Created by strunov on 9/8/2015.
 */
public class Vector3f implements Serializable {
    public final float x;
    public final float y;
    public final float z;

    public static Vector3f ZERO = new Vector3f(0, 0, 0);
    public static Vector3f X = new Vector3f(1, 0, 0);
    public static Vector3f Y = new Vector3f(0, 1, 0);
    public static Vector3f Z = new Vector3f(0, 0, 1);
    public static Vector3f XY = new Vector3f(1, 1, 0);
    public static Vector3f YZ = new Vector3f(0, 1, 1);
    public static Vector3f XZ = new Vector3f(1, 0, 1);
    public static Vector3f XYZ = new Vector3f(1, 1, 1);

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3f sum(Vector3f... vectors) {
        switch (vectors.length) {
            case 0:
                return ZERO;
            case 1:
                return vectors[0];
            case 2:
                return vectors[0].add(vectors[1]);
            default:
                return asList(vectors).stream().reduce(ZERO, Vector3f::add);
        }
    }

    public Vector3f multiply(float k) {
        if (k == 0) return ZERO;
        if (k == 1) return this;
        return new Vector3f(k * x, k * y, k * z);
    }

    public Matrix4f multiply(Vector3f v){
        return new Matrix4f(x*v.x, x*v.y, x*v.z, 0,
                    y*v.x, y*v.y, y*v.z, 0,
                    z*v.x, z*v.y, z*v.z, 0,
                    0, 0, 0, 1);
    }

    public Vector3f multiply(Matrix4f m) {
        return new Vector3f(m.xx * x + m.yx * y + m.zx * z + m.wx,
                    m.xy * x + m.yy * y + m.zy * z + m.wy,
                    m.xz * x + m.yz * y + m.zz * z + m.wz);
    }

    public Vector3f add(Vector3f v) {
        if (ZERO.equals(v)) return this;
        return new Vector3f(x + v.x, y + v.y, z + v.z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector3f vector3f = (Vector3f) o;
        return Float.compare(vector3f.x, x) == 0 && Float.compare(vector3f.y, y) == 0 && Float.compare(vector3f.z, z) == 0;
    }

    @Override
    public int hashCode() {
        int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        result = 31 * result + (z != +0.0f ? Float.floatToIntBits(z) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

}
