package rasterizers;

import raster.ZBuffer;
import shaders.Shader;
import transforms.Col;
import transforms.Vec3D;
import utils.Lerp;

public class LineRasterizer {

    public LineRasterizer() {
    }

    /***
     * Kód podobný vykreslování čáry jako v PGRF1, potřebujeme si pokrýt všechny kvadrály, to nám zařizuje to K.
     * Pak tu řešíme částečně i ořezávání, stejně jako v TriangleRasterizer
     * @param a after transformtowindow
     * @param b after transformtowindow
     * @param zBuffer just a zbuffer
     */
    public void rasterize(Vec3D a, Vec3D b, ZBuffer zBuffer) {

        //kvadrály
        double k = (b.getGetY() - a.getGetY()) / (b.getX() - a.getX());
        if (k <= 1 && k >= -1) {

            //If x1 > x2
            Vec3D temp;
            if (a.getX() > b.getX()) {
                temp = a;
                a = b;
                b = temp;
            }
            for (int x = Math.max((int) a.getX() + 1, 0);
                 x <= Math.min(b.getX(), zBuffer.getImageBuffer().getWidth() - 1);
                 x++) {
                double t = (x - a.getX()) / (b.getX() - a.getX());
                int y = (int) (a.getGetY() * (1 - t) + b.getGetY() * t);
                if (y < 0 && y > zBuffer.getImageBuffer().getHeight() - 1) continue;

                double z = (a.getZ() * (1 - t) + b.getZ() * t);
                zBuffer.drawWithTest(x, y, z, new Col(0x00ff00));
            }
        } else {

            //if x1 > x2
            Vec3D temp;
            if (a.getGetY() > b.getGetY()) {
                temp = a;
                a = b;
                b = temp;
            }
            for (int y = Math.max((int) a.getGetY() + 1, 0);
                 y <= Math.min(b.getGetY(), zBuffer.getImageBuffer().getHeight() - 1);
                 y++) {
                double t = (y - a.getGetY()) / (b.getGetY() - a.getGetY());
                int x = (int) (a.getX() * (1 - t) + b.getX() * t);
                if (x < 0 && x > zBuffer.getImageBuffer().getWidth() - 1) continue;

                double z = (a.getZ() * (1 - t) + b.getZ() * t);
                zBuffer.drawWithTest(x, y, z, new Col(0x00ff00));
            }
        }

    }
}
