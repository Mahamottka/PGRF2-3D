package model;

import render.Renderer;

import java.util.ArrayList;

public class Scene {

    private final ArrayList<Solid> solids = new ArrayList<>();

    public ArrayList<Solid> getSolids() {
        return solids;
    }

    public void addSolid(Solid solid){
        solids.add(solid);
    }

    public Solid getSolid(int index){
        return solids.get(index);
    }

    public void draw(Renderer renderer){
        for (Solid solid: solids) {
            renderer.render(solid);
        }
    }

}
