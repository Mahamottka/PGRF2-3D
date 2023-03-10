package raster;

import java.util.ArrayList;

public class DepthBuffer implements Raster<Double> {

    //dvourozmerny pole doblu sirka vyska clear value
    private double clearValue = 1;
    private final int width, height;
    private final double[][] buffer;

    public DepthBuffer(int width, int height) {
        this.width = width;
        this.height = height;
        this.buffer = new double[width][height];
    }


    @Override
    public void clear() {
        for (int i = 0; i < width - 1; i++) {
            for (int j = 0; j < height - 1; j++) {
                this.buffer[i][j] = clearValue;
            }
        }

    }

    @Override
    public void setClearValue(Double value) {
        this.clearValue = value;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public Double getValue(int x, int y) {
        return buffer[x][y];
    }

    @Override
    public void setValue(int x, int y, Double value) {
        if (isInside(x, y)) {
            buffer[x][y] = value;
        }
    }
}
