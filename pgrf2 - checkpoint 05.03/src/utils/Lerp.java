package utils;

import model.Vertex;
import transforms.Vec3D;

/**
* Děla interpolaci vectoru
*/
public class Lerp {

    public Vec3D lerp(Vec3D v1,Vec3D v2, double skalar){
        //int x2 = (int)((1- t) * v1 + t * v2); potreba pouzit mull a add (aka to co jsem mel dopsat xD)
        //myslím si, že ten skalar je místo toho T

        Vec3D v = v1.mul(1-skalar).add(v2.mul(skalar));
        return v;
    }

    public Vertex lerp(Vertex v1, Vertex v2, double skalar){
        //int x2 = (int)((1- t) * v1 + t * v2); potreba pouzit mull a add (aka to co jsem mel dopsat xD)
        //myslím si, že ten skalar je místo toho T

        Vertex v = v1.mul(1-skalar).add(v2.mul(skalar));
        return v;
    }

}
