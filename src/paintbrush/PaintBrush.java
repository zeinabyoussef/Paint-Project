package paintbrush;

import javax.swing.*;
import java.awt.*;

public class PaintBrush {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Paint Brush");
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        DrawingPanel drawingPanel = new DrawingPanel();
        frame.add(drawingPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setPreferredSize(new Dimension(180, 700));
        controlPanel.setBorder(BorderFactory.createTitledBorder("Controls"));

        // ===== Colors =====
        JPanel colorPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        colorPanel.setBorder(BorderFactory.createTitledBorder("Colors"));

        JButton red = new JButton("Red");
        JButton green = new JButton("Green");
        JButton blue = new JButton("Blue");

        red.setBackground(Color.RED);
        green.setBackground(Color.GREEN);
        blue.setBackground(Color.BLUE);
        blue.setForeground(Color.WHITE);

        colorPanel.add(red);
        colorPanel.add(green);
        colorPanel.add(blue);

        // ===== Shapes =====
        JPanel shapePanel = new JPanel(new GridLayout(5, 1, 5, 5));
        shapePanel.setBorder(BorderFactory.createTitledBorder("Shapes"));

        JButton rect = new JButton("Rectangle");
        JButton oval = new JButton("Oval");
        JButton line = new JButton("Line");
        JButton free = new JButton("Free Hand");
        JButton eraser = new JButton("Eraser");

        shapePanel.add(rect);
        shapePanel.add(oval);
        shapePanel.add(line);
        shapePanel.add(free);
        shapePanel.add(eraser);

        // ===== Options =====
        JPanel optionsPanel = new JPanel(new GridLayout(2, 1));
        optionsPanel.setBorder(BorderFactory.createTitledBorder("Options"));

        JCheckBox dotted = new JCheckBox("Dotted");
        JCheckBox filled = new JCheckBox("Filled");

        optionsPanel.add(dotted);
        optionsPanel.add(filled);

        // ===== Actions =====
        JPanel actionPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        actionPanel.setBorder(BorderFactory.createTitledBorder("Actions"));

        JButton undo = new JButton("Undo");
        JButton clear = new JButton("Clear All");
        JButton save = new JButton("Save");
        JButton open = new JButton("Open");

        actionPanel.add(undo);
        actionPanel.add(clear);
        actionPanel.add(save);
        actionPanel.add(open);

        // ===== Add panels =====
        controlPanel.add(colorPanel);
        controlPanel.add(shapePanel);
        controlPanel.add(optionsPanel);
        controlPanel.add(actionPanel);

        frame.add(controlPanel, BorderLayout.WEST);

        // ===== Actions =====
        red.addActionListener(e -> drawingPanel.setCurrentColor(Color.RED));
        green.addActionListener(e -> drawingPanel.setCurrentColor(Color.GREEN));
        blue.addActionListener(e -> drawingPanel.setCurrentColor(Color.BLUE));

        rect.addActionListener(e -> drawingPanel.setCurrentShapeType("Rectangle"));
        oval.addActionListener(e -> drawingPanel.setCurrentShapeType("Oval"));
        line.addActionListener(e -> drawingPanel.setCurrentShapeType("Line"));
        free.addActionListener(e -> drawingPanel.setCurrentShapeType("Free Hand"));
        eraser.addActionListener(e -> drawingPanel.setEraserMode(true));

        dotted.addActionListener(e -> drawingPanel.setDotted(dotted.isSelected()));
        filled.addActionListener(e -> drawingPanel.setFilled(filled.isSelected()));

        clear.addActionListener(e -> drawingPanel.clearAll());
        undo.addActionListener(e -> drawingPanel.undo());

        save.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                try {
                    drawingPanel.saveImage(chooser.getSelectedFile());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        open.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                try {
                    drawingPanel.openImage(chooser.getSelectedFile());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        frame.setVisible(true);
    }
}
