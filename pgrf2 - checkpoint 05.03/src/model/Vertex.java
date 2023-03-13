package model;

import transforms.*;

public class Vertex implements Vectorizable<Vertex>{

    private final Point3D position;
    private Col color;
    private Mat4 model = new Mat4Identity();

    //TODO souradnice do textury - toto je pro textury
    private Vec2D u,v;

    //TODO normala - toto je pro textury
    private Vec3D nx,ny,nz;

    private double one;




    public Vertex(double x, double y, double z, Col color) {
        this.position = new Point3D(x,y,z);
        this.color = color;
        this.one = 1;

    }

    public Vertex(Point3D position, Col color){
        this.position = position;
        this.color = color;
    }

    public Vertex(Vec3D vec3D, Col color){
        this.position = new Point3D(vec3D.getX(),vec3D.getGetY(),vec3D.getZ());
        this.color = color;
    }

    public double getOne() {
        return one;
    }

    @Override
    public Vertex mul(double k) { //nesmíme zapomenout na "one" TODO - pro textury
        return new Vertex(position.getX() * k, position.getY() * k, position.getZ() * k, color);
    }

    public Vertex mul(Mat4 trans){
        return new Vertex(position.mul(trans), color);
    }

    @Override
    public Vertex add(Vertex vertex) { //nesmíme zapomenout na "one"
        return new Vertex(position.add(vertex.getPosition()), color);
    }


    public double getX(){
        return position.getX();
    }

    public double getW(){
        return position.getW();
    }

    public double getY(){
        return position.getY();
    }

    public double getZ(){
        return position.getZ();
    }
    public Point3D getPosition() {
        return position;
    }
    public Col getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "vertex " + position;
    }

    public Vertex transform(Mat4 trans, Mat4 view, Mat4 projection) {
        Vertex v;
        v = this.mul(trans);
        v = v.mul(view);
        return v.mul(projection);
    }

    public Vertex dehomog(){
        return this.mul(1 / this.getPosition().getW());
    }

    public Vec3D toVec3D(){
        return new Vec3D(position.getX(), position.getY(),position.getZ());
    }

    public Vec3D transformToWindow(int width, int height) {
        return this.toVec3D()
                .mul(new Vec3D(1, -1, 1))
                .add(new Vec3D(1, 1, 0))
                .mul(new Vec3D(
                        (width - 1) / 2.,
                        (height - 1) / 2.,
                        1));
    }

    public Mat4 getModel() {
        return model;
    }

    public Mat4 setModel(){
        return model;
    }
}
