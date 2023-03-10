package shaders;

import model.Vertex;
import transforms.Col;

/***
 * Natvrdo vraci nejakou barvu
 */
public class ShaderConstant implements Shader {
    @Override
    public Col shade(Vertex v) {
        return new Col(0x00ff00);
    }
}
