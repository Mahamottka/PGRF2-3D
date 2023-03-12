package raster;

public interface Raster<E> {

    void clear();

    void setClearValue(E value);

    int getWidth();

    int getHeight();

    E getValue(int x, int y);

    void setValue(int x, int y, E value);

    default boolean isInside(int x, int y) {

        if (x < getWidth() && y < getHeight() && x >= 0 && y >= 0) {
            return true;
        }

        return false;

    }

}
