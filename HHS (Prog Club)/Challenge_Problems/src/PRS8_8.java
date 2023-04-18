import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class PRS8_8 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        int[] rs = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            rs[i] = Integer.parseInt(st.nextToken());
        }
        Trie t = new Trie();
        for (int i = 0; i < n; i++) {
            t.insert(br.readLine(), rs[i], i + 1);
        }

        int q = Integer.parseInt(br.readLine());
        for (int i = 0; i < q; i++) {
            pw.println(t.findMax(br.readLine()));
        }

        pw.close();
    }

    private static class Trie {
        private final char rootCharacter = '\0';
        private final Node root = new Node(rootCharacter, -1, -1);

        public void insert(String key, int val, int num) {
            Node node = root;

            for (int i = 0; i < key.length(); ++i) {
                char ch = key.charAt(i);
                Node nextNode = node.children.get(ch);

                if (nextNode == null) {
                    nextNode = new Node(ch, val, num);
                    node.addChild(nextNode, ch);
                }

                node = nextNode;
                node.updateMax(val, num);
                node.count += 1;
            }

            if (node != root) node.isWordEnding = true;
        }

        public int findMax(String key) {
            Node node = root;

            for (int i = 0; i < key.length(); i++) {
                char ch = key.charAt(i);
                if (node == null) return 1;
                node = node.children.get(ch);
            }

            if (node != null) return node.maxInd;
            return 1;
        }

        private static class Node {
            char ch;
            int val, num, max = 0, maxInd = 0;
            int count = 0;
            boolean isWordEnding = false;
            java.util.Map<Character, Node> children = new java.util.HashMap<>();

            public Node(char ch, int val, int num) {
                this.ch = ch;
                this.val = val;
                this.num = num;
            }

            public void updateMax(int val, int num) {
                if (val > max) {
                    max = val;
                    maxInd = num;
                }
            }

            public void addChild(Node node, char c) {
                children.put(c, node);
            }
        }
    }
}
