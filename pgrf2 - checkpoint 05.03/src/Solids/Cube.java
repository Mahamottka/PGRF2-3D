package Solids;

import model.Part;
import model.Solid;
import model.TopologyType;
import model.Vertex;
import transforms.Col;

public class Cube extends Solid {

    public Cube(){
        //vytvorit celou cube
        getVertexBuffer().add(new Vertex(10,10,10, new Col(0xff0000))); //v0
        getVertexBuffer().add(new Vertex(10,20,10, new Col(0x00ff00))); //v1
        getVertexBuffer().add(new Vertex(20,10,10, new Col(0x0000ff))); //v2
        getVertexBuffer().add(new Vertex(20,20,10, new Col(0xff0000))); //v3

        getVertexBuffer().add(new Vertex(10,10,20, new Col(0xff0000))); //v0
        getVertexBuffer().add(new Vertex(10,20,20, new Col(0x00ff00))); //v1
        getVertexBuffer().add(new Vertex(20,10,20, new Col(0x0000ff))); //v2
        getVertexBuffer().add(new Vertex(20,20,20, new Col(0xff0000))); //v3

        getVertexBuffer().add(new Vertex(10,10,10, new Col(0xff0000))); //v0
        getVertexBuffer().add(new Vertex(10,20,10, new Col(0x00ff00))); //v1
        getVertexBuffer().add(new Vertex(20,10,10, new Col(0x0000ff))); //v2
        getVertexBuffer().add(new Vertex(20,20,10, new Col(0xff0000))); //v3

        getVertexBuffer().add(new Vertex(10,10,10, new Col(0xff0000))); //v0
        getVertexBuffer().add(new Vertex(10,20,10, new Col(0x00ff00))); //v1
        getVertexBuffer().add(new Vertex(20,10,10, new Col(0x0000ff))); //v2
        getVertexBuffer().add(new Vertex(20,20,10, new Col(0xff0000))); //v3

        getVertexBuffer().add(new Vertex(10,10,10, new Col(0xff0000))); //v0
        getVertexBuffer().add(new Vertex(10,20,10, new Col(0x00ff00))); //v1
        getVertexBuffer().add(new Vertex(20,10,10, new Col(0x0000ff))); //v2
        getVertexBuffer().add(new Vertex(20,20,10, new Col(0xff0000))); //v3

        getVertexBuffer().add(new Vertex(10,10,10, new Col(0xff0000))); //v0
        getVertexBuffer().add(new Vertex(10,20,10, new Col(0x00ff00))); //v1
        getVertexBuffer().add(new Vertex(20,10,10, new Col(0x0000ff))); //v2
        getVertexBuffer().add(new Vertex(20,20,10, new Col(0xff0000))); //v3

        getIndexBuffer().add(0);
        getIndexBuffer().add(1);
        getIndexBuffer().add(2);

        getIndexBuffer().add(1);
        getIndexBuffer().add(2);
        getIndexBuffer().add(3);

        getIndexBuffer().add(4);
        getIndexBuffer().add(5);
        getIndexBuffer().add(6);

        getIndexBuffer().add(5);
        getIndexBuffer().add(6);
        getIndexBuffer().add(7);


        getPartBuffer().add(new Part(TopologyType.TRIANGLE,0,4));

    }

}
