package render;

import model.Part;
import model.Solid;
import model.Vertex;
import rasterizers.Rasterizer;

import transforms.*;
import utils.Clip;
import utils.Lerp;

import java.util.List;

public class Renderer {

    private final Rasterizer rasterizer;
    private Lerp lerp;
    private Mat4 proj;
    private Camera camera;
    private Mat4 cameraMat;

    public Renderer(Rasterizer rasterizer, Camera camera, Mat4 proj) {
        this.rasterizer = rasterizer;
        this.lerp = new Lerp();
        this.proj = proj;
        this.camera = camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void render(Solid solid) {
        cameraMat = camera.getViewMatrix();
        for (Part part : solid.getPartBuffer()) {
            switch (part.getType()) {
                case LINE:
                    int start2 = part.getIntex();
                    for (int i = 0; i < part.getCount(); i++) {
                        int indexA = start2;
                        int indexB = start2 + 1;

                        start2 += 2;

                        Vertex a = solid.getVertexBuffer().get(solid.getIndexBuffer().get(indexA));
                        Vertex b = solid.getVertexBuffer().get(solid.getIndexBuffer().get(indexB));

                        a = a.transform(solid.getModel(), cameraMat, proj);
                        b = b.transform(solid.getModel(), cameraMat, proj);

                        renderLine(a,b);
                    }
                    break;
                case TRIANGLE:
                    int start = part.getIntex();
                    for (int i = 0; i < part.getCount(); i++) {
                        // spocitat pozice indexu v indexbufferu (start + 1, start + 2)
                        int indexA = start;
                        int indexB = start + 1;
                        int indexC = start + 2;
                        //zvetsit start o 3
                        start += 3;
                        //vytáhnout vertex z vertex bufferu na zakladě indexu z index bufferu


                        Vertex a = solid.getVertexBuffer().get(solid.getIndexBuffer().get(indexA));
                        Vertex b = solid.getVertexBuffer().get(solid.getIndexBuffer().get(indexB));
                        Vertex c = solid.getVertexBuffer().get(solid.getIndexBuffer().get(indexC));

                        a = a.transform(solid.getModel(), cameraMat, proj);
                        b = b.transform(solid.getModel(), cameraMat, proj);
                        c = c.transform(solid.getModel(), cameraMat, proj);
                        // Poslat do rasterizeru
                        renderTriangle(a,b,c);
                    }
                    break;
            }
        }
    }

    private void renderLine(Vertex a, Vertex b){


        rasterizer.rasterizeLine(a,b);
    }
    private void renderTriangle(Vertex a, Vertex b, Vertex c){

        clipTriangle(a,b,c);
    }
    private void renderPoint(Vertex a){

    }



    private void clipTriangle(Vertex a, Vertex b, Vertex c) {
        if ((a.getX() > a.getW() && b.getX() > b.getW() && c.getX() > c.getW()) ||
                (a.getX() < -a.getW() && b.getX() < -b.getW() && c.getX() < -c.getW()) ||
                (a.getY() < -a.getW() && b.getY() < -b.getW() && c.getY() < -c.getW()) ||
                (a.getY() > a.getW() && b.getY() > b.getW() && c.getY() > c.getW()) ||
                (a.getZ() < 0 && b.getZ() < 0 && c.getZ() < 0) ||
                (a.getZ() > a.getW() && b.getZ() > b.getW() && c.getZ() > c.getW()))
            return;


        //seřazení vrcholů podle Z, Az = max
        Vertex temp;
        while (!(a.getZ() >= b.getZ() && b.getZ() >= c.getZ())) {
            if (a.getZ() < b.getZ()) {
                temp = a;
                a = b;
                b = temp;
            }
            if (b.getZ() < c.getZ()) {
                temp = b;
                b = c;
                c = temp;
            }
        }


        double zMin = 0;
        if (a.getZ() < zMin) {
            return;
        }
        if (b.getZ() < zMin) {
            double t1 = (zMin - a.getZ()) / (b.getZ() - a.getZ());
            Vertex vab = lerp.lerp(a, b, t1);

            double t2 = (zMin - a.getZ() / c.getZ() - a.getZ());
            Vertex vac = lerp.lerp(a, c, t2);

            rasterizer.rastarizeTriangle(a, vab, vac);
            return;
        }

        if (c.getZ() < zMin) {
            double t1 = (zMin - b.getZ()) / (c.getZ() - b.getZ());
            Vertex vbc = lerp.lerp(b, c, t1);

            double t2 = (zMin - a.getZ() / c.getZ() - a.getZ());
            Vertex vac = lerp.lerp(a, c, t2);

            rasterizer.rastarizeTriangle(a, vbc, vac);
            return;
        }

        rasterizer.rastarizeTriangle(a,b,c);
    }

}
