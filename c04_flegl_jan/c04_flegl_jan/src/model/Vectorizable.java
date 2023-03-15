package model;

import transforms.Point3D;

public interface Vectorizable<V> {

    V mul(double k);
    V add(V v);
    Point3D getPosition();
}
