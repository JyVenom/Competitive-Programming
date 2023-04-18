//e * log (v)
//v < 1,000,000
//e < 4v == 4,000,000
//28,000,000
//dad says not best alg
//it worked but could be better

import java.io.*;
import java.util.*;

public class BessiesDream8 {
    static int[][] tile;
    static int[][][] visited;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int n, m;

    static MinIndexedDHeap q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("dream.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("dream.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        tile = new int[n][m];
        visited = new int[n][m][2];

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; ++j) {
                int color = Integer.parseInt(st.nextToken());
                tile[i][j] = color;
                visited[i][j][0] = visited[i][j][1] = 100000000;
            }
        }

        int edgeCount = 0;
        int N = n - 1;
        int M = m - 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tile[i][j] != 0) {
                    if (i > 0) {
                        if (tile[i - 1][j] != 0) {
                            edgeCount++;
                        }
                    }
                    if (i < N) {
                        if (tile[i + 1][j] != 0) {
                            edgeCount++;
                        }
                    }
                    if (j > 0) {
                        if (tile[i][j - 1] != 0) {
                            edgeCount++;
                        }
                    }
                    if (j < M) {
                        if (tile[i][j + 1] != 0) {
                            edgeCount++;
                        }
                    }
                }
            }
        }
        q = new MinIndexedDHeap(edgeCount / (n * m), n * m);
        q.insert(0, new value(0, 0));

        while (q.size() > 0) {
            int front = q.peekMinKeyIndex();
            value value = q.pollMinValue();
            int dist = value.dist;
            int row = front / m;
            int col = front % m;
            int isSmelly = value.isSmelly;

            if (row == m - 1 && col == n - 1) {
                pw.println(dist);
                pw.close();
                return;
            }
            if (visited[col][row][isSmelly] != 100000000) {
                continue;
            }
            visited[col][row][isSmelly] = dist;

            for (int[] dir : dirs) {
                int nx = row + dir[0];
                int ny = col + dir[1];
                int nd = dist + 1;
                int nSmelly = isSmelly;

                if (!isPathable(nx, ny, isSmelly)) continue;

                if (tile[ny][nx] == 4) {
                    while (isPathable(nx + dir[0], ny + dir[1], isSmelly) &&
                            tile[ny][nx] == 4) {
                        nx += dir[0];
                        ny += dir[1];
                        nd++;
                        nSmelly = 0;
                    }
                }
                if (tile[ny][nx] == 2) {
                    nSmelly = 1;
                }
                if (visited[ny][nx][nSmelly] <= nd) continue;
                q.insert(nx * m + ny, new value(nd, nSmelly));
            }
        }
        pw.println("-1");

        pw.close();
    }

    public static boolean isPathable(int x, int y, int smellsNice) {
        if (x < 0 || x >= m || y < 0 || y >= n) return false;
        if (tile[y][x] == 0) return false;
        if (tile[y][x] == 3) return (smellsNice > 0);

        return true;
    }

    private static class MinIndexedDHeap {

        // The Position Map (pm) maps Key Indexes (ki) to where the position of that
        // key is represented in the priority queue in the domain [0, sz).
        public final int[] pm;
        // The Inverse Map (im) stores the indexes of the keys in the range
        // [0, sz) which make up the priority queue. It should be noted that
        // 'im' and 'pm' are inverses of each other, so: pm[im[i]] = im[pm[i]] = i
        public final int[] im;
        // The values associated with the keys. It is very important  to note
        // that this array is indexed by the key indexes (aka 'ki').
        public final value[] values;
        // Maximum number of elements in the heap.
        private final int N;
        // The degree of every node in the heap.
        private final int D;
        // Lookup arrays to track the child/parent indexes of each node.
        private final int[] child, parent;
        // Current number of elements in the heap.
        private int sz;

        // Initializes a D-ary heap with a maximum capacity of maxSize.
        public MinIndexedDHeap(int degree, int maxSize) {
            if (maxSize <= 0) throw new IllegalArgumentException("maxSize <= 0");

            D = Math.max(2, degree);
            N = Math.max(D + 1, maxSize);

            im = new int[N];
            pm = new int[N];
            child = new int[N];
            parent = new int[N];
            values = new value[N];

            for (int i = 0; i < N; i++) {
                parent[i] = (i - 1) / D;
                child[i] = i * D + 1;
                pm[i] = im[i] = -1;
            }
        }

        public int size() {
            return sz;
        }

        public boolean isEmpty() {
            return sz == 0;
        }

        public boolean contains(int ki) {
            keyInBoundsOrThrow(ki);
            return pm[ki] != -1;
        }

        public int peekMinKeyIndex() {
            isNotEmptyOrThrow();
            return im[0];
        }

//            public int pollMinKeyIndex() {
//                int minKey = peekMinKeyIndex();
//                delete(minKey);
//                return minKey;
//            }

        public value peekMinValue() {
            isNotEmptyOrThrow();
            return values[im[0]];
        }

        public value pollMinValue() {
            value minValue = peekMinValue();
            delete(peekMinKeyIndex());
            return minValue;
        }

        public void insert(int ki, value value) {
            if (contains(ki)) throw new IllegalArgumentException("index already exists; received: " + ki);
            valueNotNullOrThrow(value);
            pm[ki] = sz;
            im[sz] = ki;
            values[ki] = value;
            swim(sz++);
        }

//            @SuppressWarnings("unchecked")
//            public T valueOf(int ki) {
//                keyExistsOrThrow(ki);
//                return (T) values[ki];
//            }

        public void delete(int ki) {
            keyExistsOrThrow(ki);
            final int i = pm[ki];
            swap(i, --sz);
            sink(i);
            swim(i);
            values[ki] = null;
            pm[ki] = -1;
            im[sz] = -1;
        }

//            @SuppressWarnings("unchecked")
//            public T update(int ki, T value) {
//                keyExistsAndValueNotNullOrThrow(ki, value);
//                final int i = pm[ki];
//                T oldValue = (T) values[ki];
//                values[ki] = value;
//                sink(i);
//                swim(i);
//                return oldValue;
//            }

//        // Strictly decreases the value associated with 'ki' to 'value'
//        public void decrease(int ki, value value) {
//            keyExistsAndValueNotNullOrThrow(ki, value);
//            if (less(value, values[ki])) {
//                values[ki] = value;
//                swim(pm[ki]);
//            }
//        }

//            // Strictly increases the value associated with 'ki' to 'value'
//            public void increase(int ki, T value) {
//                keyExistsAndValueNotNullOrThrow(ki, value);
//                if (less(values[ki], value)) {
//                    values[ki] = value;
//                    sink(pm[ki]);
//                }
//            }

        /* Helper functions */

        private void sink(int i) {
            for (int j = minChild(i); j != -1; ) {
                swap(i, j);
                i = j;
                j = minChild(i);
            }
        }

        private void swim(int i) {
            while (less(i, parent[i])) {
                swap(i, parent[i]);
                i = parent[i];
            }
        }

        // From the parent node at index i find the minimum child below it
        private int minChild(int i) {
            int index = -1, from = child[i], to = Math.min(sz, from + D);
            for (int j = from; j < to; j++) if (less(j, i)) index = i = j;
            return index;
        }

        private void swap(int i, int j) {
            pm[im[j]] = i;
            pm[im[i]] = j;
            int tmp = im[i];
            im[i] = im[j];
            im[j] = tmp;
        }

        // Tests if the value of node i < node j
        private boolean less(int i, int j) {
            return (values[im[i]].dist) < (values[im[j]].dist);
        }

//        @SuppressWarnings("unchecked")
//        private boolean less(Object obj1, Object obj2) {
//            return ((Comparable<? super T>) obj1).compareTo((T) obj2) < 0;
//        }

        @Override
        public String toString() {
            List<Integer> lst = new ArrayList<>(sz);
            for (int i = 0; i < sz; i++) lst.add(im[i]);
            return lst.toString();
        }

        /* Helper functions to make the code more readable. */

        private void isNotEmptyOrThrow() {
            if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        }

//        private void keyExistsAndValueNotNullOrThrow(int ki, Object value) {
//            keyExistsOrThrow(ki);
//            valueNotNullOrThrow(value);
//        }

        private void keyExistsOrThrow(int ki) {
            if (!contains(ki)) throw new NoSuchElementException("Index does not exist; received: " + ki);
        }

        private void valueNotNullOrThrow(Object value) {
            if (value == null) throw new IllegalArgumentException("value cannot be null");
        }

        private void keyInBoundsOrThrow(int ki) {
            if (ki < 0 || ki >= N)
                throw new IllegalArgumentException("Key index out of bounds; received: " + ki);
        }
    }

    public static class value {
        int dist;
        int isSmelly;

        public value(int dist, int isSmelly) {
            this.dist = dist;
            this.isSmelly = isSmelly;
        }
    }
}

