import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {

    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output
    public static void transform() {
        String instream = BinaryStdIn.readString();
        CircularSuffixArray csa = new CircularSuffixArray(instream);
        StringBuilder string = new StringBuilder();
        int index;
        int first = -1;
        int output;

        for (int i = 0; i < instream.length(); i++) {
            index = csa.index(i);
            if (index == 0) {
                first = i;
            }

            output = index - 1;
            if (output < 0) {
                output = output + instream.length();
            }
            string.append(instream.charAt(output));
        }
        BinaryStdOut.write(first);
        BinaryStdOut.write(string.toString());
        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {
        int first = BinaryStdIn.readInt();
        String encoded = BinaryStdIn.readString();
        int R = 256;
        int N = encoded.length();

        int[] count = new int[R + 1];
        int[] next = new int[N];

        for (int i = 0; i < N; i++) {
            count[encoded.charAt(i) + 1]++;
        }

        for (int r = 0; r < R; r++) {
            count[r + 1] += count[r];
        }

        for (int i = 0; i < N; i++) {
            next[count[encoded.charAt(i)]++] = i;
        }

        for (int i = next[first], c = 0; c < N; i = next[i], c++) {
            BinaryStdOut.write(encoded.charAt(i));
        }

        BinaryStdOut.close();

    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args[0].equals("-")) {
            transform();
        } else if (args[0].equals("+")) {
            inverseTransform();
        }
    }

}
