package control;

import Solids.Cube;
import Solids.Triangle;
import model.Scene;
import model.Solid;
import raster.ImageBuffer;
import raster.ZBuffer;
import rasterizers.Rasterizer;
import render.Renderer;
import shaders.Shader;
import transforms.*;
import view.Panel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Controller3D implements Controller {
    private final Panel panel;
    private int ox, oy;
    private final ZBuffer zBuffer;
    private final Scene scene;
    private double cameraSpeed = 10;
    private final Renderer renderer;
    private Camera camera;
    private final Rasterizer rasterizer;

    private int selector = -1;

    public Controller3D(Panel panel) {
        this.panel = panel;
        this.zBuffer = new ZBuffer(panel.getRaster());
        this.rasterizer = new Rasterizer(zBuffer);
        this.scene = new Scene();

        init();

        //view
        camera = new Camera(
                new Vec3D(100, -100, 50), //pokud chci 3rd person, nastavit x a y a z na nulu
                Math.toRadians(90),  //azimut
                Math.toRadians(0),  //zenith
                1, true     //pro 3rd person, nastavit na false a 1 na 4 (třeba)
        );


        Mat4OrthoRH projekceOrtho = new Mat4OrthoRH(
                zBuffer.getImageBuffer().getWidth() / 4.,
                zBuffer.getImageBuffer().getHeight() / 4.,
                0.1,
                200
        );

        Mat4PerspRH projekcePersp = new Mat4PerspRH(
                Math.toRadians(60),
                zBuffer.getImageBuffer().getHeight() / (double) zBuffer.getImageBuffer().getWidth(),
                0.1,
                1000
        );
        Shader blueShader = y -> {
            return new Col(0x0000ff);
        };

        renderer = new Renderer(rasterizer, camera, projekcePersp);


        initObjects(panel.getRaster());
        initListeners();
        redraw();
    }
    public void init(){
        //když nebude fungovat tak najebat mimo metodu
        Solid triangle = new Triangle();
        scene.addSolid(triangle);

        Solid cube = new Cube();
        scene.addSolid(cube);
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

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                boolean update = false;
                if (e.getKeyCode() == KeyEvent.VK_W){
                    camera = camera.forward(cameraSpeed);
                    update = true;}
                if (e.getKeyCode() == KeyEvent.VK_S){
                    camera = camera.backward(cameraSpeed);
                    update = true;}
                if (e.getKeyCode() == KeyEvent.VK_A){
                    camera = camera.left(cameraSpeed);
                    update = true;}
                if (e.getKeyCode() == KeyEvent.VK_D){
                    camera = camera.right(cameraSpeed);
                    update = true;}



                //Selecting object //TODO dodelat scene.getSolid(selector).getColor??
                if (e.getKeyCode() == KeyEvent.VK_PAGE_UP) {
                    if (selector >= 0){
                        scene.getSolid(selector);}
                    selector += 1;
                    if (selector >= scene.getSize())
                        selector = scene.getSize()-1;
                    scene.getSolid(selector);
                    update = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
                    if (selector >= 0){
                        scene.getSolid(selector);}
                    selector -= 1;
                    if (selector < 0)
                        selector = 0;
                    scene.getSolid(selector);
                    update = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    if (selector >= 0){
                        scene.getSolid(selector);}
                    selector = -1;
                    update = true;
                }
                if (selector >= 0){
                   //Prostor pro translace
                }

                if (update){
                    redraw();
                }
            }
        });

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ox = e.getX();
                oy = e.getY();
            }
        });
        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);

                int dx = ox - e.getX();
                int dy = oy - e.getY();
                double azimuth = dx / 1000.;
                double zenizh = dy / 1000.;

                camera =  camera.withAzimuth(camera.getAzimuth() + azimuth);
                camera = camera.withZenith(camera.getZenith() + zenizh);

                ox = e.getX();
                oy = e.getY();

                redraw(); //překresluje obrazovku
            }
        });
    }

    private void redraw() {
        renderer.setCamera(camera);
        panel.clear();
        zBuffer.getDepthBuffer().clear();
        scene.draw(renderer);
        panel.repaint();
    }
}
