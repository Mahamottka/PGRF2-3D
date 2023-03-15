package Solids;

import model.Part;
import model.Solid;
import model.TopologyType;
import model.Vertex;
import transforms.Col;

public class zAxis extends Solid {

    public zAxis(){

        vertexBuffer.add(new Vertex(-1,-1,0,new Col(0x0000ff))); //0
        vertexBuffer.add(new Vertex(1, 1,0, new Col(0x0000ff))); //1
        vertexBuffer.add(new Vertex(-1, -1, 1, new Col(0x0000ff))); //2
        vertexBuffer.add(new Vertex(1,1,1,new Col(0x0000ff)));  //3
        vertexBuffer.add(new Vertex(-1,-1,2,new Col(0x0000ff))); //4
        vertexBuffer.add(new Vertex(1,1,2,new Col(0x0000ff))); //5
        vertexBuffer.add(new Vertex(-1,-1,3,new Col(0x0000ff))); //6
        vertexBuffer.add(new Vertex(1,1,3,new Col(0x0000ff))); //7

        vertexBuffer.add(new Vertex(0,0,3,new Col(0x0000ff))); //8
        vertexBuffer.add(new Vertex(0,0,6,new Col(0x0000ff))); //9

        vertexBuffer.add(new Vertex(1,1,6,new Col(0x0000ff))); //10
        vertexBuffer.add(new Vertex(-1,-1,6,new Col(0x0000ff))); //11
        vertexBuffer.add(new Vertex(0,0,7,new Col(0x0000ff))); //12




        addIndices(0,1,2,3,4,5,6,7,
                8,9,
                10,11,12);

        partBuffer.add(new Part(TopologyType.TRIANGLE_STRIP, 0, 6));
        partBuffer.add(new Part(TopologyType.LINE, 8, 1));
        partBuffer.add(new Part(TopologyType.TRIANGLE, 10, 1));

    }

}
