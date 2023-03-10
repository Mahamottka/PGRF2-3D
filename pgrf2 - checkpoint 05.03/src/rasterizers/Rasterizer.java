package rasterizers;

import model.Vertex;
import raster.ZBuffer;
import transforms.Vec3D;

/***
 *
 */
public class Rasterizer {

    private final ZBuffer zBuffer;
    private int h,w;

    public Rasterizer(ZBuffer zBuffer) {
        this.zBuffer = zBuffer;
        h = zBuffer.getImageBuffer().getHeight();
        w = zBuffer.getImageBuffer().getWidth();
    }

    public void rastarizeTriangle(Vertex a, Vertex b, Vertex c){
        a = a.dehomog();
        b = b.dehomog();
        c = c.dehomog();

        Vec3D aV = a.transformToWindow(w,h);
        Vec3D bV = b.transformToWindow(w,h);
        Vec3D cV = c.transformToWindow(w,h);
    }
    public void rasterizeLine(Vertex a, Vertex b){
        a = a.dehomog();
        b = b.dehomog();

        Vec3D aV = a.transformToWindow(w,h);
        Vec3D bV = b.transformToWindow(w,h);
    }
    public void rasterizePoint(Vertex a){
        a = a.dehomog();

        Vec3D aV = a.transformToWindow(w,h);
    }
}
