package Solids;

import model.Part;
import model.Solid;
import model.TopologyType;
import model.Vertex;
import transforms.Col;

public class Cube extends Solid {

    public Cube(){
        //vytvorit celou cube
        getVertexBuffer().add(new Vertex(10,10,10, new Col(0xff0000)));
        getVertexBuffer().add(new Vertex(10,20,10, new Col(0x00ff00)));
        getVertexBuffer().add(new Vertex(20,10,10, new Col(0x0000ff)));
        getVertexBuffer().add(new Vertex(20,20,10, new Col(0xff0000)));

        getVertexBuffer().add(new Vertex(10,10,20, new Col(0xff0000)));
        getVertexBuffer().add(new Vertex(10,20,20, new Col(0x00ff00)));
        getVertexBuffer().add(new Vertex(20,10,20, new Col(0x0000ff)));
        getVertexBuffer().add(new Vertex(20,20,20, new Col(0xff0000)));

        getVertexBuffer().add(new Vertex(10,10,10, new Col(0xff0000)));
        getVertexBuffer().add(new Vertex(10,20,10, new Col(0x00ff00)));
        getVertexBuffer().add(new Vertex(10,10,20, new Col(0x0000ff)));
        getVertexBuffer().add(new Vertex(10,20,20, new Col(0xff0000)));

        getVertexBuffer().add(new Vertex(20,10,10, new Col(0xff0000)));
        getVertexBuffer().add(new Vertex(20,20,10, new Col(0x00ff00)));
        getVertexBuffer().add(new Vertex(20,10,20, new Col(0x0000ff)));
        getVertexBuffer().add(new Vertex(20,20,20, new Col(0xff0000)));

        getVertexBuffer().add(new Vertex(10,10,10, new Col(0xff0000)));
        getVertexBuffer().add(new Vertex(20,10,10, new Col(0x00ff00)));
        getVertexBuffer().add(new Vertex(10,10,20, new Col(0x0000ff)));
        getVertexBuffer().add(new Vertex(20,10,20, new Col(0xff0000)));

        getVertexBuffer().add(new Vertex(10,20,10, new Col(0xff0000)));
        getVertexBuffer().add(new Vertex(20,20,10, new Col(0x00ff00)));
        getVertexBuffer().add(new Vertex(20,20,20, new Col(0x0000ff)));
        getVertexBuffer().add(new Vertex(10,20,20, new Col(0xff0000)));

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

        getIndexBuffer().add(8);
        getIndexBuffer().add(9);
        getIndexBuffer().add(10);
        getIndexBuffer().add(9);
        getIndexBuffer().add(10);
        getIndexBuffer().add(11);

        getIndexBuffer().add(12);
        getIndexBuffer().add(13);
        getIndexBuffer().add(14);
        getIndexBuffer().add(13);
        getIndexBuffer().add(14);
        getIndexBuffer().add(15);

        getIndexBuffer().add(16);
        getIndexBuffer().add(17);
        getIndexBuffer().add(18);
        getIndexBuffer().add(17);
        getIndexBuffer().add(18);
        getIndexBuffer().add(19);

        getIndexBuffer().add(20);
        getIndexBuffer().add(21);
        getIndexBuffer().add(22);
        getIndexBuffer().add(21);
        getIndexBuffer().add(22);
        getIndexBuffer().add(23);

        getPartBuffer().add(new Part(TopologyType.TRIANGLE,0,12));

    }

}
