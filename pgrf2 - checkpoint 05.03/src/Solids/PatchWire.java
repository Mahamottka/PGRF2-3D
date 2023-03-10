package Solids;


import model.Solid;


public class PatchWire extends Solid {

    public PatchWire (){
        //TODO: řídící body (jako u CurveWire), bude jich 16

        //TODO: instance
        //Bicubic bicubic = new Bicubic();

        double hundered = 100.;
        for (int i = 0; i <= 100; i++){
            double t = i / hundered;
            for (int j = 0; j <= 100; j++){
                double u = j / hundered;

                //Bicubic.compute(u, t);
            }
        }

    }

}
