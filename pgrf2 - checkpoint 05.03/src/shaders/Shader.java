package shaders;

import model.Vertex;
import transforms.Col;

public interface Shader {

    Col shade(Vertex v);
    //shader constant color, shader interpolated color, constant bude vracet natvrdo nejakou barvu,
    //ten druhej bude vracet tu druhou barvu v závislosti na vertexech


}
