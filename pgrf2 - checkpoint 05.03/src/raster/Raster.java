package raster;

public interface Raster<E> {

    void clear();

    void setClearValue(E value);

    int getWidth();

    int getHeight();

    E getValue(int x, int y);

    void setValue(int x, int y, E value);

    default boolean isInside(int x, int y){
        //getWidth() getHeight

        //TODO kontrola, true tam je jenom aby to tedka fungovalo
        return true;
    }

}
