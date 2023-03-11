package raster;

import transforms.Col;

public class ZBuffer {

    private final ImageBuffer imageBuffer;
    private final DepthBuffer depthBuffer;

    public ZBuffer(ImageBuffer imageBuffer){
        this.imageBuffer = imageBuffer;
        this.depthBuffer = new DepthBuffer(imageBuffer.getWidth(), imageBuffer.getHeight());
        depthBuffer.clear();
    }

    public void drawWithTest(int x, int y, double z, Col color){
        //načtu staré Z z depthbufferu
        double old = depthBuffer.getValue(x, y);
        //porovnam nove Z a staré Z
        //pokud nové Z bude menší než staré Z, tak updaatuju Z
        if (z < old){
            depthBuffer.setValue(x,y,z);
            //obarvím pixel pokud předesli krok AOK
            imageBuffer.setValue(x,y,color);
        }

    }

    public DepthBuffer getDepthBuffer() {
        return depthBuffer;
    }

    public ImageBuffer getImageBuffer() {
        return imageBuffer;
    }

}
