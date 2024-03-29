package model;

import render.Renderer;

import java.util.ArrayList;

public class Scene {

    private final ArrayList<Solid> solids = new ArrayList<>();


    public void addSolid(Solid solid){
        solids.add(solid);
    }

    public Solid getSolid(int index){
        return solids.get(index);
    }

    public int getSize(){
        return solids.size();
    }

    public void draw(Renderer renderer){
        for (Solid solid: solids) {
            renderer.render(solid);
        }
    }

}
