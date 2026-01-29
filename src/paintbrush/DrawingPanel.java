package paintbrush;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DrawingPanel extends JPanel {
    private int lastX, lastY; //for store lasr point to draw free hand
    private ArrayList<Shape> shapes = new ArrayList<>();
    private Shape currentShape;
    private String currentShapeType;
    private boolean dotted = false;
    private boolean filled = false;
    public DrawingPanel() {
        setBackground(Color.MAGENTA);
        MouseAdapter mouseHandler = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (currentShapeType.equals("Free Hand")) {
                    lastX = e.getX();
                    lastY = e.getY();
                }
                else {

                    currentShape = new Shape(currentShapeType, e.getX(), e.getY(), dotted, filled);
                    shapes.add(currentShape);
                }
                repaint();
            }

            public void mouseDragged(MouseEvent e) {
                if (currentShapeType.equals("Free Hand"))
                {
                    currentShape = new Shape("Line", lastX, lastY, dotted, false);
                    currentShape.setEnd(e.getX(), e.getY());
                    shapes.add(currentShape);

                    lastX = e.getX();
                    lastY = e.getY();
                }
                else {
                    currentShape.setEnd(e.getX(), e.getY());
                }
                repaint();
            }
        };

        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape shape : shapes) {
            shape.draw(g);
        }
    }


    public void setCurrentShapeType(String type)
    {
        currentShapeType = type;
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

