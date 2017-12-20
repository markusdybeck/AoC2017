package adventofcode.utils;

import java.util.Objects;

public class Vector3 {

    private Long x;
    private Long y;
    private Long z;

    public Vector3(final Long x, final Long y, final Long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Long getX() {
        return x;
    }

    public void setX(final Long x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(final Long y) {
        this.y = y;
    }

    public Long getZ() {
        return z;
    }

    public void setZ(final Long z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return getClass().getName() + " {" +
            "x=" + x + ", y=" + y + ", z=" + z + '}';
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        final Vector3 vector3 = Vector3.class.cast(obj);

        return Objects.equals(x, vector3.x) &&
            Objects.equals(y, vector3.y) &&
            Objects.equals(z, vector3.z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
