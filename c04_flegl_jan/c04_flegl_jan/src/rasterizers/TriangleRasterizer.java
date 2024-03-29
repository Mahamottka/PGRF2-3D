package rasterizers;


import raster.ZBuffer;
import transforms.Col;
import transforms.Vec3D;
import utils.Lerp;


public class TriangleRasterizer {


    private final Lerp lerp;

    public TriangleRasterizer() {
        this.lerp = new Lerp();
    }


    public void rasterize(Vec3D a, Vec3D b, Vec3D c, ZBuffer zBuffer, Col colA, Col colB, Col colC) {


        Vec3D temp;
        Col colorTemp;
        while (!(a.getGetY() <= b.getGetY() && b.getGetY() <= c.getGetY())) {
            if (a.getGetY() > b.getGetY()) {
                temp = a;
                a = b;
                b = temp;
                colorTemp = colA;
                colA = colB;
                colB = colorTemp;
            }
            if (b.getGetY() > c.getGetY()) {
                temp = b;
                b = c;
                c = temp;
                colorTemp = colB;
                colB = colC;
                colC = colorTemp;
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
             y <= Math.min(b.getGetY(), zBuffer.getImageBuffer().getHeight() -1) ;
             y++) {

            //interpolacni koeficient pro AB
            double t1 = (y - a.getGetY()) / (b.getGetY() - a.getGetY());
            int x1 = (int) ((1 - t1) * a.getX() + t1 * b.getX());

            Vec3D vab = lerp.lerp(a, b, t1);
            Col ab = lerp.lerp(colA, colB, t1);

            //interpolacni keoficient pro AC
            double t2 = (y - a.getGetY()) / (c.getGetY() - a.getGetY());
            int x2 = (int) ((1 - t2) * a.getX() + t2 * c.getX());

            Vec3D vac = lerp.lerp(a, c, t2);

            Col ac = lerp.lerp(colA, colC, t2);

            //vypocet pro Z
            double zStep = ((vac.getZ() - vab.getZ())/ (x2 - x1));
            x1 = Math.max(x1 + 1,0);
            x2 = Math.min(x2, zBuffer.getImageBuffer().getWidth() - 1);
            double z = vab.getZ();

            Col colStep = ((ac.add(ab.mul(-1))).mul(1. / (x2 - x1)));
            Col color = ab;


            if (x1 > x2) {
                int helper = x1;
                x1 = x2;
                x2 = helper;
                z = vac.getZ();
                color = ac;
            }

            for (int x = x1 + 1; x <= x2; x++) {
                //barvy se interpolují špatně, proto prostě bez shaderu (for now at least)
                zBuffer.drawWithTest(x, y, z, new Col(color));
                z += zStep;
                color = color.add(colStep);
            }
        }

        //Druhy cyklus
        for (int y = (int) Math.max(b.getGetY() + 1, 0);
             y <= Math.min(c.getGetY(), zBuffer.getImageBuffer().getHeight() -1);
             y++) {

            //interpolacni koeficient pro AB
            double t1 = (y - b.getGetY()) / (c.getGetY() - b.getGetY());
            int x1 = (int) ((1 - t1) * b.getX() + t1 * c.getX());

            Vec3D vbc = lerp.lerp(b, c, t1);
            Col bc = lerp.lerp(colB, colC, t1);


            //interpolacni keoficient pro AC
            double t2 = (y - a.getGetY()) / (c.getGetY() - a.getGetY());
            int x2 = (int) ((1 - t2) * a.getX() + t2 * c.getX());

            Vec3D vac = lerp.lerp(a, c, t2);
            Col ac = lerp.lerp(colA, colC, t2);

            //vypocet pro Z
            double zStep = ((vac.getZ() - vbc.getZ())/ (x2 - x1));
            x1 = Math.max(x1 + 1,0);
            x2 = Math.min(x2, zBuffer.getImageBuffer().getWidth() - 1);
            double z = vbc.getZ();

            Col colStep = ((ac.add(bc.mul(-1))).mul(1. / (x2 - x1)));
            Col color = bc;

            if (x1 > x2) {
                int helper = x1;
                x1 = x2;
                x2 = helper;
                z = vac.getZ();
                color = ac;
            }

            for (int x = x1 + 1; x <= x2; x++) {
                //barvy se interpolují špatně, proto prostě bez shaderu (for now at least)
                zBuffer.drawWithTest(x, y, z, new Col(color));
                z += zStep;
                color = color.add(colStep);
            }
        }

    }
}