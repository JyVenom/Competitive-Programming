/*
ID: jerryya2
LANG: JAVA
TASK: castle
*/

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class castle {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("castle.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> V = new ArrayList<>();
        for (int i = 0; i < N * M; i++) {
            V.add(new ArrayList<>());
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int room = Integer.parseInt(st.nextToken());

                if (room == 0) {
                    V.get(i * M + j).add(i * M + j - 1);
                    V.get(i * M + j).add((i - 1) * M + j);
                    V.get(i * M + j).add(i * M + j + 1);
                    V.get(i * M + j).add((i + 1) * M + j);
                } else if (room == 1) {
                    V.get(i * M + j).add((i - 1) * M + j);
                    V.get(i * M + j).add(i * M + j + 1);
                    V.get(i * M + j).add((i + 1) * M + j);
                } else if (room == 2) {
                    V.get(i * M + j).add(i * M + j - 1);
                    V.get(i * M + j).add(i * M + j + 1);
                    V.get(i * M + j).add((i + 1) * M + j);
                } else if (room == 4) {
                    V.get(i * M + j).add(i * M + j - 1);
                    V.get(i * M + j).add((i - 1) * M + j);
                    V.get(i * M + j).add((i + 1) * M + j);
                } else if (room == 8) {
                    V.get(i * M + j).add(i * M + j - 1);
                    V.get(i * M + j).add((i - 1) * M + j);
                    V.get(i * M + j).add(i * M + j + 1);
                } else if (room == 3) {
                    V.get(i * M + j).add(i * M + j + 1);
                    V.get(i * M + j).add((i + 1) * M + j);
                } else if (room == 5) {
                    V.get(i * M + j).add((i - 1) * M + j);
                    V.get(i * M + j).add((i + 1) * M + j);
                } else if (room == 9) {
                    V.get(i * M + j).add((i - 1) * M + j);
                    V.get(i * M + j).add(i * M + j + 1);
                } else if (room == 6) {
                    V.get(i * M + j).add(i * M + j - 1);
                    V.get(i * M + j).add((i + 1) * M + j);
                } else if (room == 10) {
                    V.get(i * M + j).add(i * M + j - 1);
                    V.get(i * M + j).add(i * M + j + 1);
                } else if (room == 12) {
                    V.get(i * M + j).add(i * M + j - 1);
                    V.get(i * M + j).add((i - 1) * M + j);
                } else if (room == 7) {
                    V.get(i * M + j).add((i + 1) * M + j);
                } else if (room == 11) {
                    V.get(i * M + j).add(i * M + j + 1);
                } else if (room == 13) {
                    V.get(i * M + j).add((i - 1) * M + j);
                } else if (room == 14) {
                    V.get(i * M + j).add(i * M + j - 1);
                }
            }
        }
        br.readLine();

        //Find number of components/rooms (c - 1) and the size of the largest component/room (maxSize)
        int maxSize = 0;
        int size = N * M;
        int[] comp = new int[size];
        ArrayList<Integer> sizes = new ArrayList<>();
        boolean[] added = new boolean[size];
        ArrayList<Integer> queue = new ArrayList<>();
        int c = 1;
        for (int i = 0; i < size; i++) {
            if (comp[i] == 0) {
                added[i] = true;
                queue.add(i);
                int curSize = BFS(comp, V, added, queue, i, c, 0);
                if (curSize > maxSize) {
                    maxSize = curSize;
                }
                sizes.add(curSize);
                c++;
            }
        }

        //Finds the maximum size of a new room if a wall is broken down and the rooms are joined
        int maxComb = 0;
        ArrayList<int[]> pairs = new ArrayList<>();
//        int[] pair = new int[2];
        for (int i = 0; i < size; i++) {
            if (i - M >= 0) {
                int ci = comp[i];
                int co = comp[i - M];
                if (ci != co) {
                    int combSize = sizes.get(ci - 1) + sizes.get(co - 1);
                    if (combSize > maxComb) {
                        maxComb = combSize;
                        int[] newPair = new int[2];
                        newPair[0] = ci;
                        newPair[1] = co;
                        pairs = new ArrayList<>();
                        pairs.add(newPair);
//                        pair[0] = ci;
//                        pair[1] = co;
                    }
                    else if (combSize == maxComb) {
                        int[] newPair = new int[2];
                        newPair[0] = ci;
                        newPair[1] = co;
                        pairs.add(newPair);
                    }
                }
            }
            if (i + M < size) {
                int ci = comp[i];
                int co = comp[i + M];
                if (ci != co) {
                    int combSize = sizes.get(ci - 1) + sizes.get(co - 1);
                    if (combSize > maxComb) {
                        maxComb = combSize;
                        int[] newPair = new int[2];
                        newPair[0] = ci;
                        newPair[1] = co;
                        pairs = new ArrayList<>();
                        pairs.add(newPair);
//                        pair[0] = ci;
//                        pair[1] = co;
                    }
                    else if (combSize == maxComb) {
                        int[] newPair = new int[2];
                        newPair[0] = ci;
                        newPair[1] = co;
                        pairs.add(newPair);
                    }
                }
            }
            if (i % M != 0 && i - 1 >= 0) {
                int ci = comp[i];
                int co = comp[i - 1];
                if (ci != co) {
                    int combSize = sizes.get(ci - 1) + sizes.get(co - 1);
                    if (combSize > maxComb) {
                        maxComb = combSize;
                        int[] newPair = new int[2];
                        newPair[0] = ci;
                        newPair[1] = co;
                        pairs = new ArrayList<>();
                        pairs.add(newPair);
//                        pair[0] = ci;
//                        pair[1] = co;
                    }
                    else if (combSize == maxComb) {
                        int[] newPair = new int[2];
                        newPair[0] = ci;
                        newPair[1] = co;
                        pairs.add(newPair);
                    }
                }
            }
            if (i % M != M - 1 && i + 1 < size) {
                int ci = comp[i];
                int co = comp[i + 1];
                if (ci != co) {
                    int combSize = sizes.get(ci - 1) + sizes.get(co - 1);
                    if (combSize > maxComb) {
                        maxComb = combSize;
                        int[] newPair = new int[2];
                        newPair[0] = ci;
                        newPair[1] = co;
                        pairs = new ArrayList<>();
                        pairs.add(newPair);
//                        pair[0] = ci;
//                        pair[1] = co;
                    }
                    else if (combSize == maxComb) {
                        int[] newPair = new int[2];
                        newPair[0] = ci;
                        newPair[1] = co;
                        pairs.add(newPair);
                    }
                }
            }
        }

        //Finds the best wall between the two rooms
        int rowB = 0;
        int colB = M - 1;
        int dir = 0; //0 for N, 1 for E
        for (int[] pair : pairs) {
            for (int i = 0; i < size; i++) {
                if (comp[i] == pair[0]) {
                    if (i - M >= 0) {
                        if (comp[i - M] == pair[1]) {
                            int rowC = i / M;
                            int colC = i % M;
                            if (colC < colB) {
                                rowB = rowC;
                                colB = colC;
                                dir = 0;
                            } else if (colC == colB) {
                                if (rowC > rowB) {
                                    rowB = rowC;
                                    dir = 0;
                                } else if (rowC == rowB) {
                                    dir = 0;
                                }
                            }
                        }
                    }
                    if (i % M != M - 1) {
                        if (comp[i + 1] == pair[1]) {
                            int rowC = i / M;
                            int colC = i % M;
                            if (colC < colB) {
                                rowB = rowC;
                                colB = colC;
                                dir = 1;
                            } else if (colC == colB) {
                                if (rowC > rowB) {
                                    rowB = rowC;
                                    dir = 1;
                                } 
                            }
                        }
                    }
                }
                if (comp[i] == pair[1]) {
                    if (i - M >= 0) {
                        if (comp[i - M] == pair[0]) {
                            int rowC = i / M;
                            int colC = i % M;
                            if (colC < colB) {
                                rowB = rowC;
                                colB = colC;
                                dir = 0;
                            } else if (colC == colB) {
                                if (rowC > rowB) {
                                    rowB = rowC;
                                    dir = 0;
                                } else if (rowC == rowB) {
                                    dir = 0;
                                }
                            }
                        }
                    }
                    if (i % M != M - 1) {
                        if (comp[i + 1] == pair[0]) {
                            int rowC = i / M;
                            int colC = i % M;
                            if (colC < colB) {
                                rowB = rowC;
                                colB = colC;
                                dir = 1;
                            } else if (colC == colB) {
                                if (rowC > rowB) {
                                    rowB = rowC;
                                    dir = 1;
                                }
                            }
                        }
                    }
                }
            }
        }

        pw.println(c - 1);
        pw.println(maxSize);
        pw.println(maxComb);
        pw.println((rowB + 1) + " " + (colB + 1) + " " + (dir == 0 ? "N" : "E"));
        pw.close();
    }

//    private static void DFS (int[] comp, ArrayList<ArrayList<Integer>> V, int start, int c) {
//        comp[start] = c;
//        for (int i = 0; i < V.get(start).size(); i++) {
//            int next = V.get(start).get(i);
//            if (comp[next] != 0) {
//                DFS(comp, V, next, c);
//            }
//        }
//    }

    private static int BFS(int[] comp, ArrayList<ArrayList<Integer>> V, boolean[] added, ArrayList<Integer> queue, int start, int c, int size) {
        size++;
        comp[start] = c;
        queue.remove(0);
        for (int next : V.get(start)) {
            if (!added[next]) {
                added[next] = true;
                queue.add(next);
            }
        }
        if (queue.size() > 0) {
            size = BFS(comp, V, added, queue, queue.get(0), c, size);
        }
        return size;
    }
}
