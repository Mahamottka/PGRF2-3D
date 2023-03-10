package render;

import model.Part;
import model.Solid;
import model.Vertex;
import rasterizers.Rasterizer;
import rasterizers.TriangleRasterizer;
import utils.Clip;
import utils.Lerp;

import java.util.List;

public class Renderer {

    private final Rasterizer rasterizer;
    private Lerp lerp;

    public Renderer(TriangleRasterizer triangleRasterizer, Rasterizer rasterizer) {
        this.rasterizer = rasterizer;
        this.lerp = new Lerp();
    }

    public void render(Solid solid) {
        for (Part part : solid.getPartBuffer()) {
            switch (part.getType()) {
                case LINE:
                    //TODO implementovat
                    renderLine();
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
        a = a.transform();
        b = b.transform();

        //když oba dva body jsou mimo vykreslovacín objem, tak přestaneš vykreslovat tvar
        if (Clip.testMultipleVertex(a,b) == 2) return;
        rasterizer.rasterizeLine(a,b);
    }
    private void renderTriangle(Vertex a, Vertex b, Vertex c){
        a = a.transform();
        b = b.transform();
        c = c.transform();

        if (Clip.testMultipleVertex(a,b) == 2) return;
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
    public void render(List<Solid> scene) {
        for (Solid solid : scene)
            render(solid);
    }
}
