package Solids;

import model.Part;
import model.Solid;
import model.TopologyType;
import model.Vertex;
import transforms.Col;

public class xAxis extends Solid {

    public xAxis(){

        vertexBuffer.add(new Vertex(0,-1,0,new Col(0xff0000))); //0
        vertexBuffer.add(new Vertex(0, 1,0, new Col(0xff0000))); //1
        vertexBuffer.add(new Vertex(1, -1, 0, new Col(0xff0000))); //2
        vertexBuffer.add(new Vertex(1,1,0,new Col(0xff0000)));  //3
        vertexBuffer.add(new Vertex(2,-1,0,new Col(0xff0000))); //4
        vertexBuffer.add(new Vertex(2,1,0,new Col(0xff0000))); //5
        vertexBuffer.add(new Vertex(3,-1,0,new Col(0xff0000))); //6
        vertexBuffer.add(new Vertex(3,1,0,new Col(0xff0000))); //7

        vertexBuffer.add(new Vertex(3,0,0,new Col(0xff0000))); //8
        vertexBuffer.add(new Vertex(6,0,0,new Col(0xff0000))); //9

        vertexBuffer.add(new Vertex(6,1,0,new Col(0xff0000))); //10
        vertexBuffer.add(new Vertex(6,-1,0,new Col(0xff0000))); //11
        vertexBuffer.add(new Vertex(7,0,0,new Col(0xff0000))); //12




        addIndices(0,1,2,3,4,5,6,7,
                8,9,
                10,11,12);

        partBuffer.add(new Part(TopologyType.TRIANGLE_STRIP, 0, 6));
        partBuffer.add(new Part(TopologyType.LINE, 8, 1));
        partBuffer.add(new Part(TopologyType.TRIANGLE, 10, 1));





    }

}
