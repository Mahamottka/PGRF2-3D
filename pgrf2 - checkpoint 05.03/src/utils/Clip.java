package utils;

import model.Vertex;

/***
 *Testuje vertex, ořezání pro kompletně mimo obrazovku
 */

public class Clip {
    /***
     * Ořezání zobrazovacím objemem
     * @param vertex
     * @return Jestli se vertex vešel do okna nebo ne
     */

    public static boolean testVertex(Vertex vertex){
        double x = vertex.getX();
        double y = vertex.getY();
        double z = vertex.getZ();
        double w = vertex.getW();
        return (-w <= x && x <= w) && (-w <= y && y <= w) && (0 <= z && z <= w); //true == ok
    }

    /***
     * Kolik se mi těch vertexů nevejde
     * @param vertexes
     * @return počet vertexů
     */
    public static int testMultipleVertex(Vertex... vertexes){
        int test = 0;
        for (Vertex vertex: vertexes) {
            if (!testVertex(vertex)){
                test++;

            }
        }
        return test;
    }
}
