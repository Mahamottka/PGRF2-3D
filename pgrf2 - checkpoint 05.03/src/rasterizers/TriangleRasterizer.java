package rasterizers;

import model.Vertex;
import raster.ZBuffer;
import shaders.Shader;
import shaders.ShaderConstant;
import transforms.Col;
import transforms.Point3D;
import transforms.Vec3D;
import utils.Lerp;

public class TriangleRasterizer {

    private final ZBuffer zBuffer;

    private final Lerp lerp;
    private Shader shader;

    public TriangleRasterizer(ZBuffer zBuffer) {
        this.zBuffer = zBuffer;
        this.lerp = new Lerp();
        setShader(new ShaderConstant());
    }


    public void setShader(Shader shader) {
        this.shader = shader;
    }

    public void rasterize(Vec3D a, Vec3D b, Vec3D c, ZBuffer zBuffer) {

        //pro debug ucely
        //Graphics g = zBuffer.getImageBuffer().getGraphics();
        //g.setColor(new Color(0x00ff00));
        //g.drawLine((int) a.getX(), (int) a.getGetY(), (int) b.getX(), (int) b.getGetY());
        //.drawLine((int) b.getX(), (int) b.getGetY(), (int) c.getX(), (int) c.getGetY());
        //g.drawLine((int) c.getX(), (int) c.getGetY(), (int) a.getX(), (int) a.getGetY());

        Vec3D temp;
        while (!(a.getGetY() <= b.getGetY() && b.getGetY() <= c.getGetY())) {
            if (a.getGetY() > b.getGetY()) {
                temp = a;
                a = b;
                b = temp;
            }
            if (b.getGetY() > c.getGetY()) {
                temp = b;
                b = c;
                c = temp;
            }
        }

        /***
         * Math.max - for better perfomance -> nekotrolujeme ohranice az v zBufferu
         * Max se podívá na dva vstupní parametry a vybere větší
         * Min to dělá to samé akorát zvrchu
         * Max a min vlastně řeší ořezávání
         */
        //Prvni cyklus
        for (int y = (int) Math.max(a.getGetY() + 1, 0);
             y <= Math.min(b.getGetY(), zBuffer.getImageBuffer().getHeight());
             y++) {

            //interpolacni koeficient pro AB
            double t1 = (y - a.getGetY()) / (b.getGetY() - a.getGetY());
            int x1 = (int) ((1 - t1) * a.getX() + t1 * b.getX());

            Vec3D vab = lerp.lerp(a, b, t1);


            //interpolacni keoficient pro AC
            double t2 = (y - a.getGetY()) / (c.getGetY() - a.getGetY());
            int x2 = (int) ((1 - t2) * a.getX() + t2 * c.getX());

            Vec3D vac = lerp.lerp(a, c, t2);

            //vypocet pro Z
            double zStep = ((vac.getZ() - vab.getZ())/ (x2 - x1));
            x1 = Math.max(x1 + 1,0);
            x2 = Math.min(x2, zBuffer.getImageBuffer().getWidth() - 1);
            double z = vab.getZ();

            if (x1 > x2) {
                int helper = x1;
                x1 = x2;
                x2 = helper;
                z = vac.getZ();
            }

            for (int x = x1; x <= x2; x++) {
                //barvy se interpolují špatně, proto prostě bez shaderu (for now at least)
                zBuffer.drawWithTest(x, y, z, new Col(0x00ff00));//TODO vertex ten interpolovanej, same bellow);
                z += zStep;
            }
        }

        //Druhy cyklus
        for (int y = (int) Math.max(b.getGetY() + 1, 0);
             y <= Math.min(c.getGetY(), zBuffer.getImageBuffer().getHeight());
             y++) {

            //interpolacni koeficient pro AB
            double t1 = (y - b.getGetY()) / (c.getGetY() - b.getGetY());
            int x1 = (int) ((1 - t1) * b.getX() + t1 * c.getX());

            Vec3D vab = lerp.lerp(b, c, t1);


            //interpolacni keoficient pro AC
            double t2 = (y - b.getGetY()) / (c.getGetY() - b.getGetY());
            int x2 = (int) ((1 - t2) * b.getX() + t2 * c.getX());

            Vec3D vac = lerp.lerp(b, c, t2);

            //vypocet pro Z
            double zStep = ((vac.getZ() - vab.getZ())/ (x2 - x1));
            x1 = Math.max(x1 + 1,0);
            x2 = Math.min(x2, zBuffer.getImageBuffer().getWidth() - 1);
            double z = vab.getZ();

            if (x1 > x2) {
                int helper = x1;
                x1 = x2;
                x2 = helper;
                z = vac.getZ();
            }

            for (int x = x1; x <= x2; x++) {
                //barvy se interpolují špatně, proto prostě bez shaderu (for now at least)
                zBuffer.drawWithTest(x, y, z, new Col(0x00ff00));//TODO vertex ten interpolovanej, same bellow);
                z += zStep;
            }
        }

    }
}
