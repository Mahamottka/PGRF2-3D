package Solids;

import model.*;
import transforms.Col;

public class yAxis extends Solid {

    public yAxis(){
        vertexBuffer.add(new Vertex(-1,0,0,new Col(0x00ff00))); //0
        vertexBuffer.add(new Vertex(1, 0,0, new Col(0x00ff00))); //1
        vertexBuffer.add(new Vertex(-1, 1, 0, new Col(0x00ff00))); //2
        vertexBuffer.add(new Vertex(1,1,0,new Col(0x00ff00)));  //3
        vertexBuffer.add(new Vertex(-1,2,0,new Col(0x00ff00))); //4
        vertexBuffer.add(new Vertex(1,2,0,new Col(0x00ff00))); //5
        vertexBuffer.add(new Vertex(-1,3,0,new Col(0x00ff00))); //6
        vertexBuffer.add(new Vertex(1,3,0,new Col(0x00ff00))); //7

        vertexBuffer.add(new Vertex(0,3,0,new Col(0x00ff00))); //8
        vertexBuffer.add(new Vertex(0,6,0,new Col(0x00ff00))); //9

        vertexBuffer.add(new Vertex(1,6,0,new Col(0x00ff00))); //10
        vertexBuffer.add(new Vertex(-1,6,0,new Col(0x00ff00))); //11
        vertexBuffer.add(new Vertex(0,7,0,new Col(0x00ff00))); //12




        addIndices(0,1,2,3,4,5,6,7,
                8,9,
                10,11,12);

        partBuffer.add(new Part(TopologyType.TRIANGLE_STRIP, 0, 6));
        partBuffer.add(new Part(TopologyType.LINE, 8, 1));
        partBuffer.add(new Part(TopologyType.TRIANGLE, 10, 1));
    }

}
