package paintbrush;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class DrawingPanel extends JPanel {
    private int lastX, lastY; //for store lasr point to draw free hand
    private ArrayList<Shape> shapes = new ArrayList<>();
    private Shape currentShape;
    private String currentShapeType;
    private boolean dotted = false;
    private boolean filled = false;
    private Color currentColor = Color.BLACK;
    private boolean eraserMode = false;
    private Image backgroundImage;
    private boolean freeHandStarted = false;

    public DrawingPanel() {
        setBackground(Color.WHITE);
        MouseAdapter mouseHandler = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

                if (currentShapeType.equals(currentShapeType.equals("Free Hand") || eraserMode)) {
                    lastX = e.getX();
                    lastY = e.getY();
                    freeHandStarted = true;
                }
                else {

                    currentShape = new Shape(
                            currentShapeType,
                            e.getX(),
                            e.getY(),
                            dotted,
                            filled,
                            currentColor
                    );
                    shapes.add(currentShape);
                }
                repaint();
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                freeHandStarted = false;
            }

            public void mouseDragged(MouseEvent e) {

                if (currentShapeType.equals("Free Hand") || eraserMode)
                {
                    if (!freeHandStarted) {
                        // Safety: if drag happens without a proper press
                        lastX = e.getX();
                        lastY = e.getY();
                        freeHandStarted = true;
                        return;
                    }
                    Color drawColor = eraserMode ? Color.WHITE : currentColor;
                    Shape segment = new Shape(
                            "Line",
                            lastX,
                            lastY,
                            false,
                            false,
                            drawColor
                    );
                    segment.setEnd(e.getX(), e.getY());
                    shapes.add(segment);

                    lastX = e.getX();
                    lastY = e.getY();

                    repaint();

                }
                else {
                    currentShape.setEnd(e.getX(), e.getY());
                    repaint();
                }

            }
        };


        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    public void undo() {
        if (!shapes.isEmpty()) {
            shapes.remove(shapes.size() - 1);
            repaint();
        }
    }
    public void saveImage(File file) throws Exception {
        BufferedImage img = new BufferedImage(
                getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB
        );
        Graphics2D g2 = img.createGraphics();
        paint(g2);
        g2.dispose();
        ImageIO.write(img, "png", file);
    }

    public void openImage(File file) throws Exception {
        backgroundImage = ImageIO.read(file);
        repaint();
    }
    public void setEraserMode(boolean eraser) {
        eraserMode = eraser;
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this);
        }
        for (Shape shape : shapes) {
            shape.draw(g);
        }

    }

    public void setCurrentColor(Color color) {
        this.currentColor = color;
        eraserMode = false;
    }


    public void setCurrentShapeType(String type)
    {
        currentShapeType = type;
        eraserMode = false;
    }

    public void setDotted(boolean dotted)
    {
        this.dotted = dotted;
    }
    public void setFilled(boolean filled)
    {
        this.filled = filled;
    }
    public void clearAll()
    {
        shapes.clear();
        repaint();
    }
}

