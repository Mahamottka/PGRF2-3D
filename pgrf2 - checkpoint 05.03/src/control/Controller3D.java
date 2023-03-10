package control;

import Solids.Arrow;
import model.Solid;
import raster.ImageBuffer;
import raster.ZBuffer;
import render.Renderer;
import shaders.Shader;
import transforms.Col;
import rasterizers.TriangleRasterizer;
import view.Panel;

import java.awt.event.*;

public class Controller3D implements Controller {
    private final Panel panel;
    private final ZBuffer zBuffer;
    private Renderer renderer;

    private final TriangleRasterizer triangleRasterizer;

    public Controller3D(Panel panel) {
        this.panel = panel;
        this.zBuffer = new ZBuffer(panel.getRaster());
        this.triangleRasterizer = new TriangleRasterizer(zBuffer);
        Col color = new Col(0xff0000);


        Shader blueShader = y ->{
            return new Col(0x0000ff);
        };
        triangleRasterizer.setShader(blueShader);
        initObjects(panel.getRaster());
        initListeners();
        redraw();
    }

    public void initObjects(ImageBuffer raster) {
        raster.setClearValue(new Col(0x101010));
    }

    @Override
    public void initListeners() {
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                panel.resize();
                initObjects(panel.getRaster());
            }
        });
    }

    private void redraw() {
        panel.clear();

     //   zBuffer.drawWithTest(10,10,0.5, new Col(0x00ff00));
     //  zBuffer.drawWithTest(10,10,0.7, new Col(0xff0000));

      //  triangleRasterizer.rasterize(
        //        new Point3D(1,1,0.3),
          //      new Point3D(-1,0,0.3),
            //    new Point3D(0,-1,0.3)
        //);


        panel.repaint();
    }
}
