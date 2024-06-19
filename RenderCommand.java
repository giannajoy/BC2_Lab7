/*
 * Bootcamp 2 Code
 * CPSC 5002, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
package gee_lab7_local;

/**
 * L-system rendering commands.
 * We need two forwards to support various rewrite rules that want to 
 * recurse differently on some forward segments.
 * Similarly, we need an IGNORE to support a type of recursion in some 
 * L-systems.
 * The push and pop are ways of remembering and restoring the pen during
 * the drawing sequence.
 * @author bc2code
 */
public enum RenderCommand {
    FORWARD,    // 'F' in traditional notation
    FORWARD2,   // 'R' in traditional notation
    IGNORE,     // 'X' in traditional notation
    RIGHT,      // '-' in traditional notation
    LEFT,       // '+' in traditional notation
    PUSH,       // '[' in traditional notation
    POP         // ']' in traditional notation
}
