/*
 * GEE
 * This is free and unencumbered software released into the public domain.
 */
package gee_lab7_local;

/**
 * Represents a queue of RenderCommands, used to store drawing instructions
 * for L-systems.
 *
 */
public class RenderQueue {

    //The head of the queue, pointing to the first node.
    private  Node head;

    //The tail of the queue, pointing to the last node.
    private Node tail;

    /**
     * Constructs an empty RenderQueue.
     *
     */
    public RenderQueue() {
        head = null;
        tail = null;
    }

    /**
     * Enqueues a RenderCommand at the end of the queue.
     *
     * @param command The RenderCommand to be added to the queue.
     *
     */
    public void enqueue(RenderCommand command) {

        // Create a new node for the command
        Node newNode = new Node(command);

        // If the queue is empty, make the new node both head and tail
        if (empty()) {
            head = tail = newNode;
        } else {
            // Otherwise, append the new node to the tail / If not empty,
            // add the new node to the end of the queue and update the tail
            tail.next = newNode;
            tail = newNode;
        }
    }

    /**
     * Dequeues the first RenderCommand from the queue and returns it.
     *
     * @return The first RenderCommand in the queue, or null if the queue is
     * empty.
     *
     */
    public RenderCommand dequeue() {
        // Check if the queue is empty
        if (empty()) {
            return null;
        }

        // Store the command at the head / Get the RenderCommand from the
        // head of the queue
        RenderCommand command = head.data;

        // Update the head pointer to the next node / Move the head to the
        // next node
        head = head.next;

        // If the queue becomes empty, set the tail to null as well
        if (head == null) {
            tail = null;
        }

        // Return the dequeued RenderCommand
        return command;
    }


    /**
     * Checks if the queue is empty.
     *
     * @return True if the queue is empty, false otherwise.
     *
     */
    public boolean empty() {
        return head == null;
    }

    /**
     * Creates a deep copy of the queue.
     *
     * @return A deep copy of the queue.
     *
     */
    public RenderQueue copy() {

        // Create a new RenderQueue for the copy
        RenderQueue copy = new RenderQueue();

        // Start from the head of the original queue
        Node current = head;

        // Traverse the original queue and enqueue each RenderCommand
        // to the copy
        while (current != null) {

            // Directly add the same RenderCommand object (enum value)
            // to the copy
            copy.enqueue(current.data);
            current = current.next;
        }

        // Return the deep copy of the queue
        return copy;
    }

    /**
     * Appends the elements of another queue to the end of this queue,
     * without modifying the original queue.
     *
     * @param other The RenderQueue to append.
     *
     */
    public void append(RenderQueue other) {

        // Check if the other queue is null or empty
        if (other == null || other.empty()) {
            return;
        }

        // Create a copy to avoid modifying the original queue
        RenderQueue copy = other.copy();

        // Start from the head of the copied queue
        Node current = copy.head;

        // Enqueue each RenderCommand from the copied queue to this queue
        while (current != null) {
            enqueue(current.data);
            current = current.next;
        }
    }

    /**
     * Converts the queue to a string representation.
     *
     * @return A string representation of the queue.
     *
     */
    public String toString() {

        // Use a StringBuilder to efficiently build the string
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        // Start from the head of the queue
        Node current = head;

        // Traverse the queue and append each RenderCommand to the StringBuilder
        while (current != null) {
            sb.append(current.data.toString());

            // Add a comma if there are more elements in the queue
            if (current.next != null) {
                sb.append(", ");
            }

            // Move to the next node
            current = current.next;
        }
        sb.append("]");

        // Return the final string representation
        return sb.toString();
    }

    /**
     * Reads a queue from a string representation
     * (handles single-character commands).
     *
     * @param str The string representation of the queue.
     * @return The RenderQueue parsed from the string.
     * @throws IllegalArgumentException If the input string is null or empty.
     */
    public static RenderQueue fromString(String str) {

        // Check if the input string is null or empty
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("String cannot be null or empty");
        }

        // Create a new RenderQueue to store the parsed commands
        RenderQueue queue = new RenderQueue();

        // Remove leading/trailing whitespace
        str = str.trim();

        // Iterate through each character in the string
        for (char commandChar : str.toCharArray()) {

            // Map the character to a RenderCommand enum value
            RenderCommand command;
            switch (commandChar) {
                case 'F':
                    command = RenderCommand.FORWARD;
                    break;
                case '+':
                    command = RenderCommand.RIGHT;
                    break;
                case '-':
                    command = RenderCommand.LEFT;
                    break;
                case 'X':
                    command = RenderCommand.IGNORE;
                    break;
                case '[':  // Added case for '[' character
                    command = RenderCommand.PUSH; // Assuming '[' represents PUSH
                    break;
                case ']':  // Added case for ']' character
                    command = RenderCommand.POP;  // Assuming ']' represents POP
                    break;
                case 'R':
                    command = RenderCommand.FORWARD2;
                    break;

                default:
                    throw new IllegalArgumentException("Unknown command: " + commandChar);
            }

            // Enqueue the mapped RenderCommand to the queue
            queue.enqueue(command);
        }

        // Return the parsed RenderQueue
        return queue;
    }


    /**
     * Represents a node in the linked list forming the RenderQueue.
     *
     */
    private static class Node {
        RenderCommand data;
        Node next;

        /**
         * Constructs a Node with the given RenderCommand data.
         *
         * @param data The RenderCommand data for the node.
         *
         */
        public Node(RenderCommand data) {
            this.data = data;
            this.next = null;
        }
    }
}
