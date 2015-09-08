package utils;

import java.io.Serializable;

import static utils.Vector3f.*;

/**
 * Created by qw3rt_000 on 09.09.2015.
 */
public class Matrix4f implements Serializable {
    public final float xx;
    public final float xy;
    public final float xz;
    public final float xw;
    public final float yx;
    public final float yy;
    public final float yz;
    public final float yw;
    public final float zx;
    public final float zy;
    public final float zz;
    public final float zw;
    public final float wx;
    public final float wy;
    public final float wz;
    public final float ww;

    public static final Matrix4f O0 = rows(ZERO, ZERO, ZERO, 0);

    public static final Matrix4f O1 = rows(ZERO, ZERO, ZERO, 1);

    public static final Matrix4f E = rows(X, Y, Z, 1);

    public static Matrix4f rows(Vector3f x, Vector3f y, Vector3f z, float ww) {
        return new Matrix4f(x.x, x.y, x.z, 0, y.x, y.y, y.z, 0, z.x, z.y, z.z, 0, 0, 0, 0, ww);
    }

    public static Matrix4f cols(Vector3f x, Vector3f y, Vector3f z, float ww) {
        return new Matrix4f(x.x, y.x, z.x, 0, x.y, y.y, z.y, 0, x.z, y.z, z.z, 0, 0, 0, 0, ww);
    }

    public Matrix4f(float xx, float xy, float xz, float xw, float yx, float yy, float yz, float yw, float zx, float zy, float zz, float zw, float wx, float wy, float wz, float ww) {
        this.xx = xx;
        this.xy = xy;
        this.xz = xz;
        this.xw = xw;
        this.yx = yx;
        this.yy = yy;
        this.yz = yz;
        this.yw = yw;
        this.zx = zx;
        this.zy = zy;
        this.zz = zz;
        this.zw = zw;
        this.wx = wx;
        this.wy = wy;
        this.wz = wz;
        this.ww = ww;
    }

    public Matrix4f add(Matrix4f m) {
        return new Matrix4f(m.xx + xx, m.xy + xy, m.xz + xz, m.xw + xw,
                m.yx + yx, m.yy + yy, m.yz + yz, m.yw + yw,
                m.zx + zx, m.zy + zy, m.zz + zz, m.zw + zw,
                m.wx + wx, m.wy + wy, m.wz + wz, m.ww + ww);
    }

    public Matrix4f multiply(double a) {
        return new Matrix4f((float) a * xx, (float) (a * xy), (float) a * xz, (float) a * xw,
                (float) a * yx, (float) a * yy, (float) a * yz, (float) a * yw,
                (float) a * zx, (float) a * zy, (float) a * zz, (float) a * zw,
                (float) a * wx, (float) a * wy, (float) a * wz, (float) a * ww);
    }

    public Matrix4f transpose() {
        return new Matrix4f(xx, yx, zx, wx, xy, yy, zy, wy, xz, yz, zz, wz, xw, yw, zw, ww);
    }

    public Vector3f multiply(Vector3f v) {
        return new Vector3f(xx * v.x + xy * v.y + xz * v.z + xw, yx * v.x + yy * v.y + yz * v.z + yw, zx * v.x + zy * v.y + zz * v.z + zw);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Matrix4f matrix4f = (Matrix4f) o;

        if (Float.compare(matrix4f.ww, ww) != 0) return false;
        if (Float.compare(matrix4f.wx, wx) != 0) return false;
        if (Float.compare(matrix4f.wy, wy) != 0) return false;
        if (Float.compare(matrix4f.wz, wz) != 0) return false;
        if (Float.compare(matrix4f.xw, xw) != 0) return false;
        if (Float.compare(matrix4f.xx, xx) != 0) return false;
        if (Float.compare(matrix4f.xy, xy) != 0) return false;
        if (Float.compare(matrix4f.xz, xz) != 0) return false;
        if (Float.compare(matrix4f.yw, yw) != 0) return false;
        if (Float.compare(matrix4f.yx, yx) != 0) return false;
        if (Float.compare(matrix4f.yy, yy) != 0) return false;
        if (Float.compare(matrix4f.yz, yz) != 0) return false;
        if (Float.compare(matrix4f.zw, zw) != 0) return false;
        if (Float.compare(matrix4f.zx, zx) != 0) return false;
        if (Float.compare(matrix4f.zy, zy) != 0) return false;
        if (Float.compare(matrix4f.zz, zz) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (xx != +0.0f ? Float.floatToIntBits(xx) : 0);
        result = 31 * result + (xy != +0.0f ? Float.floatToIntBits(xy) : 0);
        result = 31 * result + (xz != +0.0f ? Float.floatToIntBits(xz) : 0);
        result = 31 * result + (xw != +0.0f ? Float.floatToIntBits(xw) : 0);
        result = 31 * result + (yx != +0.0f ? Float.floatToIntBits(yx) : 0);
        result = 31 * result + (yy != +0.0f ? Float.floatToIntBits(yy) : 0);
        result = 31 * result + (yz != +0.0f ? Float.floatToIntBits(yz) : 0);
        result = 31 * result + (yw != +0.0f ? Float.floatToIntBits(yw) : 0);
        result = 31 * result + (zx != +0.0f ? Float.floatToIntBits(zx) : 0);
        result = 31 * result + (zy != +0.0f ? Float.floatToIntBits(zy) : 0);
        result = 31 * result + (zz != +0.0f ? Float.floatToIntBits(zz) : 0);
        result = 31 * result + (zw != +0.0f ? Float.floatToIntBits(zw) : 0);
        result = 31 * result + (wx != +0.0f ? Float.floatToIntBits(wx) : 0);
        result = 31 * result + (wy != +0.0f ? Float.floatToIntBits(wy) : 0);
        result = 31 * result + (wz != +0.0f ? Float.floatToIntBits(wz) : 0);
        result = 31 * result + (ww != +0.0f ? Float.floatToIntBits(ww) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "(" + '(' + xx + ", " + xy + ", " + xz + ", " + xw + "), " + '(' + yx + ", " + yy + ", " + yz + ", " + yw + "), " + '(' + zx + ", " + zy + ", " + zz + ", " + zw + "), " + '(' + wx + ", " + wy + ", " + wz + ", " + ww + "))";

    }
}

