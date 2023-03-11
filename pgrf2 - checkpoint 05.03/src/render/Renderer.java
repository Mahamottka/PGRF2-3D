package render;

import model.Part;
import model.Solid;
import model.Vertex;
import rasterizers.Rasterizer;
import rasterizers.TriangleRasterizer;
import transforms.Camera;
import transforms.Mat4;
import transforms.Mat4Transl;
import transforms.Mat4ViewRH;
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

                        // Poslat do rasterizeru
                        renderTriangle(a,b,c);
                    }
                    break;
            }
        }
    }

    private void renderLine(Vertex a, Vertex b){
        a = a.transform(a.getModel(), cameraMat, proj);
        b = b.transform(b.getModel(), cameraMat, proj);

        //když oba dva body jsou mimo vykreslovacín objem, tak přestaneš vykreslovat tvar
        if (Clip.testMultipleVertex(a,b) == 2) return;
        rasterizer.rasterizeLine(a,b);
    }
    private void renderTriangle(Vertex a, Vertex b, Vertex c){
        a = a.transform(a.getModel(), cameraMat, proj);

        b = b.transform(b.getModel(), cameraMat, proj);
        c = c.transform(c.getModel(), cameraMat, proj);

        if (Clip.testMultipleVertex(a,b,c) == 3) return;
        //Ořezujeme podle Z
        while (!(a.getPosition().getZ() >= b.getPosition().getZ() && b.getPosition().getZ() >= c.getPosition().getZ())) {
            if (a.getPosition().getZ() < b.getPosition().getZ()) {
                Vertex temp = a;
                a = b;
                b = temp;
            }
            if (b.getPosition().getZ() < c.getPosition().getZ()) {
                Vertex temp = b;
                b = c;
                c = temp;
            }
        }
        rasterizer.rastarizeTriangle(a,b,c);
    }
    private void renderPoint(Vertex a){

    }


    /*
    private void renderTriangle(Vertex a, Vertex b, Vertex c) {
        //TODO: fast clip - slide99
        if ((a.getX() > a.getW() && b.getX() > b.getW() && c.getX() > c.getW()) ||
                (a.getX() < -a.getW() && b.getX() < -b.getW() && c.getX() < -c.getW()) ||
                (a.getY() < -a.getW() && b.getY() < -b.getW() && c.getY() < -c.getW()) ||
                (a.getY() > a.getW() && b.getY() > b.getW() && c.getY() > c.getW()) ||
                (a.getZ() < 0 && b.getZ() < 0 && c.getZ() < 0) ||
                (a.getZ() > a.getW() && b.getZ() > b.getW() && c.getZ() > c.getW()))
            return;
            //TODO: Orezani podle z - slide103

            //TODO: seradit vrcholi podle Z, kde Az je největší = max

            //orezani trojuhelniku
            double zMin = 0;
        if (a.getZ() < zMin) {
            return;
        }
        if (b.getZ() < zMin) {
            double t1 = (zMin - a.getZ()) / (b.getZ() - a.getZ());
            Vertex vab = lerp.lerp(a, b, t1);

            double t2 = (zMin - a.getZ() / c.getZ() - a.getZ());
            Vertex vac = lerp.lerp(a, c, t2);

            triangleRasterizer.rasterize(a, vab, vac);

            return;
        }

        if (c.getZ() < zMin) {
            //TODO: dodelat

        }

        //triangleRasterizer.rasterize(a,b,c)
    }
*/

}
