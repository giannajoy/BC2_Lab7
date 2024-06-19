/*
 * Bootcamp 2 Code
 * CPSC 5002, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
package gee_lab7_local;

/**
 * Does the rewriting in an L-system grammar. The idea here is that you start 
 * out with an axiom which is just a string. Then you apply rewrite rules, 
 * called productions, to expand some or all of the letters in the axiom to 
 * create a string, called a derived word. You can rewrite the word again 
 * using the same productions to get further generations of words in the 
 * grammar. Here's an example:
 * <ul>
 * <li>axiom  : A
 * <li>rules  : (A → AB), (B → A)
 * </ul>
 * which produces: 
 * <p>AB as the first derived word (n=1) since A is replaced by AB
 * <p>For n=2 we take the AB from generation 1 and apply the production 
 * rules again to get ABA since the A changes to AB and the B changes to A.
 * <p>For n=3, we use the rules to move from ABA to ABAAB.
 * <p>Then for n=4, it becomes ABAABABA.
 * <p>It continues on in this way for as many generations as you like.
 * 
 * This Rewriter though is specific to the RenderCommands that we have
 * defined and it uses our RenderQueue to provide the rules and for the seed
 * and rewrite results.
 * 
 * @author bc2code
 */
public class Rewriter {
    private RenderCommand[] from;
    private RenderQueue[] to;
    
    /**
     * Create an L-system that has the given rules.
     * Example: if we had a one system rule: F --> FF, then from[0] would
     * be RenderCommand.FORWARD and to[0] would be RenderQueue.fromString("FF").
     * @param from the left side of the rules
     * @param to the corresponding right side of the rules
     */
    public Rewriter(RenderCommand[] from, RenderQueue[] to) {
        this.from = from;
        this.to = to;
    }
    
    /**
     * Perform the rewriting for the given number of generations.
     * @param seed starting word
     * @param numGenerations how many times to rewrite the word with our rules
     * @return the nth-generation word
     */
    public RenderQueue rewrite(RenderQueue seed, int numGenerations) {
        // Implementation is just to treat the seed as the "previous" output
        // queue and then move the output queue to be the input queue for
        // the next generation, apply the rules to any axioms for which we
        // have rules (the other axioms are copied over to the output
        // directly). Repeat this process for the given number of generations.
        RenderQueue output = seed.copy();
        RenderQueue input;
        for (int gen = 0; gen < numGenerations; gen++) {
            input = output; // last generation's output is this one's input
            output = new RenderQueue();
            while (!input.empty()) {
                RenderCommand nextInput = input.dequeue();
                // look through the from-list to find a rule to invoke
                boolean ruleFound = false;
                for (int i = 0; !ruleFound && i < from.length; i++)
                    if (from[i] == nextInput) {
                        ruleFound = true;
                        // append a copy of this rule's right side onto the 
                        // end of the output queue
                        output.append(to[i]);
                    }
                // if no rule was found, then just copy this command to output
                if (!ruleFound)
                    output.enqueue(nextInput);
            }
        }    
        return output;
    }
}
