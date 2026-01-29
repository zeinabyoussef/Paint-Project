package paintbrush;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class PaintBrush {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Paint Brush");
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        DrawingPanel drawingPanel = new DrawingPanel();
        frame.add(drawingPanel, BorderLayout.CENTER);

        JPanel Panel = new JPanel();

        JButton rectButton = new JButton("Rectangle");
        JButton ovalButton = new JButton("Oval");
        JButton lineButton = new JButton("Line");
        JButton freeHandButton = new JButton("Free Hand");
        JButton clearButton = new JButton("Clear All");
        JCheckBox dottedCheck = new JCheckBox("Dotted");
        JCheckBox filledCheck = new JCheckBox("Filled");

        Panel.add(rectButton);
        Panel.add(ovalButton);
        Panel.add(lineButton);
        Panel.add(freeHandButton);
        Panel.add(clearButton);
        Panel.add(dottedCheck);
        Panel.add(filledCheck);

        frame.add(Panel, BorderLayout.NORTH);


        rectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPanel.setCurrentShapeType("Rectangle");
            }
        });

        ovalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPanel.setCurrentShapeType("Oval");
            }
        });

        lineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPanel.setCurrentShapeType("Line");
            }
        });

        freeHandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPanel.setCurrentShapeType("Free Hand");
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPanel.clearAll();
            }
        });

        dottedCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPanel.setDotted(dottedCheck.isSelected());
            }
        });

        filledCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPanel.setFilled(filledCheck.isSelected());
            }
        });


        frame.setVisible(true);
    }
}
