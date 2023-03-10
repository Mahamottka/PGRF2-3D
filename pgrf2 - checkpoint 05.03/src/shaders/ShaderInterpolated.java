package shaders;

import model.Vertex;
import transforms.Col;

/***
 * Vraci barvu v závislosti na vertexu
 */
public class ShaderInterpolated implements Shader {
    @Override
    public Col shade(Vertex v) {
        return v.getColor().mul(1/v.getOne());
    }
}
