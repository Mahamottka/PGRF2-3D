/*package rasterizers;

import raster.ZBuffer;
import transforms.Col;
import transforms.Vec3D;
import utils.Lerp;

public class Temp {
    private final Lerp lerp;
    private int width;
    private int height;

    public Temp() {
        lerp = new Lerp();
    }


    public void rasterize(Vec3D v1, Vec3D v2, Vec3D v3, ZBuffer zbuffer) {


        Vec3D help;
        if (v3.getGetY() < v1.getGetY()) {
            help = v3;
            v3 = v1;
            v1 = help;
        }
        if (v2.getGetY() < v1.getGetY()) {
            help = v2;
            v2 = v1;
            v1 = help;
        }
        if (v3.getGetY() < v2.getGetY()) {
            help = v3;
            v3 = v2;
            v2 = help;
        }
        for (int y = (int) v1.getGetY()+1; y < v2.getGetY(); y++) {
            //interpolacni koeficient pro hranu AB
            double t1 = (y - v1.getGetY()) / (v2.getGetY() - v1.getGetY());
            //int x1 = (int) ((1 - t1) * a.getX() + b.getX() * t1);
            //double z1 = ((1.0 - t1) * a.getZ() + b.getZ() * t1);
            Vec3D vab = lerp.lerp(v1, v2, t1);

            //interpolacni koeficient pro hranu AC
            double t2 = (y - v1.getGetY()) / (v3.getGetY() - v1.getGetY());
            //int x2 = (int) ((1 - t2) * a.getX() + c.getX() * t2);
            //double z2 = ((1.0 - t2) * a.getZ() + c.getZ() * t2);
            Vec3D vac = lerp.lerp(v1, v3, t2);

            if (vab.getX() > vac.getX()) {
                help = vab;
                vab = vac;
                vac = help;

            }
            for (int x = (int) vab.getX()+1; x < vac.getX(); x++) {
                double t = (double) (x - vab.getX()) / (double) (vac.getX() - vab.getX());
                // double z = z1 * (1.0 - t) + z2 * t;
                Vec3D z = lerp.lerp(vab, vac, t);
                zbuffer.drawWithTest(x, y, z.getZ(), new Col(0x00ff00));
            }

        }
        for (int y = (int) v2.getGetY()+1; y < v3.getGetY(); y++) {
            double t1 = (y - v2.getGetY()) / (v3.getGetY() - v2.getGetY());
            //int x1 = (int) ((1 - t1) * b.getX() + c.getX() * t1);
            //double z1 = ((1.0 - t1) * b.getZ() + c.getZ() * t1);
            Vec3D vbc = lerp.lerp(v2, v3, t1);

            double t2 = (y - v1.getGetY()) / (v3.getGetY() - v1.getGetY());
            //int x2 = (int) ((1 - t2) * a.getX() + c.getX() * t2);
            //double z2 = ((1.0 - t2) * a.getZ() + c.getZ() * t2);
            Vec3D vac = lerp.lerp(v1, v3, t2);


            if (vbc.getX() > vac.getX()) {
                help = vbc;
                vbc = vac;
                vac = help;
            }

            for (int x = (int) vbc.getX()+1; x < vac.getX(); x++) {
                double t = (double) (x - vbc.getX()) / (double) (vac.getX() - vbc.getX());
                //double z = z1 * (1.0 - t) + z2 * t;
                Vec3D z = lerp.lerp(vbc, vac, t);
                zbuffer.drawWithTest(x, y, z.getZ(), new Col(0x00ff00));
            }
        }

    }

}
*/