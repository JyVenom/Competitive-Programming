/*
ID: jerryya2
LANG: JAVA
TASK: heritage
*/

import java.io.*;
import java.util.ArrayList;

public class heritage {
    static int preIndex = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("heritage.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("heritage.out")));

        String[] a = br.readLine().split("");
        String[] b = br.readLine().split("");
        br.close();

        int len = a.length;
        int[] in = new int[len];
        int[] pre = new int[len];
        for (int i = 0; i < len; i++) {
            in[i] = a[i].charAt(0) - 'A';
            pre[i] = b[i].charAt(0) - 'A';
        }


        ArrayList<Integer> post = new ArrayList<>();
        printPost(in, pre, 0, len - 1, post);
        for (Integer cow : post) {
            pw.print((char) (cow + 'A'));
        }
        pw.println();
        pw.close();
    }

    private static void printPost(int[] in, int[] pre, int inStart, int inEnd, ArrayList<Integer> post) {
        if (inStart > inEnd)
            return;

        // Find index of next item in preorder traversal in
        // inorder.
        int inIndex = search(in, inStart, inEnd, pre[preIndex++]);

        // traverse left tree
        printPost(in, pre, inStart, inIndex - 1, post);

        // traverse right tree
        printPost(in, pre, inIndex + 1, inEnd, post);

        // print root node at the end of traversal
        post.add(in[inIndex]);
    }

    private static int search(int[] in, int startIn, int endIn, int data) {
        int i;
        for (i = startIn; i < endIn; i++)
            if (in[i] == data)
                return i;
        return i;
    }
}
