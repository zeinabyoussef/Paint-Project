package paintbrush;

import java.awt.*;

public class Shape {
    private String type;
    private int startX, startY, endX, endY;
    private boolean dotted;
    private boolean filled;


    public Shape(String type, int startX, int startY, boolean dotted, boolean filled) {
        this.type = type;
        this.startX = startX;
        this.startY = startY;
        this.dotted = dotted;
        this.filled = filled;
        this.endX = startX;
        this.endY = startY;
    }

    public void setEnd(int x, int y) {
        this.endX = x;
        this.endY = y;
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;


        if (dotted) {
            float[] dash = {5f, 5f};
            g2.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, dash, 0.0f));
        } else {
            g2.setStroke(new BasicStroke(2));
        }

        int x = Math.min(startX, endX);
        int y = Math.min(startY, endY);
        int width = Math.abs(endX - startX);
        int height = Math.abs(endY - startY);

        switch (type) {
            case "Rectangle":
                if (filled)
                {
                    g2.fillRect(x, y, width, height);
                }
                else
                {
                    g2.drawRect(x, y, width, height);
                }
                break;
            case "Oval":
                if (filled)
                {
                    g2.fillOval(x, y, width, height);
                }
                else
                {
                    g2.drawOval(x, y, width, height);
                }
                break;
            case "Line":
                g2.drawLine(startX, startY, endX, endY);
                break;
            case "Free Hand":
                g2.drawLine(startX, startY, endX, endY);
                break;
        }
    }
}
