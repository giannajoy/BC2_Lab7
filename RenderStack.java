/*
 * Bootcamp 2 Code
 * CPSC 5002, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
package gee_lab7_local;

import java.util.EmptyStackException;

/**
 * Stack ADT for RenderCommands.
 * @author bc2code
 */
public class RenderStack {
    /**
     * Implementation of the stack is a linked list.
     */
    public class Node {

        public RenderPoint data;    // Stack element
        public Node next;           // Stack element next down.
        
        /**
         * Construct a node in the stack list.
         * @param data
         * @param next 
         */
        public Node(RenderPoint data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
    
    private Node head;  // top of the stack
    
    /**
     * Create a new empty stack.
     */
    public RenderStack() {
        head = null;
    }
    
    /**
     * Pushes an item onto the top of this stack.
     * @param point the item to be pushed onto this stack
     */
    public void push(RenderPoint point) {
        head = new Node(point.copy(), head);
    }
    
    /**
     * Removes the object at the top of this stack and returns that object as 
     * the value of this function.
     * @return The object at the top of this stack
     */
    public RenderPoint pop() {
        if (head == null)
            throw new EmptyStackException();
        RenderPoint top = head.data;
        head = head.next;
        return top;
    }
    
    /**
     * Looks at the object at the top of this stack without removing it from 
     * the stack.
     * @return the object at the top of this stack 
     */
    public RenderPoint peek() {
        if (head == null)
            throw new EmptyStackException();
        return head.data.copy();
    }
    
    /**
     * Tests if this stack is empty.
     * @return true if and only if this stack contains no items
     */
    public boolean empty() {
        return head == null;
    }
}
