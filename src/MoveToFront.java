import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

import java.util.LinkedList;

public class MoveToFront {

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        LinkedList<Character> sequence = new LinkedList<>();
        // add each char in ASCII to a node in LinkedList
        for (int i = 0; i < 256; i++) {
            char add = (char) (i);
            sequence.addLast(add);
        }
        while (!BinaryStdIn.isEmpty()) {
            char in = BinaryStdIn.readChar(); // read a letter
            int out = sequence.indexOf(in); // get index of the letter
            BinaryStdOut.write((char) (out)); // output index
            sequence.remove(out); // move the letter to front
            sequence.addFirst(in);
        }
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        LinkedList<Character> sequence = new LinkedList<>();
        // add each char in ASCII to a node in LinkedList
        for (int i = 0; i < 256; i++) {
            char add = (char) (i);
            sequence.addLast(add);
        }
        while (!BinaryStdIn.isEmpty()) {
            int in = BinaryStdIn.readChar(); // read the first coded int
            char out = sequence.get(in); // find corresponding character to int
            BinaryStdOut.write(out); // output character
            sequence.remove(in); // move coded int to front
            sequence.addFirst(out);
        }
        BinaryStdOut.close();

    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {
        if (args[0].equals("-")) {
            encode();
        } else if (args[0].equals("+")) {
            decode();
        } else {
            throw new IllegalArgumentException();
        }
    }

}
