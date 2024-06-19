/*
 * Bootcamp 2 Code
 * CPSC 5002, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
package gee_lab7_local;

import javax.swing.*;
import java.awt.*;

/**
 * Render a RenderQueue word to a panel.
 * @author bc2code
 */
public class Canvas extends JPanel {
    private RenderQueue word;
    private RenderPoint pen;
    private int forward;
    private int turn;

    public Canvas() {
        this.word = new RenderQueue();
        this.pen = new RenderPoint();
        this.forward = 8;
        this.turn = 25;
    }

    public void setWord(RenderQueue word, int forwardPixels, int turnDegrees,
                  int startX, int startY, int startHeading) {
        this.word = word;
        this.pen = new RenderPoint(startX, startY, startHeading);
        this.forward = forwardPixels;
        this.turn = turnDegrees;
    }

    public void setWord(RenderQueue word) {
        this.word = word;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // calls the JPanel paintComponent first
        drawWord(g);
    }

    private void drawWord(Graphics g) {
        System.out.println(this.word.toString());
        RenderStack stack = new RenderStack();
        RenderQueue word = this.word.copy();
        RenderPoint pen = this.pen.copy();
        while (!word.empty()) {
            //System.out.println(pen);
            switch(word.dequeue()) {
                case FORWARD:
                case FORWARD2:
                    int x = pen.getX();
                    int y = pen.getY();
                    pen.move(forward);
                    g.drawLine(x, y, (int)pen.getX(), (int)pen.getY());
                    break;

                case RIGHT:
                    pen.rotate(turn);
                    break;

                case LEFT:
                    pen.rotate(-turn);
                    break;

                case PUSH:
                    stack.push(pen);
                    break;

                case POP:
                    pen = stack.pop();
                    break;

                case IGNORE:
                default:
                    break;
            }
        }
    }

}
