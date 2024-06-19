/*
 * Bootcamp 2 Code
 * CPSC 5002, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
package gee_lab7_local;

import javax.swing.*;

/**
 * This lab draws a bunch of L-systems:
 * <ul>
 * <li> Koch Snowflake
 * <li> A symmetrical plant
 * <li> Fass Curve
 * <li> An unsymmetrical plant
 * </ul>
 * This lab makes extensive use of stacks and queues.
 * @author bc2code
 */
public class Lab7 {
    public static void main(String[] args) throws InterruptedException {
                // construct the race course (a JPanel that paints the frogs)
        JFrame application = new JFrame();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Canvas canvas = new Canvas();
        application.add(canvas);
        application.setSize(800, 600);        
        application.setVisible(true);
        
        // TEST
        int forward = 50; // forward steps are 50 pixels
        int turn = 25; // turns are 25 degrees
        int x = 400; // start at (400,500)
        int y = 500;
        int heading = -90; // start pointing up
        canvas.setWord(getTestWord(), forward, turn, x, y, heading);
        application.repaint();
        Thread.sleep(200);
        
        canvas.setWord(getKochSnowflake(4), 5,
                60, 200, 200, 60);
        application.repaint();
        Thread.sleep(1000);
        
        canvas.setWord(getPlant1(7), 2,
                25, 300, 550, -90);
        application.repaint();
        Thread.sleep(1000);
        
        canvas.setWord(getFassCurve(4), 8,
                60, 500, 150, -120);
        application.repaint();
        Thread.sleep(1000);

        canvas.setWord(getPlant2(6), 3,
                22, 250, 590, -80);
        application.repaint();
        Thread.sleep(1000);
    }

    /**
     * Make a Koch Snowflake L-system.
     * @param numGenerations number of rewrite generations
     * @return the word for rendering
     */
    private static RenderQueue getKochSnowflake(int numGenerations) {
        // rule: F --> F-F++F-F
        RenderCommand[] ruleFrom = { RenderCommand.FORWARD };
        RenderQueue[] ruleTo = { RenderQueue.fromString("F-F++F-F") };
        Rewriter rewriter = new Rewriter(ruleFrom, ruleTo);
        
        RenderQueue seed = RenderQueue.fromString("F++F++F");
        return rewriter.rewrite(seed, numGenerations);
    }

    /**
     * Make a symmetrical plant L-system.
     * @param numGenerations number of rewrite generations
     * @return the word for rendering
     */
    private static RenderQueue getPlant1(int numGenerations) {
        // rules: X --> F[+X][-X]FX, F --> FF
        RenderCommand[] rulesFrom = { RenderCommand.IGNORE,
                                      RenderCommand.FORWARD };
        RenderQueue[] rulesTo = { RenderQueue.fromString("F[+X][-X]FX"),
                                  RenderQueue.fromString("FF") };
        Rewriter rewriter = new Rewriter(rulesFrom, rulesTo);
        
        RenderQueue seed = RenderQueue.fromString("X");
        return rewriter.rewrite(seed, numGenerations);
    }
    
    /**
     * Make a Fass Curve L-system.
     * @param numGenerations number of rewrite generations
     * @return the word for rendering
     */
    private static RenderQueue getFassCurve(int numGenerations) {
        // rules: F --> F+R++R-F--FF-R+, R --> -F+RR++R+F--F-R
        RenderCommand[] rulesFrom = { RenderCommand.FORWARD,
                                      RenderCommand.FORWARD2 };
        RenderQueue[] rulesTo = { RenderQueue.fromString("F+R++R-F--FF-R+"),
                                  RenderQueue.fromString("-F+RR++R+F--F-R") };
        Rewriter rewriter = new Rewriter(rulesFrom, rulesTo);
        
        RenderQueue seed = RenderQueue.fromString("F");
        return rewriter.rewrite(seed, numGenerations);
    }
    
    /**
     * Make an unsymmetrical plant L-system.
     * @param numGenerations number of rewrite generations
     * @return the word for rendering
     */
    private static RenderQueue getPlant2(int numGenerations) {
        // rules: X --> F-[[X]+X]+F[+FX]-X, F --> FF
        RenderCommand[] rulesFrom = { RenderCommand.IGNORE,
                                      RenderCommand.FORWARD };
        RenderQueue[] rulesTo = { RenderQueue.fromString("F-[[X]+X]+F[+FX]-X"),
                                  RenderQueue.fromString("FF") };
        Rewriter rewriter = new Rewriter(rulesFrom, rulesTo);
        
        RenderQueue seed = RenderQueue.fromString("X");
        return rewriter.rewrite(seed, numGenerations);
    }
    
    /**
     * Make a test pattern rendering word.
     * @return the word for rendering
     */
    private static RenderQueue getTestWord() {
        RenderQueue word = new RenderQueue();
        word.enqueue(RenderCommand.PUSH); // remember starting point
        word.enqueue(RenderCommand.FORWARD);
        word.enqueue(RenderCommand.LEFT);
        word.enqueue(RenderCommand.FORWARD2); // same as FORWARD
        word.enqueue(RenderCommand.POP); // return to starting point
        word.enqueue(RenderCommand.RIGHT);
        word.enqueue(RenderCommand.FORWARD);
        word.enqueue(RenderCommand.IGNORE); // does nothing
        return word;
    }
}
