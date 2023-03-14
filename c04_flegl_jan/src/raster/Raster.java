package raster;

public interface Raster<E> {

    void clear();

    void setClearValue(E value);

    int getWidth();

    int getHeight();

    E getValue(int x, int y);

    void setValue(int x, int y, E value);

    default boolean isInside(int x, int y) {

        if(x<0 || x > this.getWidth()-1){
            return false;
        }
        if(y<0 || y > this.getHeight()-1){
            return false;
        }
        return true;

    }

}
