package adventofcode.utils;

import java.util.Objects;

public class Particle {

    private Integer id;
    private Vector3 p;
    private Vector3 v;
    private Vector3 a;

    public Particle() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Vector3 getP() {
        return p;
    }

    public void setP(final Vector3 p) {
        this.p = p;
    }

    public Vector3 getV() {
        return v;
    }

    public void setV(final Vector3 v) {
        this.v = v;
    }

    public Vector3 getA() {
        return a;
    }

    public void setA(final Vector3 a) {
        this.a = a;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        final Particle particle = Particle.class.cast(obj);

        return Objects.equals(p, particle.p);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p);
    }

    @Override
    public String toString() {
        return (getClass().getName() + " {") +
            "id=" +
            id +
            ", p=" +
            p +
            ", v=" +
            v +
            ", a=" +
            a +
            '}';
    }
}
