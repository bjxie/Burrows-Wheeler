import edu.princeton.cs.algs4.StdOut;

public class CircularSuffixArray {
    private static final int CUTOFF = 5;   // cutoff to insertion sort (any value between 0 and 12)
    private final char[] text;
    private final int[] index;   // index[i] = j means text.substring(j) is ith largest suffix
    private final int n;


    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }
        n = s.length();
        this.text = s.toCharArray();
        this.index = new int[n];
        for (int i = 0; i < n; i++) {
            index[i] = i;
        }
        sort(0, n - 1, 0);
    }

    private void sort(int lo, int hi, int d) {
        if (lo + d >= 2 * n || hi + d >= 2 * n) {
            return;
        }

        // cutoff to insertion sort for small subarrays
        if (hi <= lo + CUTOFF) {
            insertion(lo, hi, d);
            return;
        }

        int lt = lo, gt = hi;
        char v = text[(index[lo] + d) % n];
        int i = lo + 1;
        while (i <= gt) {
            char t = text[(index[i] + d) % n];
            if (t < v) exch(lt++, i++);
            else if (t > v) exch(i, gt--);
            else i++;
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
        sort(lo, lt - 1, d);
        if (v > 0) sort(lt, gt, d + 1);
        sort(gt + 1, hi, d);
    }

    private void insertion(int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(index[j], index[j - 1], d); j--)
                exch(j, j - 1);
    }

    // is text[i+d..N) < text[j+d..N) ?
    private boolean less(int i, int j, int d) {
        if (i == j) return false;
        i = i + d;
        j = j + d;
        while (i < 2 * n && j < 2 * n) {
            if (text[i % n] < text[j % n]) return true;
            if (text[i % n] > text[j % n]) return false;
            i++;
            j++;
        }
        return i > j;
    }

    // exchange index[i] and index[j]
    private void exch(int i, int j) {
        int swap = index[i];
        index[i] = index[j];
        index[j] = swap;
    }

    // length of s
    public int length() {
        return n;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i > n - 1) {
            throw new IllegalArgumentException();
        }
        return index[i];
    }

    // unit testing (required)
    public static void main(String[] args) {
        String s = "BBBBABBBBB";
        CircularSuffixArray csa = new CircularSuffixArray(s);

        // should print (s.length())
        StdOut.println(csa.length());

        int[] a = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            a[i] = csa.index(i);
            StdOut.println("Index of " + i + " is " + a[i]);
        }
    }

}
